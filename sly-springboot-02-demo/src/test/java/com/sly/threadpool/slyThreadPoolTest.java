package com.sly.threadpool;

import com.sly.utils.slyThreadPool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO:测试工具方法类
 *
 * @author leyuan
 * @date 2021/7/14 15:27
 */
public class slyThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor pool1 = slyThreadPool.createPool();
        ThreadPoolExecutor pool2 = slyThreadPool.createPool();
        System.out.println(pool1);
        System.out.println(pool2);
        System.out.println(pool1 == pool2);
    }
}
