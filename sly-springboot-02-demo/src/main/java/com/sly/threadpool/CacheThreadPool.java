package com.sly.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO:带缓冲区的线程池
 *
 * @author leyuan
 * @date 2021/7/15 10:50
 */
public class CacheThreadPool {
    public static void main(String[] args) {
        ExecutorService ese = Executors.newCachedThreadPool();
    }
}
