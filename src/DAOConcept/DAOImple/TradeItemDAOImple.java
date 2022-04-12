package DAOConcept.DAOImple;

import DAOConcept.DAO.TradeItemDAO;
import Entity.Book;
import Entity.TradeItem;
import TemporaryEntity.ShoppingCart;
import TemporaryEntity.ShoppingCartItem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;

public class TradeItemDAOImple extends BaseDAOImple<TradeItem> implements TradeItemDAO{
    @Override
    public int batchInsertTradeItem(Connection connection,ArrayList<TradeItem> tradeItems){
        String sqlStatement="insert trade_item(trade_id, book_name, purchase_amount, cost_amount)\n"+"values (?,"+"?,"+"?,?);";
        Object[][] params=new Object[tradeItems.size()][];
        //将tradeItem的每个属性化为数组元素,tradeItem便成为数组,并将tradeItems化为二维数组.
        int i=0;
        for(TradeItem tradeItem:tradeItems){
            ArrayList<Object> param=new ArrayList<>();
            param.add(tradeItem.getTradeId());
            param.add(tradeItem.getBookName());
            param.add(tradeItem.getPurchaseAmount());
            param.add(tradeItem.getCostAmount());
            params[i]=param.toArray();
            i++;
        }
        return batchInsert(connection,sqlStatement,params);
    }
    @Override
    public ArrayList<TradeItem> getTradeItemsByTradeId(Connection connection,TradeItem tradeItem){
        String sqlStatement="select id, trade_id tradeId, book_name bookName, purchase_amount purchaseAmount, " +
                "cost_amount costAmount\n"+"from trade_item\n"+"where trade_id=?;";
        return getBeanList(connection,sqlStatement,tradeItem.getTradeId());
    }
    @Override
    public TradeItem getTradeItemById(Connection connection,TradeItem tradeItem){
        String sqlStatement="select id, trade_id tradeId, book_name bookName, purchase_amount purchaseAmount, cost_amount costAmount\n"+"from trade_item\n"+"where id=?;";
        return getBean(connection,sqlStatement,tradeItem.getId());
    }
    @Override
    public ArrayList<TradeItem> getAllTradeItems(Connection connection){
        String sqlStatement="select id, trade_id tradeId, book_name bookName, purchase_amount purchaseAmount, cost_amount costAmount\n"+"from trade_item;";
        return getBeanList(connection,sqlStatement);
    }
    @Override
    public int getAllTradeItemsAmount(Connection connection){
        String sqlStatement="select count(*)\n"+"from trade_item;";
        return getAggregationValue(connection,sqlStatement);
    }
}
