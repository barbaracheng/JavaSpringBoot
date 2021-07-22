package com.sly.socket2;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("192.168.43.27", 6666);
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

        ) {
            bw.write("whut");
            bw.newLine();
            bw.flush();

            String responseInfo = br.readLine().toString();
            System.out.println(responseInfo);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端套接字创建失败！");
        }
    }
}
