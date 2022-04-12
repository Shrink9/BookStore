package DAOConcept.DAOImple;

import DAOConcept.DAO.BaseDAO;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseDAOImple<T> implements BaseDAO<T>{
    private QueryRunner queryRunner=new QueryRunner();
    //定义一个Class对象来接收泛型的Class对象
    private Class<T> clazz=null;
    //获取T的Class对象，获取泛型的类型，泛型是在被子类继承时才确定
    {
        //获取父类的类型       (this最终为子类对象的this)
        Type genericSuperclass=this.getClass().getGenericSuperclass();
        ParameterizedType paramType=(ParameterizedType)genericSuperclass;
        //获取父类的泛型类型
        Type[] actualTypeArguments=paramType.getActualTypeArguments();
        //给clazz赋值
        clazz=(Class<T>)actualTypeArguments[0];
    }
    @Override
    public int update(Connection connection,String sqlStatement,Object... params){
        int count=-1;
        try{
            count=queryRunner.update(connection,sqlStatement,params);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    @Override
    public T getBean(Connection connection,String sqlStatement,Object... params){
        T t=null;
        try{
            t=queryRunner.query(connection,sqlStatement,new BeanHandler<T>(clazz),params);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return t;
    }
    @Override
    public ArrayList<T> getBeanList(Connection connection,String sqlStatement,Object... params){
        ArrayList<T> beanList=null;
        try{
            beanList=(ArrayList<T>)queryRunner.query(connection,sqlStatement,new BeanListHandler<T>(clazz),params);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return beanList;
    }
    @Override
    public <E> E getAggregationValue(Connection connection,String sqlStatement,Object... params){
        Object value=null;
        try{
            // 调用queryRunner的query方法获取一个单一的值
            value=queryRunner.query(connection,sqlStatement,new ScalarHandler<>(),params);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return (E)value;
    }
    @Override
    public int batchInsert(Connection connection,String sqlStatement,Object[]... params){
        int[] counts=null;
        int amount=-1;
        try{
            counts=queryRunner.batch(connection,sqlStatement,params);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        if(counts!=null){
            amount=0;
            for(int count: counts){
                amount+=count;
            }
        }
        return amount;
    }
}
