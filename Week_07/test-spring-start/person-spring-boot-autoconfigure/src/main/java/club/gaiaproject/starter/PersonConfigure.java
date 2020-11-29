package club.gaiaproject.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Phoenix Luo
 * @version 2020/11/18
 **/
@Data
@ConfigurationProperties(prefix = "person")
public class PersonConfigure {
    private String name;
    private Boolean enable;
}
