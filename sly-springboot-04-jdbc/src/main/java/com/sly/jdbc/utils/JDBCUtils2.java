package com.sly.jdbc.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * TODO:JDBC工具类（带配置文件）
 * 从磁盘读取JDBC配置信息，将配置信息的数据加载到properties缓存中
 * JDBC工具类需要用到配置信息的时候，直接从缓存中get()
 * 步骤：
 * 1 定义properties缓存
 * 2 使用当前线程的类加载器加载配置文件jdbc.properties到InputStream数据流中
 * 3 将InputStream数据流的数据加载到Properties
 * 4 定义JDBC连接的工具方法，从Properties缓存中读取数据
 * @author leyuan
 * @date 2021/7/15 17:31
 */
public class JDBCUtils2 {
    // Properties缓存，用于存储JDBC数据库连接的数据
    private static Properties props = new Properties();

    static {
        try (
                // 当前线程的类加载器
                // Thread.currentThread().getContextClassLoader()
                // 将磁盘指定的配置文件jdbc.properties的数据转换到IO流(InputStream)
                InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");

                ) {
            // 判断磁盘配置文件是否加载到IO流
            if (in == null) {
                throw new RuntimeException("配置文件加载失败");
            }
            // 将IO流(InputStream)的数据加载到Properties缓存
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection () throws ClassNotFoundException, SQLException {
        Class.forName(props.getProperty("jdbc.driver"));
        String url = props.getProperty("jdbc.url");
        String userName = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, userName, password);
    }
}
