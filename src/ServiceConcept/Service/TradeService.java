package ServiceConcept.Service;

import DAOConcept.DAOImple.TradeDAOImple;
import DAOConcept.DAOImple.TradeItemDAOImple;
import Entity.Trade;
import Utils.ConnectionManager;

import java.sql.Connection;
import java.util.ArrayList;

public class TradeService{
    private Connection connection=ConnectionManager.getConnection();
    private TradeDAOImple tradeDAOImple=new TradeDAOImple();
    public TradeService(Connection connection){
        this.connection=connection;
    }
    public TradeService(){
    }
    public int addTradeRecord(Trade trade){
        return tradeDAOImple.insertTrade(connection,trade);
    }
    public int queryLastTradeIdByUserId(Trade trade){
        trade=tradeDAOImple.getLastTradeIdByUserId(connection,trade);
        return trade.getId();
    }
    public ArrayList<Trade> queryTradesByUserId(Trade trade){
        return tradeDAOImple.getTradesByUserId(connection,trade);
    }
}
