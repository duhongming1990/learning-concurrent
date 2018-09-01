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


