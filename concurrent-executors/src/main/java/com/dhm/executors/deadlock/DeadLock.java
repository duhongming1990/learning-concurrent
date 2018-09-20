package com.dhm.executors.deadlock;

import com.dhm.executors.util.Threads;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/20 9:18
 *
 * 一个简单的死锁类
 * 当DeadLock类的对象flag==1时（td1），先锁定o1,睡眠500毫秒
 * 而td1在睡眠的时候另一个flag==0的对象（td2）线程启动，先锁定o2,睡眠500毫秒
 * td1睡眠结束后需要锁定o2才能继续执行，而此时o2已被td2锁定；
 * td2睡眠结束后需要锁定o1才能继续执行，而此时o1已被td1锁定；
 * td1、td2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁。
 */
@Slf4j
public class DeadLock implements Runnable {

    private int flag = 0;
    //静态对象是类的所有对象共享的
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        if (flag == 0) {
            log.info("flag:{}", flag);
            synchronized (o1) {
                Threads.sleep(500,TimeUnit.MILLISECONDS);
                synchronized(o2){
                    log.info("{}",flag);
                }
            }
        }
        if (flag == 1) {
            log.info("flag:{}", flag);
            synchronized (o2) {
                Threads.sleep(500,TimeUnit.MILLISECONDS);
                synchronized(o1){
                    log.info("{}",flag);
                }
            }
        }
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public static void main(String[] args) {

        DeadLock deadLock1 = new DeadLock();
        deadLock1.setFlag(0);
        DeadLock deadLock2 = new DeadLock();
        deadLock2.setFlag(1);

        //td1,td2都处于可执行状态，但JVM线程调度先执行哪个线程是不确定的。
        //td2的run()可能在td1的run()之前运行
        new Thread(deadLock1).start();
        new Thread(deadLock2).start();

    }
}