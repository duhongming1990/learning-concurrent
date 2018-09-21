package com.dhm.safe.atomic;

import com.dhm.tool.annotation.Recommend;
import com.dhm.tool.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/9/1 21:46
 */
@Slf4j
@ThreadSafe
@Recommend
public class AtomicReferenceDemo {
    public static void main(String[] args) {

        AtomicReference atomicReference = new AtomicReference(0);

        atomicReference.compareAndSet(0, 1);//1
        atomicReference.compareAndSet(1, 3);//3
        atomicReference.compareAndSet(2, 5);//no
        atomicReference.compareAndSet(3, 7);//7
        atomicReference.compareAndSet(4, 9);//no

        log.info("atomicReference = " + atomicReference);
    }
}
