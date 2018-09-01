package com.dhm.safe;

import com.dhm.tool.annotation.Recommend;
import com.dhm.tool.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/9/1 17:00
 */
@Slf4j
@ThreadSafe
@Recommend
public class AtomicLongDemo {
    public static void main(String[] args) {

        AtomicLong atomicLong = new AtomicLong(0);

        //++i
        atomicLong.incrementAndGet();
        log.info("incrementAndGet = " + atomicLong);

        //i++
        atomicLong.getAndIncrement();
        log.info("incrementAndGet = " + atomicLong);

        //--i
        atomicLong.decrementAndGet();
        log.info("incrementAndGet = " + atomicLong);

        //i--
        atomicLong.getAndDecrement();
        log.info("incrementAndGet = " + atomicLong);

        //i
        atomicLong.get();
        log.info("incrementAndGet = " + atomicLong);
    }
}
