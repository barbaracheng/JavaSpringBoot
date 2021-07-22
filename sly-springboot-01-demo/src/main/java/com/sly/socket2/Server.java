package com.sly.socket2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
    public static void main(String[] args) {
        try(
                ServerSocket ss = new ServerSocket(6666);
                Socket socket = ss.accept();
                //获取服务器输入和输出流
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();

                //字符流装饰字节流
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

        ) {
            //读取客户端的请求
            String requestInfo = br.readLine().toString();
            String responseInfo = null;
            if (Objects.equals(requestInfo, "whut")){
                responseInfo = "有效指令";
            }else {
                responseInfo = "无效指令";
            }
            //写入字节流
            bw.write(responseInfo);
            bw.newLine();
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("服务器套接字创建失败！");
        }
    }
}
