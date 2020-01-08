package com.ocean.baseframework.thread;

public class ThreadDemo extends Thread{
    static int step = 100;
    //主要逻辑
    public void run() {
        System.out.println("线程主要逻辑");
        for (int i = 0; i < step; i++) {
            System.out.println("thread:" + i);
        }
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();

        System.out.println("main method");
        for (int i = 0; i < step; i++) {
            System.out.println("main:" + i);
        }

        // 实现runnable
        Thread thread = new Thread(new RunnableDemo());
        thread.start();
    }
}
