package com.dhm.basic;

import com.dhm.tool.annotation.NotRecommend;
import com.dhm.tool.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/8/27 21:30
 * <p>
 * i++不是原子操作
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class CountExample {
    private static int i = 0;
    private static final int THREAD_TOTAL = 100;
    private static final int CLIENT_TOTAL = 5000;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_TOTAL);

        for (int j = 0; j < CLIENT_TOTAL; j++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count();
                semaphore.release();
            });
        }

        executorService.shutdown();
        log.info("i:{}", i);
    }

    private static void count() {
        i++;
    }
}
