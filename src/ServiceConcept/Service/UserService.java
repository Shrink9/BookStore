package ServiceConcept.Service;

import DAOConcept.DAOImple.UserDAOImple;
import Entity.Book;
import Entity.User;
import TemporaryEntity.ShoppingCart;
import TemporaryEntity.ShoppingCartItem;
import Utils.ConnectionManager;

import java.sql.Connection;
import java.util.ArrayList;

public class UserService{
    UserDAOImple userDAOImple=new UserDAOImple();
    Connection connection=ConnectionManager.getConnection();
    public UserService(Connection connection){
        this.connection=connection;
    }
    public UserService(){
    }
    public int register(User user){
        return userDAOImple.insertUser(connection,user);
    }
    public User login(User user){
        return userDAOImple.getUserByUsernameAndPassword(connection,user);
    }
    public ArrayList<User> queryAllUsers(){
        return userDAOImple.getAllUsers(connection);
    }
    public boolean checkDuplicateUsername(User user){
        if(userDAOImple.getUserByUsername(connection,user)!=null){
            return true;
        }
        else{
            return false;
        }
    }
    public int payMoney(User user,double totalPay){
        double newBalance=user.getBalance()-totalPay;
        user.setBalance(newBalance);
        return userDAOImple.updateBalanceByUsername(connection,user);
    }
    public int changePassword(User user){
        return userDAOImple.updatePasswordByUsername(connection,user);
    }
    public int recharge(User user,String amount){
        double rechargeAmount=Double.parseDouble(amount);
        user.setBalance(user.getBalance()+rechargeAmount);
        return userDAOImple.updateBalanceByUsername(connection,user);
    }
}
