package com.dhm.safe;

import com.dhm.tool.annotation.Recommend;
import com.dhm.tool.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.LongAdder;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/9/1 17:03
 */
@Slf4j
@ThreadSafe
@Recommend
public class LongAdderDemo {
    public static void main(String[] args) {

        LongAdder longAdder = new LongAdder();

        //++i
        longAdder.increment();
        log.info("increment = " + longAdder);

        //--i
        longAdder.decrement();
        log.info("decrement = " + longAdder);

        //i
        longAdder.longValue();
        log.info("longValue = " + longAdder);
    }
}
