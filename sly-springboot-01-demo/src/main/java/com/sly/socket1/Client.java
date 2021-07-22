package com.sly.socket1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try(
                Socket socket = new Socket("192.168.10.15", 8888);
                //获取客户端套接字的输入管道，用于读取服务器发送的数据
                InputStream in = socket.getInputStream();
                //字符流装饰了字节流
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
        ) {
            //客户端接受服务器数据
            String currentTime = br.readLine();
            System.out.println(currentTime);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端套接字创建失败！");
        }
    }
}
