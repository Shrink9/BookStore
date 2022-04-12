package TemporaryEntity;

import Entity.Book;
import Entity.Goods;

public class ShoppingCartItem<T extends Goods>{
    private T content;
    private int amount;
    private double totalMoney;
    public ShoppingCartItem(T content,String amount){
        this.content=content;
        this.amount=Integer.parseInt(amount);
        this.totalMoney=content.getPrice()*this.amount;
    }
    public T getContent(){
        return content;
    }
    public void setContent(T content){
        this.content=content;
    }
    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount=amount;
    }
    public void addAmount(int incrementAmount){
        this.amount+=incrementAmount;
    }
    public void addTotalMoney(double incrementMoney){
        this.totalMoney+=incrementMoney;
    }
    public double getTotalMoney(){
        return totalMoney;
    }
}
