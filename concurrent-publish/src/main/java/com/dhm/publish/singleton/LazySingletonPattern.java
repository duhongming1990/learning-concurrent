package com.dhm.publish.singleton;

/**
 * 懒汉单例模式
 * 写法简单，适合简单、小的对象创建；
 * 当创建一个非常复杂、非常大I/O操作、非常耗内存的对象时，不宜采用；
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/6/28 16:56
 */
public class LazySingletonPattern {

    //方法1 声明为常量
    private static final LazySingletonPattern instance = new LazySingletonPattern();

    //方法2 静态代码块
    /*private static LazySingletonPattern instance = null;
    static{
        instance = new LazySingletonPattern();
    }*/

    //构造方法私有
    private LazySingletonPattern(){}

    public static LazySingletonPattern getInstance() {
        return instance;
    }
}