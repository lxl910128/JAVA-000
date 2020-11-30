package club.gaiaproject.homework.source;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.sql.DataSource;

/**
 * @author Phoenix Luo
 * @version 2020/11/30
 **/
@SpringBootApplication
@EnableConfigurationProperties
@Slf4j
public class DataSourceApplication implements CommandLineRunner {
    
    @Autowired
    private DataSource dataSource;
    
    public static void main(String[] args) {
        SpringApplication.run(DataSourceApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        log.info(dataSource.getClass().getCanonicalName());
    }
}
