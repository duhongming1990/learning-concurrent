package com.dhm.safe;

import com.dhm.tool.annotation.Recommend;
import com.dhm.tool.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author duhongming
 * @Email 935720334@qq.com
 * @Date 2018/9/1 22:13
 */
@Slf4j
@ThreadSafe
@Recommend
public class AtomicBooleanDemo {
    public static void main(String[] args) {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        atomicBoolean.compareAndSet(false,true);
        atomicBoolean.compareAndSet(true,false);

        log.info("atomicBoolean = " + atomicBoolean);
    }
}
