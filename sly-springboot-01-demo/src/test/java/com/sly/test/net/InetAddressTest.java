package com.sly.test.net;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    @Test
    public void inetAddressTest() throws Exception {
        InetAddress ipAddress = InetAddress.getByName("LAPTOP-F7IGBFS2");
        String hostAddress = ipAddress.getHostAddress();
        String hostName = ipAddress.getHostName();
        System.out.println("主机名/地址："+ ipAddress);
        System.out.println("hostName：" + hostName);
        System.out.println("hostAddress：" + hostAddress);
    }

    @Test
    public void inetAddress1Test() throws UnknownHostException {
        InetAddress ip = InetAddress.getByName("www.qq.com");
        String hostAddress = ip.getHostAddress();
        String hostName = ip.getHostName();
        System.out.println("主机名/地址："+ ip);
        System.out.println("hostName：" + hostName);
        System.out.println("hostAddress：" + hostAddress);
    }
}
