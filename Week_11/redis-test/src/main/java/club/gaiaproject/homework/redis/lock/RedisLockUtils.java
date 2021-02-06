package club.gaiaproject.homework.redis.lock;

import club.gaiaproject.homework.redis.common.JedisPoolEnum;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author Phoenix Luo
 * @version 2021/1/11
 **/
public abstract class RedisLockUtils {
    public static final String LUA_LOCK_SCRIPTS = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then" +
            "redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end";
    
    public static final String LUA_UNLOCK_SCRIPTS = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
            "return redis.call('del',KEYS[1]) else return 0 end";
    
    public static final String LUA_EXPIRE_SCRIPTS = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
            "return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end";
    
    
    public static String lock(String key, Long expireSeconds) {
        try (Jedis jedis = JedisPoolEnum.POOL.getConnenction()) {
            String uuid = UUID.randomUUID().toString();
            List<String> keys = new ArrayList<>();
            List<String> values = new ArrayList<>();
            keys.add(key);
            values.add(uuid);
            values.add(String.valueOf(expireSeconds));
            Object result = jedis.eval(LUA_LOCK_SCRIPTS, keys, values);
            if (result.equals(1)) {
                return uuid;
            } else {
                throw new RuntimeException("redis 上锁失败！key：" + key);
            }
        }
    }
    
    public boolean renew(String key, String value, Integer time) {
        try (Jedis jedis = JedisPoolEnum.POOL.getConnenction()) {
            List<String> keys = new ArrayList<>();
            List<String> values = new ArrayList<>();
            keys.add(key);
            values.add(value);
            values.add(String.valueOf(time));
            Object result = jedis.eval(LUA_EXPIRE_SCRIPTS, keys, values);
            if (result.equals(1)) {
                return true;
            } else {
                System.out.println("key：" + key + " 续期失败！");
                return false;
            }
        }
    }
    
    public static boolean unlock(String key, String value) {
        try (Jedis jedis = JedisPoolEnum.POOL.getConnenction()) {
            return jedis.eval(LUA_UNLOCK_SCRIPTS, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
        }
    }
}
