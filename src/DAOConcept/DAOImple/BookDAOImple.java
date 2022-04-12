package DAOConcept.DAOImple;

import Entity.Book;
import DAOConcept.DAO.BookDAO;
import TemporaryEntity.BookQueryPredicate;

import java.sql.Connection;
import java.util.ArrayList;

public class BookDAOImple extends BaseDAOImple<Book> implements BookDAO{
    @Override
    public Book getBookByName(Connection connection,Book book){
        String sqlStatement="select id,\n"+"    name,\n"+"    author,\n"+"    publish_time publishTime,\n"+"    cover_link   coverLink,\n"+"    markup_link markupLink,\n"+"    price,\n"+"    sale_amount  saleAmount,\n"+"    stock\n"+"from book\n"+"where name=?;";
        return getBean(connection,sqlStatement,book.getName());
    }
    @Override
    public ArrayList<Book> getAllBooks(Connection connection){
        String sqlStatement="select id,\n"+"    name,\n"+"    author,\n"+"    publish_time publishTime,\n"+"    cover_link   coverLink,\n"+"    markup_link markupLink,\n"+"    price,\n"+"    sale_amount  saleAmount,\n"+"    stock\n"+"from book;";
        return getBeanList(connection,sqlStatement);
    }
    @Override
    public int insertBook(Connection connection,Book book){
        String sqlStatement="insert book(name, author, publish_time, cover_link, markup_link markupLink, price, stock)\n"+"values (?,"+"?,?,?,?,?,?);";
        return update(connection,sqlStatement,book.getName(),book.getAuthor(),book.getPublishTime(),book.getCoverLink(),book.getMarkupLink(),book.getPrice(),book.getStock());
    }
    @Override
    public int deleteBookByName(Connection connection,Book book){
        String sqlStatement="delete book\n"+"from book\n"+"where name=?;";
        return update(connection,sqlStatement,book.getName());
    }
    @Override
    public int updateBookStockByName(Connection connection,Book book){
        String sqlStatement="update book\n"+"set stock=?\n"+"where name=?;";
        return update(connection,sqlStatement,book.getStock(),book.getName());
    }
    @Override
    public int updateBookSaleAmountByName(Connection connection,Book book){
        String sqlStatement="update book\n"+"set sale_amount=?\n"+"where name=?;";
        return update(connection,sqlStatement,book.getSaleAmount(),book.getName());
    }
    @Override
    public int getAllBooksAmount(Connection connection){
        String sqlStatement="select count(*) from book";
        return ((Long)getAggregationValue(connection,sqlStatement)).intValue();
    }
    @Override
    public ArrayList<Book> getBooksWithPredicate(Connection connection,BookQueryPredicate bookQueryPredicate){
        String sqlStatement="select id,\n"+"    name,\n"+"    author,\n"+"    publish_time publishTime,\n"+"    cover_link   coverLink,\n"+"    markup_link markupLink,\n"+"    price,\n"+"    sale_amount  saleAmount,\n"+"    stock\n"+"from book\n"+"where price>=? and price<=?\n"+"limit ?,?;";
        return getBeanList(connection,sqlStatement,bookQueryPredicate.getMinPrice(),bookQueryPredicate.getMaxPrice(),(bookQueryPredicate.getCurrentPageNo()-1)*bookQueryPredicate.getPageSize(),bookQueryPredicate.getPageSize());
    }
    @Override
    public int getBooksAmountWithPredicate(Connection connection,BookQueryPredicate bookQueryPredicate){
        String sqlStatement="select count(*)\n"+"from book\n"+"where price>=? and price<=?;";
        return ((Long)getAggregationValue(connection,sqlStatement,bookQueryPredicate.getMinPrice(),bookQueryPredicate.getMaxPrice())).intValue();
    }
}
