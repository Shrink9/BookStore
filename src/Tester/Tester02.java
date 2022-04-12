package Tester;

import DAOConcept.DAOImple.BookDAOImple;
import Entity.Book;
import TemporaryEntity.BookQueryPredicate;
import Utils.ConnectionManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Tester02{
    @Test
    public void test01(){
        BookDAOImple imple=new BookDAOImple();
        ArrayList<Book> books=imple.getBooksWithPredicate(ConnectionManager.getConnection(),new BookQueryPredicate("100","100","1","10"));
        System.out.println(books);
    }
}
