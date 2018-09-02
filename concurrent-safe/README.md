# concurrent-tool

## 4 线程安全性
[返回主目录](../README.md)

### 4.1 atomic包

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

### 4.2 锁

  ● 内置锁synchronized：依赖jvm
  
    修饰代码块：大括号括起来的代码，作用于调用的对象。
    修饰方法：整个方法，作用于调用的对象。
    修饰静态方法：整个静态方法，作用于所有对象。
    修饰类：括号括起来的部分，作用于所有对象。
    
  ● 显式锁Lock：依赖特殊的CPU指令，ReentrantLock

### 4.3 原子性对比
    synchronized：不可中断锁，适合竞争不激烈，可读性好。
    Lock：可中断锁，多样化同步，竞争激烈时能维持常态。
    Atomic：竞争激烈时能维持常态，比Lock性能好；只能同步一个值。
    LongAdder：非volatile的64位数值变量不是原子（long、double），所以在竞争激烈时比AtomicLong性能好。

