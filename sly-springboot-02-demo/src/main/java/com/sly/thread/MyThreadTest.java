package com.sly.thread;

public class MyThreadTest {
    public static void main(String[] args) {
        MyThread th1 = new MyThread();
        MyThread2 th2 = new MyThread2();
        th1.start();
        th2.start();
    }
}
