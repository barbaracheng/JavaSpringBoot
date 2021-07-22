package com.sly.thread;

public class MyThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("MyThread2--->" + i);
        }
    }
}
