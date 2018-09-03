package com.dhm.publish.singleton;

/**
 * 四种单例模式测试
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/6/28 16:59
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingletonPatternTest{
    private static final HungrySingletonPattern hungrySingletonPattern = HungrySingletonPattern.getInstance();
    private static final LazySingletonPattern lazySingletonPattern = LazySingletonPattern.getInstance();
    private static final EnumSingletonPattern enumSingletonPattern = EnumSingletonPattern.getInstance();
    private static final AtomicBooleanSingletonPattern atomicBooleanSingletonPattern = AtomicBooleanSingletonPattern.getInstance();

    /**
     * 初始化原子操作AtomicBoolean为false，只要有一个为false，
     * 它就会置为true，只要不为true，我们单例模式都是可以正常运行的
     */
    private static final AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private static final int TEST_NUM = 1000000;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch1 = new CountDownLatch(TEST_NUM);
        CountDownLatch countDownLatch2 = new CountDownLatch(TEST_NUM);
        CountDownLatch countDownLatch3 = new CountDownLatch(TEST_NUM);
        CountDownLatch countDownLatch4 = new CountDownLatch(TEST_NUM);

        Long start1 = System.currentTimeMillis();
        for (int i = 0; i < TEST_NUM; i++) {
            executorService.execute(()->{
                try{
                    doHungrySingletonPatternTest();
                } finally {
                    countDownLatch1.countDown();
                }
            });
        }
        countDownLatch1.await();
        System.out.println("doHungrySingletonPatternTest cost time:"+(System.currentTimeMillis()-start1));

        Long start2 = System.currentTimeMillis();
        for (int i = 0; i < TEST_NUM; i++) {
            executorService.execute(()->{
                try{
                    doLazySingletonPatternTest();
                } finally {
                    countDownLatch2.countDown();
                }
            });
        }
        countDownLatch2.await();
        System.out.println("doLazySingletonPatternTest cost time:"+(System.currentTimeMillis()-start2));

        Long start3 = System.currentTimeMillis();
        for (int i = 0; i < TEST_NUM; i++) {
            executorService.execute(()->{
                try{
                    doEnumSingletonPatternTest();
                } finally {
                    countDownLatch3.countDown();
                }
            });
        }
        countDownLatch3.await();
        System.out.println("doEnumSingletonPatternTest cost time:"+(System.currentTimeMillis()-start3));

        Long start4 = System.currentTimeMillis();
        for (int i = 0; i < TEST_NUM; i++) {
            executorService.execute(()->{
                try{
                    doAtomicBooleanSingletonPatternTest();
                } finally {
                    countDownLatch4.countDown();
                }
            });
        }
        countDownLatch4.await();
        System.out.println("doAtomicBooleanSingletonPatternTest cost time:"+(System.currentTimeMillis()-start4));

        executorService.shutdown();

        if(!atomicBoolean.get()){
            System.out.println("单例模式测试通过！");
        }else{
            System.out.println("单例模式测试失败...");
        }
    }

    private static void doHungrySingletonPatternTest(){
        Boolean isEquals = hungrySingletonPattern.hashCode()==HungrySingletonPattern.getInstance().hashCode();
        if(atomicBoolean.compareAndSet(isEquals,true)){
            System.out.println("doHungrySingletonPatternTest fail");
        }
    }
    private static void doLazySingletonPatternTest(){
        Boolean isEquals = lazySingletonPattern.hashCode()==LazySingletonPattern.getInstance().hashCode();
        if(atomicBoolean.compareAndSet(isEquals,true)){
            System.out.println("doLazySingletonPatternTest fail");
        }
    }
    private static void doEnumSingletonPatternTest(){
        Boolean isEquals = enumSingletonPattern.hashCode()==EnumSingletonPattern.getInstance().hashCode();
        if(atomicBoolean.compareAndSet(isEquals,true)){
            System.out.println("doEnumSingletonPatternTest fail");
        }
    }
    private static void doAtomicBooleanSingletonPatternTest(){
        Boolean isEquals = atomicBooleanSingletonPattern.hashCode()==AtomicBooleanSingletonPattern.getInstance().hashCode();
        if(atomicBoolean.compareAndSet(isEquals,true)){
            System.out.println("doAtomicBooleanSingletonPatternTest fail");
        }
    }
}