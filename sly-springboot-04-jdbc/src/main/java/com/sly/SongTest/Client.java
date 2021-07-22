package com.sly.SongTest;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/16 20:19
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
                        Socket socket = new Socket("192.168.43.27", 8088);
                        InputStream in = socket.getInputStream();
                        OutputStream out = socket.getOutputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
                ) {
                    Scanner input = new Scanner(System.in);
                    //使用输出流向服务器发送消息
                    String requestInfo = input.next();
                    LocalDateTime ldt = LocalDateTime.now();
                    System.out.println(InetAddress.getLocalHost()+":"+requestInfo);
                    bw.write(InetAddress.getLocalHost()+":"+requestInfo);
                    bw.newLine();
                    bw.flush();
                    //接收服务器响应的结果
                    String responseInfo = br.readLine();
                    System.out.println("时间:"+ldt);
                    System.out.println(socket.getInetAddress()+":"+responseInfo);

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
                        songName = "十年";
                        break;
                    case 2:
                        songName = "演员";
                        break;
                    case 3:
                        songName = "情歌王";
                        break;
                    case 4:
                        songName = "去年夏天";
                        break;
                    case 5:
                        songName = "海底";
                        break;
                }
                return songName;
            }
        }, 1000, 500, TimeUnit.MILLISECONDS);
        //ses.shutdown();
    }
}
