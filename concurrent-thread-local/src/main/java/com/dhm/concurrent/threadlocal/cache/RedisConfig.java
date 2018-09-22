package com.dhm.concurrent.threadlocal.cache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/22 11:45
 */
@Configuration
public class RedisConfig {

    @Bean("jedisPool")
    public JedisPool jedisPool( @Qualifier("jedis.pool.config") JedisPoolConfig jedisPoolConfig,
                                @Value("${spring.redis.host}") String host,
                                @Value("${spring.redis.port}") int port,
                                @Value("${spring.redis.timeout}") int timeout,
                                @Value("${spring.redis.password}") String password){
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,password);
        return jedisPool;
    }

    @Bean(name = "jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig(
            @Value("${jedis.pool.config.maxTotal}") int maxTotal,
            @Value("${jedis.pool.config.maxIdle}") int maxIdle,
            @Value("${jedis.pool.config.maxWaitMillis}") int maxWaitMillis,
            @Value("${jedis.pool.config.testOnBorrow}") boolean testOnBorrow){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        return config;
    }
}