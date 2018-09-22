package com.dhm.concurrent.threadlocal.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/22 11:51
 */
@Component
public class RedisClient {

    @Autowired
    private JedisPool jedisPool;

    public void set(String key,String value){
        Jedis jedis = jedisPool.getResource();
        jedis.set(key,value);
        jedis.close();
    }

    public String get(String key){
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }
}