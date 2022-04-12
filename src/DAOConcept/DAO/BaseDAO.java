package DAOConcept.DAO;

import org.apache.commons.dbutils.QueryRunner;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;

public interface BaseDAO<T>{
    /**
     * 通用的DML以及DDL
     *
     * @param connection   要进行的连接
     * @param sqlStatement 要执行的sql语句
     * @param params       填充参数
     * @return 受影响的行数,如果失败则返回-1.
     */
    int update(Connection connection,String sqlStatement,Object... params);
    /**
     * 查询获取一个Bean对象
     *
     * @param connection   要进行的连接
     * @param sqlStatement 要执行的sql语句
     * @param params       填充参数
     * @return 获取的对象,如果失败则返回null.
     */
    T getBean(Connection connection,String sqlStatement,Object... params);
    /**
     * 查询获取Bean对象组成的ArrayList
     *
     * @param connection   要进行的连接
     * @param sqlStatement 要执行的sql语句
     * @param params       填充参数
     * @return 获取的ArrayList,如果失败则返回null.
     */
    ArrayList<T> getBeanList(Connection connection,String sqlStatement,Object... params);
    /**
     * 查询获取一个聚合函数返回值
     *
     * @param connection   要进行的连接
     * @param sqlStatement 要执行的sql语句
     * @param params       填充参数
     * @return 聚合函数返回值,如果失败则返回null.
     */
    <E> E getAggregationValue(Connection connection,String sqlStatement,Object... params);
    /**
     * @param connection   要进行的连接
     * @param sqlStatement 要执行的sql语句
     * @param params       填充参数(每一个都是一个数组)
     * @return 受影响的行数,如果失败则返回-1.
     */
    int batchInsert(Connection connection,String sqlStatement,Object[]... params);
}
