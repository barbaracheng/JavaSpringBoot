package com.sly.runnable;


public class BreakFastTask implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            //Thread.currentThread().getName() 获取当前线程的名称
            System.out.println(Thread.currentThread().getName() +"早餐吃了"+i+"包子");
        }
    }
}
