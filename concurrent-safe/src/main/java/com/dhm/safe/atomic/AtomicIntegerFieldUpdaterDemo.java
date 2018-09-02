package com.dhm.safe.atomic;

import com.dhm.tool.annotation.Recommend;
import com.dhm.tool.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/9/1 22:04
 */
@Slf4j
@ThreadSafe
@Recommend
public class AtomicIntegerFieldUpdaterDemo {

    //必须volatile修饰，不能使static修饰才可以
    @Getter
    private volatile int count = 100;

    public static void main(String[] args) {

        AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterDemo> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterDemo.class,"count");

        AtomicIntegerFieldUpdaterDemo atomicIntegerFieldUpdaterDemo = new AtomicIntegerFieldUpdaterDemo();
//        atomicIntegerFieldUpdater.compareAndSet(atomicIntegerFieldUpdaterDemo,0,1);//1
//        atomicIntegerFieldUpdater.compareAndSet(atomicIntegerFieldUpdaterDemo,1,3);//3
//        atomicIntegerFieldUpdater.compareAndSet(atomicIntegerFieldUpdaterDemo,2,5);//no
//        atomicIntegerFieldUpdater.compareAndSet(atomicIntegerFieldUpdaterDemo,3,7);//7
//        atomicIntegerFieldUpdater.compareAndSet(atomicIntegerFieldUpdaterDemo,4,9);//no

        if (atomicIntegerFieldUpdater.compareAndSet(atomicIntegerFieldUpdaterDemo, 100, 120)) {
            log.info("update success 1, {}", atomicIntegerFieldUpdaterDemo.getCount());
        }

        if (atomicIntegerFieldUpdater.compareAndSet(atomicIntegerFieldUpdaterDemo, 100, 120)) {
            log.info("update success 2, {}", atomicIntegerFieldUpdaterDemo.getCount());
        } else {
            log.info("update failed, {}", atomicIntegerFieldUpdaterDemo.getCount());
        }
    }
}
