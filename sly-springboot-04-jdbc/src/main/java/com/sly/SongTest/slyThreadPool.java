package com.sly.SongTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO:自定义线程池
 * public ThreadPoolExecutor(int corePoolSize,
 *                               int maximumPoolSize,
 *                               long keepAliveTime,
 *                               TimeUnit unit,
 *                               BlockingQueue<Runnable> workQueue) {}
 * corePoolSize: 线程池的核心线程大小(核心线程数量)
 * maximumPoolSize：线程池的最大线程大小(数量)
 * keepAliveTime: 非核心线程(maximumPoolSize-corePoolSize)存活的有效时间，
 * unit:参数3的单位
 * workQueue：线程池的工作队列
 *
 * 工具方法只有一个参数：表示核心线程大小和最大线程大小是一致的
 * 如果核心线程大小和最大线程大小一致，那么参数2，参数3，参数4都是哑巴参数
 * 注意：new LinkedBlockingQueue<Runnable>()此时队列是无界限的队列。
 * @author leyuan
 * @date 2021/7/14 14:58
 */
public class slyThreadPool {
    //线程池对象
    private static ThreadPoolExecutor pool;

    /**
     * 自定义线程池原则：
     * 1 核心线程数和最大线程数保持一致
     * 2 阻塞队列一定要指定容量
     * 3 创建线程池对象一定要判断，非空才创建
     * @return 线程池对象
     */
    //创建线程池
    public static ThreadPoolExecutor createPool() {
        if (pool == null) {
            pool = new ThreadPoolExecutor(
                    500, 500, 0L,
                    TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(30000));
        }
        return pool;
    }
}
