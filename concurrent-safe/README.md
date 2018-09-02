# concurrent-tool

## 4 线程安全性
[返回主目录](../README.md)

### 4.1 原子性

#### 4.1.1 atomic包

  ● AtomicXXX：CAS-Unsafe.compareAndSwapXXX
  
  ```java
  public final long incrementAndGet() {
      return unsafe.getAndAddLong(this, valueOffset, 1L) + 1L;
  }
  
  public final long getAndAddLong(Object var1, long var2, long var4) {
      long var6;
      do {
          var6 = this.getLongVolatile(var1, var2);
      } while(!this.compareAndSwapLong(var1, var2, var6, var6 + var4));
      return var6;
  }
  ```
  
  ● AtomicLong、LongAdder:LongAdder在AtomicLong的基础上将单点的更新压力分散到各个节点，
  在低并发的时候通过对base的直接更新可以很好的保障和AtomicLong的性能基本保持一致，
  而在高并发的时候通过分散提高了性能。 
  缺点是LongAdder在统计的时候如果有并发更新，可能导致统计的数据有误差。
  
  ● AtomicReference、AtomicIntegerFieldUpdater
  
  ● AtomicStampReference：解决CAS的ABA问题
  
### 示例代码

  ```java
    
    AtomicInteger atomicInteger = new AtomicInteger(0);
    //++i
    atomicInteger.incrementAndGet();
    //i++
    atomicInteger.getAndIncrement();
    //--i
    atomicInteger.decrementAndGet();
    //i--
    atomicInteger.getAndDecrement();
    //i
    atomicInteger.get();



    AtomicLong atomicLong = new AtomicLong(0);    
    //++i
    atomicLong.incrementAndGet();
    //i++
    atomicLong.getAndIncrement();
    //--i
    atomicLong.decrementAndGet();
    //i--
    atomicLong.getAndDecrement();
    //i
    atomicLong.get();


    LongAdder longAdder = new LongAdder();
    //++i
    longAdder.increment();
    //--i
    longAdder.decrement();
    //i
    longAdder.longValue();
    
    
    AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    atomicBoolean.compareAndSet(false,true);
    atomicBoolean.compareAndSet(true,false);
    
 
  ```

#### 4.1.2 锁

  ● 内置锁synchronized：依赖jvm
  
    修饰代码块：大括号括起来的代码，作用于调用的对象。
    修饰方法：整个方法，作用于调用的对象。
    修饰静态方法：整个静态方法，作用于所有对象。
    修饰类：括号括起来的部分，作用于所有对象。
    
  ● 显式锁Lock：依赖特殊的CPU指令，ReentrantLock

#### 4.1.3 原子性对比
    synchronized：不可中断锁，适合竞争不激烈，可读性好。
    Lock：可中断锁，多样化同步，竞争激烈时能维持常态。
    Atomic：竞争激烈时能维持常态，比Lock性能好；只能同步一个值。
    LongAdder：非volatile的64位数值变量不是原子（long、double），所以在竞争激烈时比AtomicLong性能好。


### 4.2 可见性

导致共享变量在线程间不可见的原因？

  ● 线程交叉执行。
  
  ● 重排序结合线程交叉执行。
  
  ● 共享变量更新后的值没有在工作内存与主内存间及时更新。

#### 4.2.1 synchronized 
线程解锁前，必须把共享变量的最新值刷新到主内存；线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存重新读取最新的值（注意：加锁和解锁是同一把锁）

#### 4.2.2 volatile 
内存屏障和禁止重排序优化来实现：对写操作，会在写操作加入一条store屏障指令，将本地内存中的共享变量值刷新到主内存；对读操作，会在读操作前加入一条load屏障指令，从主内存中读取共享变量。不能保证原子性。


### 4.3 有序性

#### 4.3.1 保证有序性
volatile、synchronized、Lock

#### 4.3.2 Happens-before原则？
  1. 程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作
  2. 锁定规则：一个unLock操作先行发生于后面对同一个锁的lock操作。
  3. volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作
  4. 传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C
  5. 线程启动规则：Thread对象的start()方法先行发生于此线程的每一个动作。
  6. 线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生。
  7. 线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行。
  8. 对象终结规则：一个对象的初始化完成先行发生于它的finalize()方法的开始。