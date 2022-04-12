package Entity;

import java.sql.Timestamp;

public class Trade{
    private int id;
    private int userId;
    private Timestamp time;
    private double costAmount;
    public Trade(int userId,double costAmount){
        this.userId=userId;
        this.costAmount=costAmount;
    }
    public Trade(){
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId=userId;
    }
    public Timestamp getTime(){
        return time;
    }
    public void setTime(Timestamp time){
        this.time=time;
    }
    public double getCostAmount(){
        return costAmount;
    }
    public void setCostAmount(double costAmount){
        this.costAmount=costAmount;
    }
    @Override
    public String toString(){
        return "TradeDAO{"+"id="+id+", userId="+userId+", time="+time+", costAmount="+costAmount+'}';
    }
}
