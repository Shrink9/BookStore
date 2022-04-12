package DAOConcept.DAO;

import Entity.User;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

public interface UserDAO{
    /**
     * 插入用户
     *
     * @param connection 有效的数据库连接
     * @param user       user.id(自动生成)以及user.balance(默认为0)无效
     * @return 受影响的行数,0则代表用户名重复.
     */
    int insertUser(Connection connection,User user);
    /**
     * 通过用户名修改密码
     *
     * @param connection 有效的数据库连接
     * @param user       user.username指定用户名,user.password指定新密码.
     * @return 受影响的行数
     */
    int updatePasswordByUsername(Connection connection,User user);
    /**
     * 通过用户名获取用户
     *
     * @param connection 有效的数据库连接
     * @param user       user.username指定用户名
     * @return 获得的用户
     */
    public User getUserByUsername(Connection connection,User user);
    /**
     * 通过用户名和密码获取用户
     *
     * @param connection 有效的数据库连接
     * @param user       user.username指定用户名,user.password指定密码.
     * @return 获得的用户
     */
    User getUserByUsernameAndPassword(Connection connection,User user);
    /**
     * 获取所有用户
     *
     * @param connection 有效的数据库连接
     * @return 获得的用户列表
     */
    ArrayList<User> getAllUsers(Connection connection);
    /**
     * 获取用户总数
     *
     * @param connection 有效的数据库连接
     * @return 用户总数
     */
    int getAllUsersAmount(Connection connection);
    /**
     * 通过用户名更改用户余额
     * @param connection 有效的数据库连接
     * @param user user.balance指定余额,user.username指定用户名.
     * @return
     */
    int updateBalanceByUsername(Connection connection,User user);
}
