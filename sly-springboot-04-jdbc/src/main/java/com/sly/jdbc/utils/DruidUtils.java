package com.sly.jdbc.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import sun.java2d.pipe.PixelToParallelogramConverter;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * TODO：Druid数据库连接池工具类
 * 步骤：
 * 1 加载磁盘配置文件到Properties缓存
 * 2 使用Druid连接池创建数据库连接池对象，并注入Properties缓存
 * 3 提供静态方法让外界使用连接池对象
 * @author leyuan
 * @date 2021/7/16 14:34
 */
public class DruidUtils {
    // 数据库连接池对象
    private static DataSource ds;

    // 磁盘配置文件--->ClassLoader--->IO---->Properties---->Druid连接池
    static {
        try (
                InputStream in = Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("druid.properties");

                ) {
            if (null == in) {
                throw new RuntimeException("Druid配置文件加载失败");
            }

            // 将IO数据流加载到Properties缓存
            Properties props = new Properties();
            props.load(in);

            // 使用DruidDataSourceFactory工厂方法创建Druid数据库连接池对象
           ds = DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Druid数据库连接池加载失败");
        }
    }

    /**
     * 向外界提供Druid数据库连接池对象
     * getDataSource()方法调用之后，ds里面的初始连接大小是0，最大连接大小是0
     * @return 数据库连接池对象
     */
    public static DataSource getDataSource () {
        return ds;
    }

    /**
     * 提供创建Connection对象的方法
     * @return Connection对象
     * @throws SQLException
     */
    public static Connection getConnection () throws SQLException {
        return ds.getConnection();
    }
}
