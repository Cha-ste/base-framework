package com.ocean.baseframework.thread;

public class RunnableDemo implements Runnable {
    static int step = 100;
    @Override
    public void run() {
        System.out.println("runnable 逻辑");
        for (int i = 0; i < step; i++) {
            System.out.println("runnable:" + i);
        }
    }
}
