package com.sly.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO：多线程的线程池
 * 1 线程池创建线程，
 * 2 任务提交到线程池，线程池派发线程来处理任务
 * 3 当线程池的所有线程都在执行任务时，还有任务提交，就把新的任务放入任务队列中等待线程的执行
 */
public class MutilThreadPool {
    public static void main(String[] args) {
        //创建固定数量的线程池，此时有10个线程
        ExecutorService es = Executors.newFixedThreadPool(10);
        //打印线程池的状态
        System.out.println("Initial: "+es);
        //[Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0]
        // 创建了线程池，但是没有执行Runnable任务，所以全部是0
        /*
        pool size = 0 线程数量
        active threads = 0 活动线程数量（正在处理任务的线程数量）
        queued tasks = 0 队列任务
        completed tasks= 0 已经完成的任务
        */
        for (int i = 0; i < 30; i++) {
            es.execute(() -> {
                //每处理一次任务打印一次线程池状态
                System.out.println(Thread.currentThread().getName() + "|   Hello World   |" + es);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        es.shutdown();
    }
}
