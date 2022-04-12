package ServiceConcept.Service;

import DAOConcept.DAOImple.TradeItemDAOImple;
import Entity.Book;
import Entity.Trade;
import Entity.TradeItem;
import Entity.User;
import TemporaryEntity.ShoppingCart;
import TemporaryEntity.ShoppingCartItem;
import Utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

public class ComplexService{
    private Connection connection=ConnectionManager.getConnection();
    private BookService bookService=new BookService(connection);
    private UserService userService=new UserService(connection);
    private TradeService tradeService=new TradeService(connection);
    private TradeItemService tradeItemService=new TradeItemService(connection);
    public ArrayList<String> checkout(ShoppingCart<Book> shoppingCart,User user){
        HashMap<String,ShoppingCartItem<Book>> itemHashMap=shoppingCart.getItemHashMap();
        //提示信息列表
        ArrayList<String> tips=new ArrayList<>();

        if(user.getBalance()<shoppingCart.getShoppingCartTotalMoney()){

            //余额不足
            tips.add("余额不足,请先充值!");
            return tips;
        }
        //以事务的方式处理结账
        try{
            connection.setAutoCommit(false);
            //tradeItems为tradeItem列表
            ArrayList<TradeItem> tradeItems=new ArrayList<>();
            //真实的消费金额,因为有一些可能因为库存不够而没有买.
            double realTotalPay=0;
            for(String bookName: itemHashMap.keySet()){
                ShoppingCartItem<Book> item=shoppingCart.getItemHashMap().get(bookName);
                //查库存
                Book book=bookService.queryBookByName(item.getContent());
                int stock=book.getStock();
                //查购物车中此书的数量
                int purchaseAmount=item.getAmount();
                if(stock<purchaseAmount){
                    //库存不足就不买了,添加库存不足信息.
                    tips.add(bookName);
                }
                else{
                    //改库存和销量
                    bookService.changeStock(book,-purchaseAmount);
                    bookService.increaseSaleAmount(book,purchaseAmount);
                    //改用户余额
                    userService.payMoney(user,item.getTotalMoney());
                    //真实的消费金额增加
                    realTotalPay+=item.getTotalMoney();
                    //先设置交易项
                    TradeItem tradeItem=new TradeItem();
                    tradeItem.setBookName(bookName);
                    tradeItem.setCostAmount(item.getTotalMoney());
                    tradeItem.setPurchaseAmount(purchaseAmount);
                    tradeItems.add(tradeItem);
                }
            }
            tips.add(realTotalPay+"");
            //加交易信息
            Trade trade=new Trade(user.getId(),realTotalPay);
            tradeService.addTradeRecord(trade);
            //通过用户Id获取用户最新交易的交易Id
            int tradeId=tradeService.queryLastTradeIdByUserId(trade);
            //设置好各个tradeItem的tradeId
            tradeItems.forEach((TradeItem tradeItem)->{
                tradeItem.setTradeId(tradeId);
            });
            //加交易项信息
            tradeItemService.batchAddTradeItem(tradeItems);
            //清空购物车
            shoppingCart.clearShoppingCart();
            connection.commit();
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
        finally{
            try{
                connection.setAutoCommit(true);
            }
            catch(SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return tips;
    }
}
