package DAOConcept.DAOImple;

import DAOConcept.DAO.AdministratorDAO;
import Entity.Administrator;

import java.sql.Connection;
import java.util.ArrayList;

public class AdministratorDAOImple extends BaseDAOImple<Administrator> implements AdministratorDAO{
    @Override
    public int insertAdministrator(Connection connection,Administrator administrator){
        if(getAdministratorByUsername(connection,administrator)==null){
            return update(connection,"insert administrator (username,password)\n"+"values (?,?)",
                    administrator.getUsername(),administrator.getPassword());
        }
        else{
            return 0;
        }
    }
    @Override
    public int updatePasswordByUsername(Connection connection,Administrator administrator){
        String sqlStatement="update administrator\n"+"set password=?\n"+"where username=?;";
        return update(connection,sqlStatement,administrator.getPassword(),administrator.getUsername());
    }
    @Override
    public Administrator getAdministratorByUsername(Connection connection,Administrator administrator){
        String sqlStatement="select *\n"+"from administrator\n"+"where username=?;";
        return getBean(connection,sqlStatement,administrator.getUsername());
    }
    @Override
    public Administrator getAdministratorByUsernameAndPassword(Connection connection,Administrator administrator){
        String sqlStatement="select *\n"+"from administrator\n"+"where username=? and password=?;";
        return getBean(connection,sqlStatement,administrator.getUsername(),administrator.getPassword());
    }
    @Override
    public ArrayList<Administrator> getAllAdministrators(Connection connection){
        return getBeanList(connection,"select *\n"+"from administrator;");
    }
    @Override
    public int getAllAdministratorsAmount(Connection connection){
        String sqlStatement="select count(*) from administrator";
        return ((Long)getAggregationValue(connection,sqlStatement)).intValue();
    }
}
