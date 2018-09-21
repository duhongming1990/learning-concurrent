package com.dhm.publish.singleton;

/**
 * 枚举单例模式
 * 终极解决方案，请使用！
 *
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/6/28 16:57
 */
public class EnumSingletonPattern {

    private EnumSingletonPattern() {
    }

    public static EnumSingletonPattern getInstance() {
        return Singleton.INSTANCE.getIntance();
    }

    private enum Singleton {

        INSTANCE;

        private EnumSingletonPattern instance;

        //JVM保证这个只执行一次
        Singleton() {
            instance = new EnumSingletonPattern();
        }

        public EnumSingletonPattern getIntance() {
            return instance;
        }
    }
}