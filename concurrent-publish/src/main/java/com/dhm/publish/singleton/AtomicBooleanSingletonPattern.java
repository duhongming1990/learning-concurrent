package com.dhm.publish.singleton;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 原子布尔变量单例模式
 * 呵呵，纯属娱乐！
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/6/28 16:58
 */

public class AtomicBooleanSingletonPattern {

    private static final AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private AtomicBooleanSingletonPattern(){}

    private static AtomicBooleanSingletonPattern intance = null;

    public static AtomicBooleanSingletonPattern getInstance() {
        if(atomicBoolean.compareAndSet(false,true)){
            intance = new AtomicBooleanSingletonPattern();
        }
        return intance;
    }
}