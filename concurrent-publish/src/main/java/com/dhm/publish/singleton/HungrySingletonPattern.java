package com.dhm.publish.singleton;

/**
 * 饿汉单例模式（volatile+双重检测机制）
 * 适合绝大多数情况；
 * 编写复杂，由于加锁，对性能有一定影响；
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/6/28 16:56
 * 创建对象过程：
 * 1、memory = allocate()分配对象的内存空间
 * 2、ctorInstance() 初始化对象
 * 3、instance = memory 设置instance指向刚分配的内存
 *
 * 2和3会发生指令重排
 *
 * volatile作用：内存可见+防止指令重排
 */
public class HungrySingletonPattern {

    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private volatile static HungrySingletonPattern instance = null;

    /* 私有构造方法，防止被实例化 */
    private HungrySingletonPattern(){}

    /* 因为我们只需要在创建类的时候进行同步，所以只要将创建和getInstance()分开，单独为创建加synchronized关键字 */
    private static synchronized void syncInit() {//同步锁，静态方法锁定作用域是对象
        if (instance == null) {
            instance = new HungrySingletonPattern();
        }
    }

    /* 静态的工厂方法，创建实例 */
    public static HungrySingletonPattern getInstance(){
        if(instance == null){//双重检测机制
            syncInit();
        }
        return instance;
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve(){
        return instance;
    }
}