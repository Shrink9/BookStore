package DAOConcept.DAOImple;

import DAOConcept.DAO.UserDAO;
import Entity.User;

import java.sql.Connection;
import java.util.ArrayList;

public class UserDAOImple extends BaseDAOImple<User> implements UserDAO{
    @Override
    public User getUserByUsername(Connection connection,User user){
        String sqlStatement="select *\n"+"from user\n"+"where username=?;";
        return getBean(connection,sqlStatement,user.getUsername());
    }
    @Override
    public int insertUser(Connection connection,User user){
        return update(connection,"insert user (username,password)\n"+"values (?,?)",user.getUsername(),user.getPassword());
    }
    @Override
    public int updatePasswordByUsername(Connection connection,User user){
        String sqlStatement="update user\n"+"set password=?\n"+"where username=?;";
        return update(connection,sqlStatement,user.getPassword(),user.getUsername());
    }
    @Override
    public User getUserByUsernameAndPassword(Connection connection,User user){
        String sqlStatement="select *\n"+"from user\n"+"where username=? and password=?;";
        return getBean(connection,sqlStatement,user.getUsername(),user.getPassword());
    }
    @Override
    public ArrayList<User> getAllUsers(Connection connection){
        return getBeanList(connection,"select *\n"+"from user;");
    }
    @Override
    public int getAllUsersAmount(Connection connection){
        String sqlStatement="select count(*) from user";
        return ((Long)getAggregationValue(connection,sqlStatement)).intValue();
    }
    @Override
    public int updateBalanceByUsername(Connection connection,User user){
        String sqlStatement="update user\n"+"set balance=?\n"+"where username=?;";
        return update(connection,sqlStatement,user.getBalance(),user.getUsername());
    }
}
