package club.gaiaproject.homework.source.config;

import club.gaiaproject.homework.source.common.DynamicDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Phoenix Luo
 * @version 2020/11/30
 **/
@Configuration
public class DataSourceConfiguration {
    @Value("${datasource.master.jdbc-url}")
    private String masterUrl;
    
    @Value("${datasource.master.username}")
    private String masterUser;
    
    @Value("${datasource.master.password}")
    private String masterPwd;
    
    @Value("${datasource.slave.jdbc-url}")
    private String slaveUrl;
    
    @Value("${datasource.slave.username}")
    private String slaveUser;
    
    @Value("${datasource.slave.password}")
    private String slavePwd;
    
    // 自己实现 多数据源切换
    @Bean
    public DataSource getDataSource() {
        HikariConfig masterConfig = new HikariConfig();
        masterConfig.setJdbcUrl(masterUrl);
        masterConfig.setUsername(masterUser);
        masterConfig.setPassword(masterPwd);
        masterConfig.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        masterConfig.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        masterConfig.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource master = new HikariDataSource(masterConfig);
        
        HikariConfig slaveConfig = new HikariConfig();
        slaveConfig.setJdbcUrl(slaveUrl);
        slaveConfig.setUsername(slaveUser);
        slaveConfig.setPassword(slavePwd);
        slaveConfig.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        slaveConfig.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        slaveConfig.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource slave = new HikariDataSource(slaveConfig);
        
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put("master", master);
        dsMap.put("slave", slave);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(dsMap);
        dataSource.setDefaultTargetDataSource(master);
        
        return dataSource;
    }
}
