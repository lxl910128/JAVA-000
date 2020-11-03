package club.projectgaia.geekbang.gateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
@Data
@Component
@ConfigurationProperties(prefix = "service")
public class ServiceProperties {
    private Map<String, String> router;
}

