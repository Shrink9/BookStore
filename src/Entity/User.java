package Entity;

public class User{
    private int id;
    private String username;
    private String password;
    private double balance;
    public User(String username,String password){
        this.username=username;
        this.password=password;
    }
    public User(){
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance=balance;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    @Override
    public String toString(){
        return "User{"+"id="+id+", username='"+username+'\''+", password='"+password+'\''+", balance="+balance+'}';
    }
}
