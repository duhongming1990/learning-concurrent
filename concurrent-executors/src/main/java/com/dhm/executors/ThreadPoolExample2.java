package com.dhm.executors;

import com.dhm.executors.util.Threads;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/14 16:55
 */
@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newFixedThreadPool(3);

        ThreadFactory threadFactory = Threads.createThreadFactory("newFixedThreadPool");
        ExecutorService executorService = new ThreadPoolExecutor(
                3, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue(), threadFactory);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> log.info("task:{}", index));
        }
        executorService.shutdown();
    }
}
