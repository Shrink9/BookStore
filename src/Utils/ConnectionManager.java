package Utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * JDBC实用类，包含一些通用操作。
 */
public class ConnectionManager{
    /**
     * 通过Druid连接池获取连接(推荐使用)
     * 以配置文件ConnectionPool.properties内容为参数获取连接
     *
     * @return 如果连接失败，则返回null.
     */
    //静态代码块保证在类加载时可以正确给连接池赋值
    private static DruidDataSource dataSource=null;
    static{
        try{
            Properties properties=new Properties();
            properties.load(new FileInputStream("L:\\JavaEE\\BookStore\\web\\WEB-INF\\configuration\\ConnectionPool.properties"));
            dataSource=(DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
            dataSource.setMaxActive(30);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try{
            return dataSource.getConnection();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 关闭连接
     *
     * @param connection 要关闭的连接
     * @return 如果关闭成功则为true,否则为false.
     */
    public static boolean closeConnection(Connection connection){
        try{
            connection.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

