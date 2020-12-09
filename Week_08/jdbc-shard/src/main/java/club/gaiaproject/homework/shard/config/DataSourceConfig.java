package club.gaiaproject.homework.shard.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Phoenix Luo
 * @version 2020/12/9
 **/
@Configuration
public class DataSourceConfig {
    @Value("${hasSharding}")
    private Boolean hasSharding;
    
    
    @Value("${datasource.d0.jdbc-url}")
    private String masterUrl;
    
    @Value("${datasource.d0.username}")
    private String masterUser;
    
    @Value("${datasource.d0.password}")
    private String masterPwd;
    
    @Value("${datasource.d1.jdbc-url}")
    private String slaveUrl;
    
    @Value("${datasource.d1.username}")
    private String slaveUser;
    
    @Value("${datasource.d1.password}")
    private String slavePwd;
    
    @Value("${datasource.singleton.jdbc-url}")
    private String url;
    
    @Value("${datasource.singleton.username}")
    private String user;
    
    @Value("${datasource.singleton.password}")
    private String pwd;
    
    @ConditionalOnProperty(
            value = {"hasSharding"},
            havingValue = "true")
    @Bean(name = "d0")
    public DataSource d0Datasource() {
        HikariConfig masterConfig = new HikariConfig();
        masterConfig.setJdbcUrl(masterUrl);
        masterConfig.setUsername(masterUser);
        masterConfig.setPassword(masterPwd);
        masterConfig.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        masterConfig.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        masterConfig.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource master = new HikariDataSource(masterConfig);
        
        return master;
    }
    
    @ConditionalOnProperty(
            value = {"hasSharding"},
            havingValue = "true")
    @Bean(name = "d1")
    public DataSource d1Datasource() {
        HikariConfig slaveConfig = new HikariConfig();
        slaveConfig.setJdbcUrl(slaveUrl);
        slaveConfig.setUsername(slaveUser);
        slaveConfig.setPassword(slavePwd);
        slaveConfig.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        slaveConfig.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        slaveConfig.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource d1 = new HikariDataSource(slaveConfig);
        
        return d1;
    }
    
    @ConditionalOnProperty(
            value = {"hasSharding"},
            havingValue = "false")
    @Bean("singleton")
    public DataSource singletonDatasource() {
        HikariConfig masterConfig = new HikariConfig();
        masterConfig.setJdbcUrl(url);
        masterConfig.setUsername(user);
        masterConfig.setPassword(pwd);
        masterConfig.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        masterConfig.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        masterConfig.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource master = new HikariDataSource(masterConfig);
        
        return master;
    }
}
