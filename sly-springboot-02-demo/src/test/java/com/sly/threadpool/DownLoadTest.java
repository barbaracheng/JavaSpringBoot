package com.sly.threadpool;

import com.sly.utils.slyThreadPool;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO:从远端服务器下载资源
 *
 * @author leyuan
 * @date 2021/7/14 21:48
 */
public class DownLoadTest {
    /**
     * 下载的资源
     */
    private final static String [] URL_PATH= {
            "http://n.sinaimg.cn/sports/2_img/upload/69e00db4/20170328/w1so-fycstxp52712761.jpg",
            "http://n.sinaimg.cn/sports/2_img/upload/69e00db4/20170328/k3tH-fyctevp9037944.jpg",
            "http://n.sinaimg.cn/sports/2_img/upload/69e00db4/20170328/a27s-fycstxp5271274.jpg",
            "http://n.sinaimg.cn/sports/2_img/upload/69e00db4/20170328/8IYH-fycstww1654561.jpg",
            "http://n.sinaimg.cn/sports/2_img/upload/69e00db4/20170328/ghEs-fycstww1654563.jpg"
    };

    public static void main(String[] args) {
        ThreadPoolExecutor pool = slyThreadPool.createPool();
        //遍历下载的资源地址
        for (String url : URL_PATH) {
            //下载成功返回true，失败返回false
            Future<Boolean> result = pool.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    try {
                        byte[] data = fromNetWorkDownLoad();
                        save2Disk(data);
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }

                }

                private void save2Disk(byte[] data) {
                    //获取url最后一个.的位置
                    int lastIndex = url.lastIndexOf(".");
                    //文件后缀
                    String suffix = url.substring(lastIndex);
                    //文件前缀，以系统当前时间为文件名
                    long prefix = System.nanoTime();
                    //文件路径
                    //String path = "D:/SourceCode/sly-springboot/sly-springboot-02-demo/src/main/java/com/sly/images/";
                    String fullPath = prefix + suffix;
                    try (
                            FileOutputStream fos = new FileOutputStream(fullPath);
                    ) {
                        fos.write(data);
                        fos.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                private byte[] fromNetWorkDownLoad() throws Exception {
                    //创建URL对象
                    URL urlPath = new URL(url);
                    try (
                            //向服务器发起连接请求
                            InputStream in = urlPath.openStream();
                            //内存输出流
                            ByteArrayOutputStream out = new ByteArrayOutputStream();
                    ) {
                        //每次从服务器读取1024字节
                        byte[] buf = new byte[1024];
                        //每次读取长度
                        int length = 0;
                        //读取服务器的数据
                        //-1表示读到文件结尾
                        while ((length = in.read(buf)) != -1) {
                            //将读取的结果写入内存流
                            out.write(buf, 0, length);
                        }
                        //强制刷新缓冲区内存
                        out.flush();
                        return out.toByteArray();
                    }
                }
            });
            try {
                final Boolean finalResult = result.get();
                System.out.println(finalResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //销毁线程池
        pool.shutdown();
    }

}
