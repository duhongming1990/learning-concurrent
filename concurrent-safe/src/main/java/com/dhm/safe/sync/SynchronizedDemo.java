package com.dhm.safe.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/9/2 17:55
 */
@Slf4j
public class SynchronizedDemo {

    private static int count = 0;

    // 修饰一个方法
    public synchronized void add1(){
        count++;
    }

    // 修饰一个代码块
    public void add2(){
        synchronized(this){
            count++;
        }
    }

    // 修饰一个静态方法
    public synchronized static void add3(){
        count++;
    }

    // 修饰一个类
    public void add4(){
        synchronized (SynchronizedDemo.class){
            count++;
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(()->{
//               new SynchronizedDemo().add1();
//               new SynchronizedDemo().add2();
//               SynchronizedDemo.add3();
                new SynchronizedDemo().add4();
            });
        }
        executorService.shutdown();
        System.out.println("count = " + count);
    }
}
