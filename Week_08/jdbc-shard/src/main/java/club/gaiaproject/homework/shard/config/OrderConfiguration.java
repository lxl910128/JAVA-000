package club.gaiaproject.homework.shard.config;

import club.gaiaproject.homework.shard.dao.ManyOrderDao;
import club.gaiaproject.homework.shard.dao.OrderDao;
import club.gaiaproject.homework.shard.dao.SingletonOrderDao;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Phoenix Luo
 * @version 2020/12/9
 **/
@Configuration
public class OrderConfiguration {
    @ConditionalOnProperty(
            value = {"hasSharding"},
            havingValue = "true")
    @Bean
    public OrderDao manyOrderDao(Map<String, DataSource> allDataSource) {
        return new ManyOrderDao(allDataSource);
    }
    
    @ConditionalOnProperty(
            value = {"hasSharding"},
            havingValue = "false")
    @Bean
    public OrderDao singletonOrderDao(DataSource dataSource) {
        return new SingletonOrderDao(dataSource);
    }
}
