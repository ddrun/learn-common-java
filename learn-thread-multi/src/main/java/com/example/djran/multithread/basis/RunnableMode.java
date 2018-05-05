package com.example.djran.multithread.basis;

/**
 * @author d.djran@gmail.com
 * @function 简单线程实现方式：实现java.lang.Runnable接口
 * @time 2018/4/25
 */
public class RunnableMode implements Runnable{

    private String threadName;

    public RunnableMode(String name){
        this.threadName=name;
    }

    @Override
    public void run() {
        System.out.println("Current Thread:"+this.threadName);
    }

    public static void main(String [] args){
        System.out.println("Current Thread:"+Thread.currentThread().getName());
        Thread thread=new Thread(new RunnableMode("myThread"));
        thread.start();
    }
}
