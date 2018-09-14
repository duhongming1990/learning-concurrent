package com.dhm.executors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/14 16:55
 */
@Slf4j
public class ThreadPoolExample3 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(()->log.info("task:{}", index));
        }
        executorService.shutdown();
    }
}
