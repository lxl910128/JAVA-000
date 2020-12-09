package club.gaiaproject.homework.shard.dao;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Phoenix Luo
 * @version 2020/12/9
 **/
@Slf4j
public abstract class OrderDao implements BaseDao {
    
    private final String SELECT_BY_USER_AND_ORDER_ID = "select * from `%s` where user_id = ? and id = ?";
    
    private final String SELECT_BY_CREATE_TIME = "select * from `%s` where create_time >= %d  and create_time <= %d";
    
    private final String INSERT_SQL = "insert into `%s` (id,user_id,address,phone,total_price,create_time,update_time) values(?,?,?,?,?,?,?)";
    
    private final String DELETE_SQL = "delete from `%s` where id =?";
    
    private final String UPDATE_SQL = "UPDATE `%s` SET `status`=%d WHERE user_id=%d AND total_price >= %f;";
    
    /**
     * 根据库分区建ID 和 表分区建 orderId 查询1条订单数据
     *
     * @param userId
     * @param orderId
     */
    public void selectByUserAndOrderId(Long userId, String orderId) {
        DataSource dataSource = generateDatasource(userId);
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(SELECT_BY_USER_AND_ORDER_ID, generateTable(orderId)));
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, orderId);
            ResultSet ret = preparedStatement.executeQuery();
            while (ret.next()) {
                printlnOrder(ret);
            }
            
        } catch (SQLException sql) {
            log.error("sql 报错！", sql);
        }
    }
    
    
    /**
     * 根据创建起止时间查询 需要广播
     *
     * @param start
     * @param end
     */
    public void selectByCreateTime(Long start, Long end) {
        List<DataSource> allDatasource = getAllDatasource();
        for (DataSource dataSource : allDatasource) {
            try (Connection connection = dataSource.getConnection()) {
                Statement statement = connection.createStatement();
                List<String> tables = getAllTables();
                for (String table : tables) {
                    ResultSet ret = statement.executeQuery(String.format(SELECT_BY_CREATE_TIME, table, start, end));
                    while (ret.next()) {
                        printlnOrder(ret);
                    }
                }
            } catch (SQLException sql) {
                log.error("sql 报错！", sql);
            }
        }
    }
    
    
    /**
     * 插入一条数据，需要路由
     */
    public void insert(String orderId, Long userId, String address, String phone, Float totalPrice) {
        DataSource dataSource = generateDatasource(userId);
        String table = generateTable(orderId);
        try (Connection connection = dataSource.getConnection()) {
            log.info(String.format(INSERT_SQL, table));
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(INSERT_SQL, table));
            preparedStatement.setString(1, orderId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phone);
            preparedStatement.setFloat(5, totalPrice);
            preparedStatement.setLong(6, System.currentTimeMillis());
            preparedStatement.setLong(7, System.currentTimeMillis());
            log.info("{}插入:{}", table, preparedStatement.executeUpdate());
        } catch (SQLException sql) {
            log.error("sql 报错！", sql);
        }
        
    }
    
    /**
     * 根据订单id删除数据，需要广播到各个库的某张表中
     */
    public void deleteByOrderId(String orderId) {
        List<DataSource> allDatasource = getAllDatasource();
        for (DataSource dataSource : allDatasource) {
            try (Connection connection = dataSource.getConnection()) {
                String table = generateTable(orderId);
                PreparedStatement preparedStatement = connection.prepareStatement(String.format(DELETE_SQL, table));
                preparedStatement.setString(1, orderId);
                
                log.info("删除:{}", preparedStatement.executeUpdate());
            } catch (SQLException sql) {
                log.error("sql 报错！", sql);
            }
        }
    }
    
    /**
     * 根据用户及价格 更新 ,需要在指定库中更新所有表
     *
     * @param userId
     * @param ltePrice
     */
    public void updateByUserAndLessThanPrice(Long userId, Float ltePrice, Integer newStatus) {
        DataSource dataSource = generateDatasource(userId);
        List<String> tables = getAllTables();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            for (String table : tables) {
                log.info("{}更新:{}", table, statement.executeUpdate(String.format(UPDATE_SQL, table, newStatus, userId, ltePrice)));
                
            }
        } catch (SQLException sql) {
            log.error("sql 报错！", sql);
        }
        
    }
    
    
    private void printlnOrder(ResultSet ret) throws SQLException {
        log.info("row:");
        Integer totalColumn = ret.getMetaData().getColumnCount();
        for (int i = 1; i <= totalColumn; i++) {
            log.info("{} ---> {}", ret.getMetaData().getColumnName(i), ret.getObject(i));
        }
    }
    
    
}
