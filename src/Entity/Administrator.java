package Entity;

public class Administrator{
    private int id;
    private String username;
    private String password;
    public Administrator(){
    }
    public Administrator(String username,String password){
        this.username=username;
        this.password=password;
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
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    @Override
    public String toString(){
        return "Administrator{"+"username='"+username+'\''+", password='"+password+'\''+", id="+id+'}';
    }
}
