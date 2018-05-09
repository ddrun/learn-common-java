# 一 线程的创建方式：
    1 继承java.lang.Thread类（本质上Thread实现了Runnable接口）
       class MyThread extend Thread{
          @Override
          public void run(){
              
          }
       }
       
    2 实现Runnable接口
      class MyThread implements Runnable{
         @Override
         public void run(){
             
         }
      }
      
    3 实现Callable接口 
    
# 二 多线程基础
    1 Thread类亦实现了Runnable接口；
    2 接口方式优于继承方式，（1）接口方式耦合性更低；（2）接口方式创建的线程实例可以被
      多个线程共享；（3）继承方式创建线程，jvm会为其分配更多资源，成本更昂贵；
    3 线程调用从start()方法开始，但是start()调用后线程可能并不立即执行，而是使线程进入
      可运行状态，线程何时运行由cpu决定；
    4 main()中运行时至少存在2个线程，一是main主线程，二是GC线程；
    5 当程序中所有线程都是Demon线程时，程序即结束运行；
    6 一般情况下都是通过start()方法启动线程，而非run()方法，如果在程序中
      直接调用线程A的run()方法，则线程A的run()方法将在当前线程中执行，
      而非在A中执行；
    7 饥饿线程：永远得不到运行机会的线程，可能是线程优先级使用不当导致；
    8 线程上下文切换：一个线程被暂停，即被剥夺cpu的使用权，另外一个线程被选中开始
      或继续执行的过程即为线程的上下文切换； 
    9 java.lang.Thread\Runnbale
      java.object.wait\notify\notifyAll
      java.lang.Thread.sleep\interupt\join
    10 start()启动线程，而线程中的run()来完成具体的操作；
    11 sleep()与wait()的区别
      （1）两者处理机制不同；
       sleep()让线程暂停一段时间,sleet()不会释放对象锁，到时会自动变为可运行状态，等待cpu分配资源运行；
       wait()使线程等待，会释放对象锁，且需要其他线程主动调用notify()/notifyAll()进行唤醒；
      （2）两者父类不同；
       sleep()是Thread类的静态方法，wait()是Obeject类的方法；
      （3）两者的使用区域不同；
       sleep()可以在线程的任何地方使用；wait()必须在线程的同步方法或者同步语句块内执行；
    12 如何在多个线程之间共享数据？
    
    13 ThreadLocal的作用是什么？
    
    14 如何检测线程是否持有对象锁
      Thread.holdsLock(Object obj)方法可以检测对象obj是否被当前线程持有锁；
    
 # 三 Java多线程同步机制
    1 
 
 # 四 线程基础
    1 什么是线程安全
      通俗解释：代码无论在单线程下执行还是在多线程下执行，得到永远都是相同的结果，则
      代码就是线程安全的。
    2 Java中用到的线程调度算法是什么？
      抢占式的线程调度算法。
    3 Thread.sleep(0)的作用？
      由于Java采用的是抢占式线程调度算法，当某个线程抢占到cpu资源后，会占用cpu直到其执行完毕（一般情况下），
      可能会使得某些线程常常获取不到或很少获取cpu使用权的情况，为了平衡cpu资源的使用，
      使用Thread.sleep(0)暂停当前线程执行重新计算线程优先级进行cpu资源分配。
      Thread.sleep(0)的作用即：触发操作系统立即重新进行一次cpu竞争。
      
    4 线程能一直霸占cpu吗？
      不能。操作系统会监控线程占用cpu资源的情况，如果发现某个线程长时间霸占cpu，操作系统会强制该线程挂起。
      
 # 五 线程池
    1 什么是线程池？
       
    2 为什么要使用线程池？
     （1）避免频发的创建和销毁线程，达到线程对象的重用；
     （2）使用线程池可以灵活控制线程、并发的数目；
    3 
   