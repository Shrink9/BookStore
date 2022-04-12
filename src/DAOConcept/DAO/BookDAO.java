package DAOConcept.DAO;

import Entity.Book;
import TemporaryEntity.BookQueryPredicate;

import java.sql.Connection;
import java.util.ArrayList;

public interface BookDAO{
    /**
     * 获取指定名字的图书
     *
     * @param connection 有效的数据库连接
     * @param book       book.name指定书名
     * @return 指定的图书
     */
    Book getBookByName(Connection connection,Book book);
    /**
     * 获取所有图书
     *
     * @param connection 有效的数据库连接
     * @return 全部图书
     */
    ArrayList<Book> getAllBooks(Connection connection);
    /**
     * 新增图书
     *
     * @param connection 有效的数据库连接
     * @param book       book.id(自动生成)和book.saleAmount(默认为0)无效
     * @return 受影响的行数
     */
    int insertBook(Connection connection,Book book);
    /**
     * 通过名称删除图书
     *
     * @param connection 有效的数据库连接
     * @param book       book.name指定书名
     * @return 受影响的行数
     */
    int deleteBookByName(Connection connection,Book book);
    /**
     * 通过图书名找到图书然后修改图书的库存
     *
     * @param connection 有效的数据库连接
     * @param book       book.name指定图书名,book.stock指定新的库存
     * @return
     */
    int updateBookStockByName(Connection connection,Book book);
    /**
     * 通过图书名找到图书然后修改图书的销量
     *
     * @param connection 有效的数据库连接
     * @param book       book.name指定图书名,book.saleAmount指定新的销售量
     * @return
     */
    int updateBookSaleAmountByName(Connection connection,Book book);
    /**
     * 获取所有图书总数
     *
     * @param connection 有效的数据库连接
     * @return 图书总数
     */
    int getAllBooksAmount(Connection connection);
    /**
     * 获取有指定价格区间且分页的图书列表
     *
     * @param connection         有效的数据库连接
     * @param bookQueryPredicate
     * @return 图书列表
     */
    public ArrayList<Book> getBooksWithPredicate(Connection connection,BookQueryPredicate bookQueryPredicate);
    /**
     * 获取有指定价格区间的图书数量
     *
     * @param connection         有效的数据库连接
     * @param bookQueryPredicate
     * @return 图书数量
     */
    public int getBooksAmountWithPredicate(Connection connection,BookQueryPredicate bookQueryPredicate);
}
