package com.sly.threadpool;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * TODO: 阻塞队列LinkedBlockingQueue
 * 阻塞有两种：
 * 1 当队列为空的情况下，进行take()操作，此时程序不会结束一直阻塞(等待)
 * 2 当队列容量已满，进行put()操作，此时程序不会结束一直阻塞(等待)
 *非阻塞队列：当某个队列为空时，进行get()等相关操作，程序要么会抛出异常，要么会返回null，总之就是程序会继续执行，直到结束。
 * 阻塞：程序不会继续执行而是一直等待。
 *
 * @author leyuan
 * @date 2021/7/14 14:47
 */
public class LinkedBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        //创建一个阻塞式队列
        /**
         * 如果使用无参构造方法创建一个LinkedBlockingQueue对象，它是一个无界限的阻塞队列
         * 工作中最好不要使用无参构造方法创建LinkedBlockingQueue对象
         */
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(1);
        //取出队列的一个元素，由于队列中没有元素，所以程序处于阻塞状态，不会立即结束
        queue.take();
        System.out.println(queue);

    }
}
