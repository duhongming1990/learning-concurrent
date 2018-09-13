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

### 7.2 J.U.C之AQS-CountDownLatch（闭锁）
```java

CountDownLatch类只提供了一个构造器：
    public CountDownLatch(int count) {  };  //参数count为计数值
    //CountDownLatch最好用final修饰
    final CountDownLatch countDownLatch = new CountDownLatch(1000);
    
然后下面这4个方法是CountDownLatch类中最重要的方法：
    public void await() throws InterruptedException { };   //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };  //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
    public void countDown() { };  //将countDown()放入finally代码块中执行,将count值减1
    public int getCount() { };  //得到count的值
```

### 7.3 J.U.C之AQS-Semaphore（信号量）
```java

    
提供了2个构造器：
    //Semaphore最好用final修饰
    public Semaphore(int permits) {          //参数permits表示许可数目，即同时可以允许多少线程进行访问
        sync = new NonfairSync(permits);
    }
    public Semaphore(int permits, boolean fair) {    //这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
        sync = (fair)? new FairSync(permits) : new NonfairSync(permits);
    }

Semaphore类中比较重要的几个方法，首先是acquire()、release()方法：
    //acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可。
    //release()用来释放许可。注意，在释放许可之前，必须先获获得许可。
    public void acquire() throws InterruptedException {  }     //获取一个许可
    public void acquire(int permits) throws InterruptedException { }    //获取permits个许可
    public void release() { }          //释放一个许可
    public void release(int permits) { }    //释放permits个许可
    
这4个方法都会被阻塞，如果想立即得到执行结果，可以使用下面几个方法：
    public boolean tryAcquire() { };    //尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
    public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };  //尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
    public boolean tryAcquire(int permits) { }; //尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
    public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { }; //尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false

另外还可以通过availablePermits()方法得到可用的许可数目。

```
### 7.4 J.U.C之AQS-CyclicBarrier（回环栅栏）
```java
    
CyclicBarrier提供2个构造器：
    // 参数parties指让多少个线程或者任务等待至barrier状态；
    // 参数barrierAction为当这些线程都达到barrier状态时会执行的内容。
    public CyclicBarrier(int parties, Runnable barrierAction) {
    }
     
    public CyclicBarrier(int parties) {
    }

然后CyclicBarrier中最重要的方法就是await方法，它有2个重载版本：
    public int await() throws InterruptedException, BrokenBarrierException { };
    public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException { };
    第一个版本比较常用，用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
    第二个版本是让这些线程等待至一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务。

CyclicBarrier可重用，而CountDownLatch无法进行重复使用。
```

下面对上面说的三个辅助类进行一个总结：

1）CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：

CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；

而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；

另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。

2）Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。

参见-Java并发编程：CountDownLatch、CyclicBarrier和 Semaphore:

http://www.importnew.com/21889.html

### 7.5 J.U.C之AQS-ReentrantLock与锁

#### 7.5.1 ReentrantLock独有的功能
    可指定是公平锁还是非公平锁
    提供了一个Condition类，可以分组唤醒需要唤醒的线程
    提供能够中断等待锁的线程的机制，lock.lockInterruptibly()

#### 7.5.2 轮询锁和定时锁
    tryLock方法实现，避免死锁发生。
    对于公平锁而言，tryLock还是会插队。
#### 7.5.3 可中断锁
    lock.lockInterruptibly()
#### 7.5.4 非公平锁（默认）与公平锁
    在持有锁的时间相对较长，公平锁上，线程将按照他们发出请求的顺序来获得锁。
    在竞争非常激烈的情况下，非公平锁，则允许插队。
    非公平锁的性能高于公平锁的性能，高出两个数量级。
#### 7.5.5 读写锁
    ReentrantReadWriteLock一个资源可以被多个读操作访问，或者被一个写操作访问，但两者不能同时进行。
### 7.6 StampedLock
### 7.7 Condition  