package Entity;

public class TradeItem{
    private int id;
    private int tradeId;
    private String bookName;
    private int purchaseAmount;
    private double costAmount;
    public TradeItem(int tradeId,String bookName,int purchaseAmount,double costAmount){
        this.tradeId=tradeId;
        this.bookName=bookName;
        this.purchaseAmount=purchaseAmount;
        this.costAmount=costAmount;
    }
    public TradeItem(){
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getTradeId(){
        return tradeId;
    }
    public void setTradeId(int tradeId){
        this.tradeId=tradeId;
    }
    public String getBookName(){
        return bookName;
    }
    public void setBookName(String bookName){
        this.bookName=bookName;
    }
    public int getPurchaseAmount(){
        return purchaseAmount;
    }
    public void setPurchaseAmount(int purchaseAmount){
        this.purchaseAmount=purchaseAmount;
    }
    public double getCostAmount(){
        return costAmount;
    }
    public void setCostAmount(double costAmount){
        this.costAmount=costAmount;
    }
    @Override
    public String toString(){
        return "TradeItem{"+"id="+id+", tradeId="+tradeId+", bookName='"+bookName+'\''+", purchaseAmount="+purchaseAmount+", costAmount="+costAmount+'}';
    }
}
