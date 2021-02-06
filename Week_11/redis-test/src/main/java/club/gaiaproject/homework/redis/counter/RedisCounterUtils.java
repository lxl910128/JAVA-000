package club.gaiaproject.homework.redis.counter;

import club.gaiaproject.homework.redis.common.JedisPoolEnum;
import redis.clients.jedis.Jedis;

/**
 * @author Phoenix Luo
 * @version 2021/1/12
 **/
public class RedisCounterUtils {
    public static Long incr(String key) {
        try (Jedis jedis = JedisPoolEnum.POOL.getConnenction()) {
            return jedis.incr(key);
        }
    }
    
    public static Long decr(String key) {
        try (Jedis jedis = JedisPoolEnum.POOL.getConnenction()) {
            return jedis.decr(key);
        }
    }
}
