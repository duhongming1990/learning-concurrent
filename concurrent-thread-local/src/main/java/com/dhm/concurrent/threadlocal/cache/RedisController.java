package com.dhm.concurrent.threadlocal.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/22 11:53
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired RedisClient redisClient;

    @RequestMapping("/set")
    public String set(String key,String value){
        redisClient.set(key,value);
        return "SUCCESS";
    }

    @RequestMapping("/get")
    public String get(String key){
        return redisClient.get(key);
    }
}