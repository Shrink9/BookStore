package ServiceConcept.Service;

import DAOConcept.DAOImple.BookDAOImple;
import Entity.Book;
import TemporaryEntity.BookQueryPredicate;
import TemporaryEntity.Page;
import Utils.ConnectionManager;

import java.sql.Connection;

public class BookService{
    Connection connection=ConnectionManager.getConnection();
    BookDAOImple bookDAOImple=new BookDAOImple();
    public BookService(Connection connection){
        this.connection=connection;
    }
    public BookService(){
    }
    public Page<Book> queryPageWithPredicate(BookQueryPredicate bookQueryPredicate){
        Page<Book> page=new Page<>(bookQueryPredicate.getCurrentPageNo(),bookQueryPredicate.getPageSize(),bookQueryPredicate.getMinPrice(),bookQueryPredicate.getMaxPrice());
        int totalBookAmount=bookDAOImple.getBooksAmountWithPredicate(connection,bookQueryPredicate);
        page.setTotalPageAmount((int)Math.ceil((totalBookAmount*1.0)/page.getPageSize()));
        page.setLastPageNo((int)Math.ceil((totalBookAmount*1.0)/page.getPageSize()));
        page.setContentList(bookDAOImple.getBooksWithPredicate(connection,bookQueryPredicate));
        return page;
    }
    public Book queryBookByName(Book book){
        return bookDAOImple.getBookByName(connection,book);
    }
    public int changeStock(Book book,int stockChangeAmount){
        book.setStock(book.getStock()+stockChangeAmount);
        return bookDAOImple.updateBookStockByName(connection,book);
    }
    public int increaseSaleAmount(Book book,int saleAmountIncrement){
        book.setSaleAmount(book.getSaleAmount()+saleAmountIncrement);
        return bookDAOImple.updateBookSaleAmountByName(connection,book);
    }
}
