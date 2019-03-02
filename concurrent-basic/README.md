# concurrent-basic

## 2 并发基础
[返回主目录](../README.md)

### 2.1 CPU多级缓存-缓存一致性
CPU多级缓存：CPU的频率太快了，快到主存跟不上。（cpu->cache->memory）
![PU多级缓存](src/main/resources/static/4.jpg)

### 2.2 CPU多级缓存-乱序执行优化
处理器为提高运算速度而做出违背代码原有顺序的优化。

### 2.3 JAVA内存模型

#### 2.3.1 JMM
![](src/main/resources/static/5.jpg)
![](src/main/resources/static/6.jpg)
![](src/main/resources/static/7.jpg)
![](src/main/resources/static/8.jpg)

#### 2.3.2 同步操作规则
![](src/main/resources/static/9.jpg)

基本规则：
1. 不允许Read和Load、Store和Write操作之一单独出现。

2. 不允许一个线程丢弃它最近的Assign操作-同步到主内存。

3. 不允许一个线程无原因地没有Assign操作-同步到主内存。

4. use、store操作之前，必须先执行过了assign和load操作。

5. 一个变量在同一个时刻只允许一条线程对其进行lock操作，可以多次lock，但也需要相同次数的unlock。---synchronized有序性

6. lock操作会清空工作内存中此变量的值，在使用这个变量前，需要重新执行load或者assign操作初始化变量的值。

7. 没有被lock，就不允许对它执行unlock。

8. unlock操作之前，必须执行store、write操作。---synchronized的可见性

volatile规则：

1. 每次使用前都必须从主内存刷新最新值
2. 每次修改后都必须同步回主内存中
3. 不会被指令重排序优化，保证代码得执行顺序与程序的顺序相同

### 2.4 并发的优势与风险
![](src/main/resources/static/10.jpg)

