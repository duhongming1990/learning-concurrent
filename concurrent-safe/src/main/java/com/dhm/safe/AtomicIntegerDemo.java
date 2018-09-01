package com.dhm.safe;

import com.dhm.tool.annotation.Recommend;
import com.dhm.tool.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/9/1 16:54
 */
@Slf4j
@ThreadSafe
@Recommend
public class AtomicIntegerDemo {
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(0);

        //++i
        atomicInteger.incrementAndGet();
        log.info("incrementAndGet = " + atomicInteger);

        //i++
        atomicInteger.getAndIncrement();
        log.info("getAndIncrement = " + atomicInteger);

        //--i
        atomicInteger.decrementAndGet();
        log.info("decrementAndGet = " + atomicInteger);

        //i--
        atomicInteger.getAndDecrement();
        log.info("getAndDecrement = " + atomicInteger);

        //i
        atomicInteger.get();
        log.info("get = " + atomicInteger);
    }
}
