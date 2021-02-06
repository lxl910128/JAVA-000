package club.gaiaproject.homework.redis.common;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Phoenix Luo
 * @version 2021/1/11
 **/
public enum JedisPoolEnum {
    POOL;
    private JedisPool pool;
    
    public Jedis getConnenction() {
        if (pool == null) {
            init();
        }
        return pool.getResource();
    }
    
    public void init() {
        if (pool == null) {
            //GenericObjectPoolConfig.DEFAULT_MAX_TOTAL = 8
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            //设置最大连接数为默认值的5倍
            poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 5);
            //设置最大空闲连接数为默认值的3倍
            poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 3);
            //设置最小空闲连接数为默认值的2倍
            poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE * 2);
            //设置连接池没有连接后客户端的最大等待时间（单位为毫秒）
            poolConfig.setMaxWaitMillis(3000);
            this.pool = new JedisPool(poolConfig, "127.0.0.1", 6379);
        }
    }
    
    public void close() {
        //使用Jedis连接池
        if (this.pool != null) {
            this.pool.close();
        }
    }
}
