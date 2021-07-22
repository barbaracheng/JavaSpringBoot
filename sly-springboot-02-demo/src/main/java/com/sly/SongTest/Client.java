package com.sly.SongTest;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * TODO:客户端
 * 模拟多个客户端向服务器发起请求
 * 步骤：
 * 1 创建定时任务线程池，每隔500毫秒向服务器发送一次请求
 * 2 创建客户端套接字
 * 3 向服务器发送歌曲请求
 * 4 接收服务器返回结果
 * @author leyuan
 * @date 2021/7/14 21:10
 */
public class Client {
    public static void main(String[] args) {
        //创建线程数量固定的线程池
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(100);
        //创建随机数对象
        ThreadLocalRandom random = ThreadLocalRandom.current();

        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try (
                        Socket socket = new Socket("192.168.10.169", 8088);
                        InputStream in = socket.getInputStream();
                        OutputStream out = socket.getOutputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
                ) {
                    //使用输出流向服务器发送消息
                    String requestInfo = getSongName();
                    bw.write(requestInfo);
                    bw.newLine();
                    bw.flush();
                    //接收服务器响应的结果
                    String responseInfo = br.readLine();
                    System.out.println(responseInfo);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("客户端连接失败");
                }
            }

            /**
             * 根据随机数返回歌曲名称
             * @return 歌曲名称
             */
            private String getSongName() {
                int num = random.nextInt(5) + 1;
                String songName = null;
                switch (num) {
                    case 1:
                        songName = "蜜雪冰城";
                        break;
                    case 2:
                        songName = "演员";
                        break;
                    case 3:
                        songName = "热爱105°的你";
                        break;
                    case 4:
                        songName = "红色高跟鞋";
                        break;
                    case 5:
                        songName = "摇晃的红酒杯";
                        break;
                }
                return songName;
            }
        }, 1000, 500, TimeUnit.MILLISECONDS);
        //ses.shutdown();
    }
}
