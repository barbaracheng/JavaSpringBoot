package com.sly.SongTest;

import com.sly.jdbc.utils.DruidUtils;
import com.sly.jdbc.utils.XMLUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO:使用JdbcTemplate对象执行sql语句
 *
 * @author leyuan
 * @date 2021/7/16 20:18
 */
public class Server {
    // Jdbc模板对象
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());

    /**
     * 根据歌名获取歌词
     * @param songName 歌名
     * @return 歌词
     */
    private String getSongWordByName(String songName) {
        String sql = XMLUtils.getSQLByKey("querySongWord");
        Object[] data = {songName};
        Song song = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Song.class),data);
        assert song != null;
        return song.getSongWord();
    }

    public static void main(String[] args) {
        try (
                ServerSocket ss = new ServerSocket(8088);
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
                            Scanner input = new Scanner(System.in);
                            //获取客户端的信息
                            InetAddress inetAddress = socket.getInetAddress();

                            //接收客户端请求
                            String requestInfo = br.readLine();
                            String responseInfo = null;
                            while(!Objects.equals(requestInfo,null) || !Objects.equals(responseInfo,"bye")) {
                                //获取系统当前时间
                                LocalDateTime ldt = LocalDateTime.now().withNano(0);
                                System.out.println(ldt);
                                System.out.println(inetAddress.getHostName()+":"+requestInfo);

                                //处理客户端请求，根据歌名返回对应的歌词
//                            String responseInfo = getSongWordByName(requestInfo);
                                System.out.println(LocalDateTime.now().withNano(0));
                                System.out.print(InetAddress.getLocalHost().getHostName()+":");
                                responseInfo = input.next();

                                bw.write(responseInfo);
                                bw.newLine();
                                bw.flush();
                                System.out.println("等待来自"+inetAddress.getHostName()+"的回复...");
                                requestInfo = br.readLine();
                                if (Objects.equals(requestInfo,"bye") || Objects.equals(requestInfo,null)) {
                                    bw.write("bye");
                                    bw.newLine();
                                    bw.flush();
                                    requestInfo = br.readLine();
                                    System.out.println(LocalDateTime.now().withNano(0));
                                    System.out.println(inetAddress.getHostName()+":"+requestInfo);
                                    break;
                                }
                            }
                            System.out.println("本次对话结束...");

                            //将歌词返回给客户端
//                            bw.write("线程："+Thread.currentThread().getName() + "-->当前时间："+ ldt + "-->歌词：" + responseInfo);
//                            bw.newLine();
//                            bw.flush();




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
