package com.dhm.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/8/27 21:30
 *
 * HashMap不是线程安全
 */
@Slf4j
public class HashMapExample {
    private static int threadTotal = 100;
    private static int clientTotal = 5000;
    private static final Map<Integer,Integer> map = new HashMap(clientTotal);

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphone = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            final Integer index = i;
            executorService.submit(()->{
                try {
                    semaphone.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                putMap(index);
                semaphone.release();
            });
        }
        executorService.shutdown();
        log.info("map Size:{}",map.size());
    }

    private static void putMap(Integer index){
        map.put(index,index);
    }
}
