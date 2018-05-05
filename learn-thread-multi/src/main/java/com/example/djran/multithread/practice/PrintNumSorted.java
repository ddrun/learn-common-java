package com.example.djran.multithread.practice;

/**
 * @author d.djran@gmail.com
 * @function 多线程顺序打印ABC 10次
 * @time 2018/4/18
 */
public class PrintNumSorted implements Runnable {
    private String name;
    private Object prev;
    private Object self;

    private PrintNumSorted(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    count--;
                    self.notify();
                }
                if(count!=0){
                    try {
                        prev.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        PrintNumSorted pa = new PrintNumSorted("A", c, a);
        PrintNumSorted pb = new PrintNumSorted("B", a, b);
        PrintNumSorted pc = new PrintNumSorted("C", b, c);


        new Thread(pa).start();
        //确保按顺序A、B、C执行
        // sleep致使线程休眠，设定休眠时间结束后线程进入Runnable状态，sleep不释放对象锁
        // sleep阻塞当前线程，让出cpu使用权
        Thread.sleep(100);
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);
    }
}
