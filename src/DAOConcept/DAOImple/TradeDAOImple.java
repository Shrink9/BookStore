package DAOConcept.DAOImple;

import DAOConcept.DAO.TradeDAO;
import Entity.Trade;

import java.sql.Connection;
import java.util.ArrayList;

public class TradeDAOImple extends BaseDAOImple<Trade> implements TradeDAO{
    @Override
    public int insertTrade(Connection connection,Trade trade){
        String sqlStatement="insert trade(user_id,cost_amount)\n"+"values (?,?)";
        return update(connection,sqlStatement,trade.getUserId(),trade.getCostAmount());
    }
    @Override
    public ArrayList<Trade> getTradesByUserId(Connection connection,Trade trade){
        String sqlStatement="select id, user_id userId, time, cost_amount costAmount\n"+"from trade\n"+"where "+
                "user_id=? order by time desc;";
        return getBeanList(connection,sqlStatement,trade.getUserId());
    }
    @Override
    public Trade getTradeById(Connection connection,Trade trade){
        String sqlStatement="select id, user_id userId, time, cost_amount costAmount\n"+"from trade\n"+"where id=?;";
        return getBean(connection,sqlStatement,trade.getId());
    }
    @Override
    public ArrayList<Trade> getAllTrades(Connection connection){
        String sqlStatement="select id, user_id userId, time, cost_amount costAmount\n"+"from trade;";
        return getBeanList(connection,sqlStatement);
    }
    @Override
    public int getAllTradesAmount(Connection connection){
        String sqlStatement="select count(*)\n"+"from trade;";
        return ((Long)getAggregationValue(connection,sqlStatement)).intValue();
    }
    @Override
    public Trade getLastTradeIdByUserId(Connection connection,Trade trade){
        String sqlStatement="select id, user_id userId, time, cost_amount costAmount\n"+"from trade\n"+"where user_id=?\n"+"order by time desc\n"+"limit 1;";
        return getBean(connection,sqlStatement,trade.getUserId());
    }
}
