package com.dhm.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/7 9:54
 */
public class A {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        cyclicBarrier.await();
        cyclicBarrier.await(1,TimeUnit.SECONDS);
        cyclicBarrier.reset();
    }
}