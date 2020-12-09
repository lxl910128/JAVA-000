package club.gaiaproject.homework.shard.dao;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

/**
 * @author Phoenix Luo
 * @version 2020/12/9
 **/
public class SingletonOrderDao extends OrderDao {
    
    private DataSource dataSource;
    
    public SingletonOrderDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    
    public DataSource generateDatasource(Long userId) {
        return dataSource;
    }
    
    @Override
    public List<DataSource> getAllDatasource() {
        return Collections.singletonList(dataSource);
    }
    
    @Override
    public String generateTable(String orderId) {
        return "order";
    }
    
    @Override
    public List<String> getAllTables() {
        return Collections.singletonList("order");
    }
}
