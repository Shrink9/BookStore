package Entity;

import java.sql.Timestamp;

public class Book extends Goods{
    private int id;
    private String name;
    private String author;
    private Timestamp publishTime;
    private String coverLink;
    private String markupLink;
    private double price;
    private int saleAmount;
    private int stock;
    public Book(String name,String author,Timestamp publishTime,String coverLink,String markupLink,double price,int saleAmount,int stock){
        this.name=name;
        this.author=author;
        this.publishTime=publishTime;
        this.coverLink=coverLink;
        this.markupLink=markupLink;
        this.price=price;
        this.saleAmount=saleAmount;
        this.stock=stock;
    }
    public Book(){
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author=author;
    }
    public Timestamp getPublishTime(){
        return publishTime;
    }
    public void setPublishTime(Timestamp publishTime){
        this.publishTime=publishTime;
    }
    public String getCoverLink(){
        return coverLink;
    }
    public void setCoverLink(String coverLink){
        this.coverLink=coverLink;
    }
    public String getMarkupLink(){
        return markupLink;
    }
    public void setMarkupLink(String markupLink){
        this.markupLink=markupLink;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public int getSaleAmount(){
        return saleAmount;
    }
    public void setSaleAmount(int saleAmount){
        this.saleAmount=saleAmount;
    }
    public int getStock(){
        return stock;
    }
    public void setStock(int stock){
        this.stock=stock;
    }
    @Override
    public String toString(){
        return "Book{"+"id="+id+", name='"+name+'\''+", author='"+author+'\''+", publishTime="+publishTime+", coverLink='"+coverLink+'\''+", markupLink='"+markupLink+'\''+", price="+price+", saleAmount="+saleAmount+", stock="+stock+'}';
    }
}
