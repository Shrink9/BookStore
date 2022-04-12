package TemporaryEntity;

import java.util.ArrayList;

public class Page<T>{
    ArrayList<T> contentList;
    int firstPageNo=1;
    int lastPageNo;
    int nextPageNo;
    int prePageNo;
    int currentPageNo;
    int totalPageAmount;
    int pageSize;
    double minPrice;
    double maxPrice;
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
    public Page(int currentPageNo,int pageSize,double minPrice,double maxPrice){
        this.currentPageNo=currentPageNo;
        this.pageSize=pageSize;
        this.minPrice=minPrice;
        this.maxPrice=maxPrice;
        this.prePageNo=currentPageNo-1;
        this.nextPageNo=currentPageNo+1;
    }
    public ArrayList<T> getContentList(){
        return contentList;
    }
    public void setContentList(ArrayList<T> contentList){
        this.contentList=contentList;
    }
    public int getFirstPageNo(){
        return firstPageNo;
    }
    public void setFirstPageNo(int firstPageNo){
        this.firstPageNo=firstPageNo;
    }
    public int getLastPageNo(){
        return lastPageNo;
    }
    public void setLastPageNo(int lastPageNo){
        this.lastPageNo=lastPageNo;
    }
    public int getNextPageNo(){
        return nextPageNo;
    }
    public void setNextPageNo(int nextPageNo){
        this.nextPageNo=nextPageNo;
    }
    public int getPrePageNo(){
        return prePageNo;
    }
    public void setPrePageNo(int prePageNo){
        this.prePageNo=prePageNo;
    }
    public int getCurrentPageNo(){
        return currentPageNo;
    }
    public void setCurrentPageNo(int currentPageNo){
        this.currentPageNo=currentPageNo;
    }
    public int getTotalPageAmount(){
        return totalPageAmount;
    }
    public void setTotalPageAmount(int totalPageAmount){
        this.totalPageAmount=totalPageAmount;
    }
    public int getPageSize(){
        return pageSize;
    }
    public void setPageSize(int pageSize){
        this.pageSize=pageSize;
    }
    public boolean hasNextPage(){
        return nextPageNo<=totalPageAmount;
    }
    public boolean hasPrePage(){
        return prePageNo>=1;
    }
}
