# concurrent-strategy

## 6 线程安全策略

### 6.1 不可变对象

#### 6.1.1 不可变对象需要满足的条件
    对象创建以后其状态就不能修改
    对象所有域都是final类型
    对象是正确创建的（在对象创建期间，this引用没有逸出）

#### 6.1.2 final关键字：类、方法、变量
    修饰类：不能被继承
    修饰方法：锁定方法不被继承类修改
    修饰变量：基本数据类型变量不能被修改，引用类型变量不能指向新的引用

#### 6.1.3 不可变对象
    Collections.ummodifiableXXX:Collection/List/Set/Map...
    Guava:ImmutableXXX:Collection/List/Set/Map...
    
### 6.2 线程封闭

    Ad-hoc线程封闭：程序控制实现，最糟糕，忽略
    堆栈封闭：局部变量，无并发问题
    ThreadLocal线程封闭：特别好的封闭方法
    
### 6.3 线程不安全类与写法

#### 6.3.1 绝对线程不安全
    字符串拼接：StringBuilder
    时间日期：SimpleDateFormat
    普通容器：ArrayList、HashSet、HashMap
    先检查后执行（不是原子操作）
#### 6.3.2 相对线程不安全
    同步容器：Vector、Stack、Hashtable
    同步工具：Collections.synchronizedXXX()
    
#### 6.3.3 线程安全
    字符串拼接：StringBuffer
    时间日期：JodaTime
    J.U.C并发容器：：
         CopyOnWriteArrayList(读多写少的场景)
         CopyOnWriteArraySet（HashSet）
         ConcurrentSkipListSet（TreeSet）
         ConcurrentHashMap(HashMap)
         ConcurrentSkipListMap(TreeMap)
    
#### 6.3.4 决策
    在堆栈封闭中，为了性能 使用线程不安全； 
    在多线程中，为了安全性 使用线程安全；

