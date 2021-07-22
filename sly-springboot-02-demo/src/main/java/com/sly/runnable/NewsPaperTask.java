package com.sly.runnable;

/**
 * TODO:看报纸线程（用Runnable接口实现）
 *
 * @author leyuan
 * @date 2021/7/14 13:22
 */
public class NewsPaperTask implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "看了"+ i + "报纸");
        }
    }
}
