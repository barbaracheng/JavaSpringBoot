package com.sly.SongTest;

import com.sly.utils.slyThreadPool;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO:定义服务器端套接字
 *步骤：
 * 1 创建服务器套接字
 * 2 定义线程池
 * 3 服务器轮询监听客户端请求
 * 4 一旦有客户端发送连接请求，服务器的线程池就派发线程处理
 * @author leyuan
 * @date 2021/7/14 17:43
 */
public class Server {
    public static void main(String[] args) {
        try (
                ServerSocket ss = new ServerSocket(20023);
                ) {
            Server server = new Server();
            //处理请求
            server.handRequest(ss);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("服务器套接字创建失败");
        }
    }

    private void handRequest(ServerSocket ss) {
        ThreadPoolExecutor pool = slyThreadPool.createPool();
        while (true) {
            try {
                //接收客户端请求
                Socket socket = ss.accept();
                //处理客户端请求，一个客户端对应线程池的一个线程
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (
                                InputStream in = socket.getInputStream();
                                OutputStream out = socket.getOutputStream();
                                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
                                ) {
                            //获取系统当前时间
                            LocalDateTime ldt = LocalDateTime.now();
                            //接收客户端请求
                            String requestInfo = br.readLine();
                            //处理客户端请求，根据歌名返回对应的歌词
                            String responseInfo = SongEnum.getSongWordByName(requestInfo);
                            //将歌词返回给客户端
                            bw.write("线程："+Thread.currentThread().getName() + "-->当前时间："+ ldt + "-->歌词：" + responseInfo);
                            bw.newLine();
                            bw.flush();
                            //获取客户端的信息
                            InetAddress inetAddress = socket.getInetAddress();
                            System.out.println("客户端名称："+ inetAddress.getHostName());
                            System.out.println("客户端地址：" + inetAddress.getHostAddress());


                            //System.out.println("客户端信息："+socket.getRemoteSocketAddress());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
