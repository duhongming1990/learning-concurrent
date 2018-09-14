package com.dhm.basic;

import com.dhm.tool.annotation.NotRecommend;
import com.dhm.tool.annotation.NotThreadSafe;
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
 * <p>
 * HashMap不是线程安全
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class HashMapExample {
    private static final int THREAD_TOTAL = 100;
    private static final int CLIENT_TOTAL = 5000;
    private static final Map<Integer, Integer> MAP = new HashMap(CLIENT_TOTAL);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_TOTAL);

        for (int i = 0; i < CLIENT_TOTAL; i++) {
            final Integer index = i;
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                putMap(index);
                semaphore.release();
            });
        }

        executorService.shutdown();
        log.info("map Size:{}", MAP.size());
    }

    private static void putMap(Integer index) {
        MAP.put(index, index);
    }
}
