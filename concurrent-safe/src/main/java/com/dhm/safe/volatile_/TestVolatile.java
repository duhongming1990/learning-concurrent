package com.dhm.safe.volatile_;

public class TestVolatile {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            VolatileImpl volatileDemo = new VolatileImpl("线程" + i);
            new Thread(volatileDemo).start();

            SleepUtil.sleep(1000);
            volatileDemo.shutdown();
            SleepUtil.sleep(1000);
            volatileDemo.start();
            SleepUtil.sleep(1000);
            volatileDemo.shutdown();
            SleepUtil.sleep(1000);
            volatileDemo.start();
        }
    }
}

class SleepUtil {
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class VolatileImpl implements Runnable {
    private volatile boolean flag = true;
    private String description;

    public VolatileImpl(String description) {
        this.description = description;
    }

    int i = 0;

    @Override
    public void run() {
        while (true) {
            while (flag) {
                SleepUtil.sleep(100);
                System.out.println(description + "=======" + i + " Flag = " + isFlag());
                i++;
            }
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void start() {
        this.flag = true;
    }

    public void shutdown() {
        this.flag = false;
    }
}
