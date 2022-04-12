package TemporaryEntity;

public class BookQueryPredicate{
    private double minPrice;
    private double maxPrice;
    private int currentPageNo;
    private int pageSize;
    public BookQueryPredicate(String minPrice,String maxPrice,String currentPageNo,String pageSize){
        this.minPrice=Double.parseDouble(minPrice);
        this.maxPrice=Double.parseDouble(maxPrice);
        this.currentPageNo=Integer.parseInt(currentPageNo);
        this.pageSize=Integer.parseInt(pageSize);
    }
    public double getMinPrice(){
        return minPrice;
    }
    public void setMinPrice(double minPrice){
        this.minPrice=minPrice;
    }
    public double getMaxPrice(){
        return maxPrice;
    }
    public void setMaxPrice(double maxPrice){
        this.maxPrice=maxPrice;
    }
    public int getCurrentPageNo(){
        return currentPageNo;
    }
    public void setCurrentPageNo(int currentPageNo){
        this.currentPageNo=currentPageNo;
    }
    public int getPageSize(){
        return pageSize;
    }
    public void setPageSize(int pageSize){
        this.pageSize=pageSize;
    }
}
