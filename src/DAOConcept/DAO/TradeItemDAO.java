package DAOConcept.DAO;

import Entity.TradeItem;

import java.sql.Connection;
import java.util.ArrayList;

public interface TradeItemDAO{
    /**
     * 批量插入交易项
     *
     * @param connection 有效的数据库连接
     * @param tradeItems 交易项列表,其中每个tradeItem.id无效(自动生成).
     * @return 受影响的行数
     */
     int batchInsertTradeItem(Connection connection,ArrayList<TradeItem> tradeItems);
    /**
     * 获取指定交易Id的交易项
     *
     * @param connection 有效的数据库连接
     * @param tradeItem  tradeItem.tradeId指定交易Id
     * @return 获得的交易项(可能有多个)
     */
    public ArrayList<TradeItem> getTradeItemsByTradeId(Connection connection,TradeItem tradeItem);
    /**
     * 获取指定Id的交易项
     *
     * @param connection 有效的数据库连接
     * @param tradeItem  tradeItem.id指定id
     * @return 获得的交易项
     */
    TradeItem getTradeItemById(Connection connection,TradeItem tradeItem);
    /**
     * 获取所有交易项
     *
     * @param connection 有效的数据库连接
     * @return 获得的交易项列表
     */
    ArrayList<TradeItem> getAllTradeItems(Connection connection);
    /**
     * 获取交易项总数
     *
     * @param connection 有效的数据库连接
     * @return 交易项总数
     */
    int getAllTradeItemsAmount(Connection connection);
}
