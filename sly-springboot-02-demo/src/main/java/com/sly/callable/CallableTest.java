package com.sly.callable;

import com.sly.utils.slyThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO: 定义一个线程池，判断1900~2000年之间的闰年
 * 每个年份都开启一个线程来判断，当前年份时闰年返回true，否则返回false
 * 步骤：
 * 1 定义线程池
 * 2 将任务提交到线程池，该任务实现了Callable接口
 * 3 获取执行结果
 * 4 打印执行结果
 * @author leyuan
 * @date 2021/7/14 16:43
 */
public class CallableTest {
    public static void main(String[] args) {
        //创建线程池
        ThreadPoolExecutor pool = slyThreadPool.createPool();
        try {
            for (int i = 1900; i <= 2000; i++) {
                //外部类的局部变量传入到匿名内部类最好使用final关键字修饰
                final int year = i;
                // 判断是否是闰年的操作，放在Callable里面
                // <Boolean>表示当前任务返回Boolean类型
                // Future表示Callable接口里面的任务可能立马返回结果，也可能在未来的某一时间段返回结果
                // 注意：Future里面的泛型类型必须跟Callable里面的泛型类型一致，表示结果是一个Boolean类型
                Future<Boolean> result = pool.submit(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        // 实现Callable接口的任务可以有返回值，返回类型跟泛型一致
                        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
                    }
                });
                //获取Future对象的结果
                final Boolean finalResult = result.get();
                System.out.println(year + "--->" + finalResult);
                }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
}
