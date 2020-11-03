package club.projectgaia.geekbang.gateway.common;

import io.netty.util.AttributeKey;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
public abstract class Consts {
    public static final AttributeKey<GatewayStatus> GATEWAY_STATUS = AttributeKey.valueOf("gateway.status");

    public static final String ERROR_RESPONSE = "{\"data\":\"service error!\"}";

}
