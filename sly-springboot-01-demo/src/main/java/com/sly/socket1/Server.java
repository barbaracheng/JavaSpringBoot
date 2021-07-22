package com.sly.socket1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Server {
    public static void main(String[] args) {
        try(
                //创建服务器套接字
                ServerSocket serverSocket = new ServerSocket(8888);
                //使用accept()方法获取套接字
                Socket socket = serverSocket.accept();
                //获取套接字字节流
                OutputStream out = socket.getOutputStream();
                //
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        ) {
            //获取系统当前时间
            LocalDateTime dt = LocalDateTime.now().withNano(0);
            //写入
            bw.write(dt.toString());
            // \r\n，回车换行，表示输出结束
            bw.newLine();
            //刷新缓存
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器套接字创建失败！");
        }

    }
}
