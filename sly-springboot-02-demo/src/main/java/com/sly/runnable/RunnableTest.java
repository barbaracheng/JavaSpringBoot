package com.sly.runnable;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/14 13:24
 */
public class RunnableTest {
    public static void main(String[] args) {
        //创建两个任务
        BreakFastTask breakFastTask = new BreakFastTask();
        NewsPaperTask newsPaperTask = new NewsPaperTask();
        //任务注入到线程
        Thread th1 = new Thread(breakFastTask);
        Thread th2 = new Thread(newsPaperTask);
        //启动线程
        th1.start();
        th2.start();
    }
}
