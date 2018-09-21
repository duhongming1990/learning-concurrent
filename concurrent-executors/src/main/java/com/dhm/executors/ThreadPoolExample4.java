package com.dhm.executors;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/14 16:55
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {

//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
        executorService.schedule(() -> log.warn("schedule run"), 3, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(() -> log.warn("schedule run"), 1, 3, TimeUnit.SECONDS);
        executorService.shutdown();

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                log.warn("timer run");
//            }
//        }, new Date(), 5 * 1000);
    }
}
