package club.gaiaproject.homework.shard.dao;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Phoenix Luo
 * @version 2020/12/9
 **/
public interface BaseDao {
    /**
     * 根据用户id找到查询的datasource
     *
     * @param userId
     * @return
     */
    DataSource generateDatasource(Long userId);
    
    /**
     * 返回所有datasource 用于广播
     *
     * @return
     */
    List<DataSource> getAllDatasource();
    
    /**
     * 根据orderId查询要搜索的表如果为空
     *
     * @param orderId
     * @return
     */
    String generateTable(String orderId);
    
    /**
     * 返回所有分表
     *
     * @return
     */
    List<String> getAllTables();
    
}
