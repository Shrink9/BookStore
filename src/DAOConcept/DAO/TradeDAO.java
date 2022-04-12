package DAOConcept.DAO;

import Entity.Trade;

import java.sql.Connection;
import java.util.ArrayList;

public interface TradeDAO{
    /**
     * 插入交易
     *
     * @param connection 有效的数据库连接
     * @param trade       trade.id(自动生成)以及trade.time(自动生成)无效
     * @return 受影响的行数
     */
    int insertTrade(Connection connection,Trade trade);
    /**
     * 获取指定用户Id的交易
     *
     * @param connection 有效的数据库连接
     * @param trade       trade.userId指定用户Id
     * @return 获得的交易(可能有多个)
     */
    public ArrayList<Trade> getTradesByUserId(Connection connection,Trade trade);
    /**
     * 获取指定Id的交易
     *
     * @param connection 有效的数据库连接
     * @param trade       trade.id指定id
     * @return获得的交易
     */
    Trade getTradeById(Connection connection,Trade trade);
    /**
     * 获取所有交易
     *
     * @param connection 有效的数据库连接
     * @return 获得的交易列表
     */
    ArrayList<Trade> getAllTrades(Connection connection);
    /**
     * 获取交易总数
     *
     * @param connection 有效的数据库连接
     * @return 交易总数
     */
    int getAllTradesAmount(Connection connection);
    /**
     * 获取指定用户的最新的交易记录
     * @param connection 有效的数据库连接
     * @param trade trade.userId指定用户id
     * @return
     */
    Trade getLastTradeIdByUserId(Connection connection,Trade trade);
}
