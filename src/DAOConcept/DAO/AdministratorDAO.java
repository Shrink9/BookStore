package DAOConcept.DAO;

import Entity.Administrator;

import java.sql.Connection;
import java.util.ArrayList;

public interface AdministratorDAO{
    /**
     * 插入管理员
     *
     * @param connection 有效的数据库连接
     * @param administrator       administrator.id(自动生成)
     * @return 受影响的行数,0则代表用户名重复.
     */
    int insertAdministrator(Connection connection,Administrator administrator);
    /**
     * 通过用户名修改密码
     *
     * @param connection 有效的数据库连接
     * @param administrator       administrator.username指定用户名,administrator.password指定新密码.
     * @return 受影响的行数
     */
    int updatePasswordByUsername(Connection connection,Administrator administrator);
    /**
     * 通过用户名获取管理员
     *
     * @param connection 有效的数据库连接
     * @param administrator       administrator.username指定用户名
     * @return 获得的管理员
     */
    public Administrator getAdministratorByUsername(Connection connection,Administrator administrator);
    /**
     * 通过用户名和密码获取管理员
     *
     * @param connection 有效的数据库连接
     * @param administrator       administrator.username指定用户名,administrator.password指定密码.
     * @return 获得的管理员
     */
    Administrator getAdministratorByUsernameAndPassword(Connection connection,Administrator administrator);
    /**
     * 获取所有管理员
     *
     * @param connection 有效的数据库连接
     * @return 获得的管理员列表
     */
    ArrayList<Administrator> getAllAdministrators(Connection connection);
    /**
     * 获取管理员总数
     *
     * @param connection 有效的数据库连接
     * @return 管理员总数
     */
    int getAllAdministratorsAmount(Connection connection);
}
