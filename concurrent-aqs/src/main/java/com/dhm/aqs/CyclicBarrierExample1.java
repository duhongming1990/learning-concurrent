package com.dhm.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/7 10:27
 */
@Slf4j
public class CyclicBarrierExample1 {
    private static int LIMIT = 1000;
    private static int NUM =10;
    private final static CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(NUM);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < LIMIT; i++) {
            final int num = i;
            Thread.sleep(1000);
            executorService.submit(()->{
                try {
                    race(num);
                } catch (Exception e) {
                    log.error("exception:",e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        CYCLIC_BARRIER.await();
        log.info("{} continue", threadNum);
    }
}