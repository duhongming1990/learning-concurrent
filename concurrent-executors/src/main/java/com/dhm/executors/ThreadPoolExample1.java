package com.dhm.executors;

import com.dhm.executors.util.Threads;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/14 16:52
 */
@Slf4j
public class ThreadPoolExample1 {

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadFactory threadFactory = Threads.createThreadFactory("newCachedThreadPool");

        ExecutorService executorService = new ThreadPoolExecutor(
                0, 2147483647,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue(), threadFactory);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> log.info("task:{}", index));
        }
        executorService.shutdown();
    }
}
