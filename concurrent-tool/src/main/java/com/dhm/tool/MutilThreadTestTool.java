package com.dhm.tool;

import com.dhm.tool.annotation.Recommend;
import com.dhm.tool.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/8/29 20:52
 */

@Slf4j
@ThreadSafe
@Recommend
public class MutilThreadTestTool {
    private static final int threadTotal = 200;
    private static final int clientTotal = 5000;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.submit(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            });
            countDownLatch.countDown();
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("final count:{}",atomicInteger.get());
    }

    private static void add(){
        atomicInteger.incrementAndGet();
    }
}
