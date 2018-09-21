package com.dhm.safe.volatile_;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/9/2 17:55
 */
@Slf4j
public class VolatileDemo {

    //volatile不具有原子性，常用于状态的可见：true or false
    private static volatile int count = 0;

    public static void add() {
        count++;
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(() -> {
                add();
            });
        }

        executorService.shutdown();
        System.out.println("count = " + count);
    }
}
