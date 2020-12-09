package club.gaiaproject.homework.shard.dao;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Phoenix Luo
 * @version 2020/12/9
 **/
public class ManyOrderDao extends OrderDao {
    private Map<String, DataSource> allDataSource;
    
    public ManyOrderDao(Map<String, DataSource> allDataSource) {
        this.allDataSource = allDataSource;
    }
    
    private String dataSourceName = "d%s";
    
    private String tableName = "order_%s";
    
    private String[] tables = {"order_0", "order_1", "order_2", "order_3", "order_4", "order_5", "order_6", "order_7",
            "order_8", "order_9", "order_10", "order_11", "order_12", "order_13", "order_14", "order_15"};
    
    @Override
    public DataSource generateDatasource(Long userId) {
        Long offset = userId % 2;
        return allDataSource.get(String.format(dataSourceName, offset.toString()));
    }
    
    @Override
    public List<DataSource> getAllDatasource() {
        return new ArrayList<>(allDataSource.values());
    }
    
    @Override
    public String generateTable(String orderId) {
        Integer offset = orderId.hashCode() % 16;
        return String.format(tableName, offset);
    }
    
    @Override
    public List<String> getAllTables() {
        return Arrays.asList(tables);
    }
}
