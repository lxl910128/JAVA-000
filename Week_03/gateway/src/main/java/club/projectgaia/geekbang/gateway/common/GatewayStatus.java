package club.projectgaia.geekbang.gateway.common;

import lombok.Data;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
@Data
public class GatewayStatus {
    private Boolean filter;
    private String url;
}
