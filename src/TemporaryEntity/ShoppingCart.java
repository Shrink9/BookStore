package TemporaryEntity;

import Entity.Goods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ShoppingCart<T extends Goods>{
    private HashMap<String,ShoppingCartItem<T>> itemHashMap=new HashMap<>();
    public ShoppingCart(){
    }
    /**
     * 购物车项(商品)加入购物车
     * @param key 商品名称
     * @param shoppingCartItem 购物车项
     */
    public void addShoppingCartItem(String key,ShoppingCartItem<T> shoppingCartItem){
        if(itemHashMap.get(key)!=null){
            int incrementAmount=shoppingCartItem.getAmount();
            ((ShoppingCartItem<T>)itemHashMap.get(key)).addAmount(incrementAmount);
            double incrementMoney=shoppingCartItem.getTotalMoney();
            ((ShoppingCartItem<T>)itemHashMap.get(key)).addTotalMoney(incrementMoney);
        }
        else {
            itemHashMap.put(key,shoppingCartItem);
        }
    }
    public void removeShoppingCartItem(String key){
        itemHashMap.remove(key);
    }
    public ShoppingCartItem<T> getShoppingCartItem(String key){
        return (ShoppingCartItem<T>)itemHashMap.get(key);
    }
    public Collection<ShoppingCartItem<T>> getItems(){
         return itemHashMap.values();
    }
    public double getShoppingCartTotalMoney(){
        Collection<ShoppingCartItem<T>> items=getItems();
        double shoppingCartTotalMoney=0;
        for(ShoppingCartItem<T> item:items){
            shoppingCartTotalMoney+=item.getTotalMoney();
        }
        return shoppingCartTotalMoney;
    }
    public HashMap<String,ShoppingCartItem<T>> getItemHashMap(){
        return itemHashMap;
    }
    public void clearShoppingCart(){
        itemHashMap.clear();
    }
}
