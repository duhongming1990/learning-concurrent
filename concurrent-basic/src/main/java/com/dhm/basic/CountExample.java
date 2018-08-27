package com.dhm.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/8/27 21:30
 *
 * i++不是原子操作
 */
@Slf4j
public class CountExample {
    private static int i = 0;
    private static int threadTotal = 100;
    private static int clientTotal = 5000;

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphone = new Semaphore(threadTotal);
        for (int j = 0; j < clientTotal; j++) {
            executorService.submit(()->{
                try {
                    semaphone.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count();
                semaphone.release();
            });
        }
        executorService.shutdown();
        log.info("i:{}",i);
    }

    private static void count(){
        i++;
    }
}
