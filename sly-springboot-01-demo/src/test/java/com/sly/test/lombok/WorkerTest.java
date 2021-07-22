package com.sly.test.lombok;

import com.sly.entities.Worker;
import org.junit.Test;

public class WorkerTest {
        @Test
        public void workerTest() {
            //调用无参构造方法
            Worker worker = new Worker();
            System.out.println(worker);
            //调用get和set方法
            worker.setId(111111);
            worker.setWokerAge(21);
            worker.setWorkerName("HanMeiMei");

            System.out.println("id:" + worker.getId());
            System.out.println("workAge:" + worker.getWokerAge());
            System.out.println("workerName:" + worker.getWorkerName());

            //调用有参构造方法
            Worker LiLei = new Worker(123333, "LiLei", 20);
            System.out.println(LiLei);
        }
}
