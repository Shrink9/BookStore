package Tester;

import DAOConcept.DAO.TradeDAO;
import DAOConcept.DAOImple.*;
import Entity.*;
import Utils.ConnectionManager;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester01{
    @Test
    public void testBookDAOImple(){
        Connection connection=ConnectionManager.getConnection();
        BookDAOImple imple=new BookDAOImple();
        Book book=new Book();
        book.setName("白鹿原");
        Book bookByName=imple.getBookByName(connection,book);
        System.out.println(bookByName);
    }
    @Test
    public void testUserDAOImple(){
        Connection connection=ConnectionManager.getConnection();
        UserDAOImple imple=new UserDAOImple();
        User user=new User();
        ArrayList<User> allUsers=imple.getAllUsers(connection);
        System.out.println(allUsers);
    }
    @Test
    public void testAdministratorDAOImple(){
        Connection connection=ConnectionManager.getConnection();
        AdministratorDAOImple imple=new AdministratorDAOImple();
        Administrator administrator=new Administrator("admin","admin");
        Administrator password=imple.getAdministratorByUsernameAndPassword(connection,administrator);
        System.out.println(password);
    }
    @Test
    public void testTradeDAOImple(){
        Connection connection=ConnectionManager.getConnection();
        TradeDAOImple imple=new TradeDAOImple();
        Trade trade=new Trade();
        trade.setUserId(1);
        trade.setCostAmount(0);
        imple.insertTrade(connection,trade);
    }
    @Test
    public void testTradeItemDAOImple(){
        Connection connection=ConnectionManager.getConnection();
        TradeItemDAOImple imple=new TradeItemDAOImple();
        TradeItem tradeItem=new TradeItem(1,"白鹿原",1,1);
    }
}
