package com.ocean.baseframework.thread;

public class RunnableDemo implements Runnable {
    @Override
    public void run() {
        System.out.println("runnable 逻辑");
        for (int i = 0; i < 20; i++) {
            System.out.println("runnable:" + i);
        }
    }
}
