package com.sly.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {
    public static void main(String[] args) {
        //创建一个单线程的线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            //执行任务
            es.execute(() -> System.out.println(Thread.currentThread().getName() + "--->" + "Hello World."));
        }
        // 销毁线程
        /*
            在循环体内部“销毁线程”会抛出RejectedExecutionException异常（拒绝执行任务异常）
            第一次循环执行任务体成功之后，线程池销毁了。第二次再执行就会出现该异常(因为线程池被销毁了)
        */
        es.shutdown();
    }
}
