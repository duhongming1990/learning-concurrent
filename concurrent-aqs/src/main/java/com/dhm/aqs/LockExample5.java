package com.dhm.aqs;

import com.dhm.tool.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/13 9:36
 */
@Slf4j
@ThreadSafe
public class LockExample5 {

    /**
     * 请求总数
     */
    public static int CLIEN_TTOTAL = 5000;

    /**
     * 同时并发执行的线程数
     */
    public static int THREAD_TOTAL = 200;

    public static int count = 0;

    private final static StampedLock LOCK = new StampedLock();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        final CountDownLatch countDownLatch = new CountDownLatch(CLIEN_TTOTAL);
        for (int i = 0; i < CLIEN_TTOTAL; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        long stamp = LOCK.writeLock();
        try {
            count++;
        } finally {
            LOCK.unlock(stamp);
        }
    }
}
