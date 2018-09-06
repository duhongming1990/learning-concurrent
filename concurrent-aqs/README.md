# concurrent-aqs

## 7 J.U.C之AQS
[返回主目录](../README.md)

### 7.1 AQS
    使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础框架
    利用了一个int类型表示状态
    使用方法是继承
    子类通过继承并通过实现它的方法管理其状态{acquire和release}的方法操作状态
    可以通过实现排它锁和共享锁模式（独占、共享）

![](src/main/resources/static/11.jpg)
### 7.2 J.U.C之AQS
    J.U.C之AQS-CountDownLatch
    J.U.C之AQS-Semaphore
    J.U.C之AQS-CyclicBarrier
    J.U.C之AQS-ReentrantLock与锁