package ServiceConcept.Service;

import DAOConcept.DAOImple.BookDAOImple;
import DAOConcept.DAOImple.TradeDAOImple;
import DAOConcept.DAOImple.TradeItemDAOImple;
import Entity.Trade;
import Entity.TradeItem;
import Utils.ConnectionManager;

import java.sql.Connection;
import java.util.ArrayList;

public class TradeItemService{
    private Connection connection=ConnectionManager.getConnection();
    private TradeItemDAOImple tradeItemDAOImple=new TradeItemDAOImple();
    public TradeItemService(Connection connection){
        this.connection=connection;
    }
    public TradeItemService(){
    }
    public int batchAddTradeItem(ArrayList<TradeItem> tradeItems){
        return tradeItemDAOImple.batchInsertTradeItem(connection,tradeItems);
    }
    public ArrayList<TradeItem> queryTradeItemsByTradeId(TradeItem tradeItem){
        return tradeItemDAOImple.getTradeItemsByTradeId(connection,tradeItem);
    }
}
