package com.example.djran.multithread.basis;

/**
 * @author d.djran@gmail.com
 * @function 简单线程实现方式：继承java.lang.Thread
 * @time 2018/4/24
 */
public class ThreadMode extends Thread {

    private String threadName;

    public ThreadMode(String name){
        this.threadName=name;
    }

    @Override
    public void run() {
        System.out.println("Current Thread:"+Thread.currentThread().getName()+" "+this.threadName);
    }

    public static void main(String [] args){
        System.out.println("Current Thread:"+Thread.currentThread().getName());
        ThreadMode myThread=new ThreadMode("myThread");
        myThread.start();

        ThreadMode myThread1=new ThreadMode("myThread1");
        myThread1.start();
    }
}
