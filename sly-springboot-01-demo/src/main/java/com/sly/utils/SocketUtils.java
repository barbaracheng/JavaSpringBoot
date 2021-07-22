package com.sly.utils;

import java.io.IOException;
import java.net.Socket;

public class SocketUtils {
    /**
     *
     * @param ipAddress 服务器的IP地址
     * @param port 服务器的端口
     * @return 返回true，表示能ping通服务器，为false则ping不同服务器
     */
    public static boolean ping(String ipAddress, int port) {
        try {
            Socket socket = new Socket(ipAddress, port);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
