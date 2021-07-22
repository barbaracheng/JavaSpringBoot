package com.sly.jdbc.utils;

import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO:使用JDBC+反射完成自定义的JdbcTemplate
 *
 * @author leyuan
 * @date 2021/7/17 12:46
 */
public class JdbcTemplate<T> {
    private Class<T> clazz;

    public JdbcTemplate(Class<T> clazz) {
        this.clazz = clazz;
    }
    /**
     * 使用JDBCTemplate完成增删改操作
     * 步骤：
     * 1 建立连接
     * 2 创建PreparedStatement对象
     * 3 设置占位符
     * 4 执行DML语句并返回受影响行数
     * @param sql SQL语句(DML)
     * @param data 占位符的值
     * @return 执行DML返回受影响行数。大于0：DML执行成功，否则：DML执行失败
     */
    public int update(String sql, Object...data) throws Exception {
        try (
                Connection conn = DruidUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ){
            int length = data.length;
            if (data != null && length > 0) {
                for (int i = 0; i < length; i++) {
                    ps.setObject(i+1,data[i]);
                }
            }
           return ps.executeUpdate();
        }
    }

    /**
     * 使用JDBC+反射完成查询某一条数据
     * 例如：根据id获取Customer表的某一行数据
     * 步骤：
     * 1 创建Connection连接
     * 2 创建PreparedStatement
     * 3 设置占位符
     * 4 执行SQL语句，并返回ResultSet对象
     * 5 获取ResulSet结果集对象的元数据(包括：select语句的总列数、列的标签名称。例如：cust_name)
     * 6 判断是否有下一行数据
     * 7 使用反射创建对象
     * 8 根据列的标签名称从结果集获取数据
     * 9 使用HuTool(胡图)工具类，将列标签名称转换为驼峰 cust_name  ---> custName
     * 10 根据名称获取属性签名 custName--->Field
     * 11 属性暴力破解，将获取的结果集数据注入到反射创建的对象中
     * 12 返回对象
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public T queryForObject(String sql, Object...params) throws Exception {
        try (
                Connection conn = DruidUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            int length = params.length;
            if (params != null && length > 0) {
                for (int i = 0; i <length ; i++) {
                    ps.setObject(i+1, params[i]);
                }
            }
            try (
                    ResultSet rs = ps.executeQuery();
                    ) {
                // 获取结果集元数据
                ResultSetMetaData metaData = rs.getMetaData();
                // 获取select语句总列数
                int columnCount = metaData.getColumnCount();
                // 反射创建对象
                T type = clazz.newInstance();
                // 遍历结果集
                if (rs.next()) {
                    for (int i = 0; i < columnCount; i++) {
                        String columnLabel = metaData.getColumnLabel(i+1);

                        Object data = rs.getObject(columnLabel);

                        String fieldName = StrUtil.toCamelCase(columnLabel);

                        Field filed = clazz.getDeclaredField(fieldName);

                        // 对属性签名进行暴力破解
                        filed.setAccessible(true);
                        filed.set(type, data);
                    }
                }
                return type;
            }
        }
    }

    /**
     * 查询所有数据
     * 步骤：
     *  1 创建Connection对象
     *  2 创建PreparedStatement对象
     *  3 执行SQL语句返回Result
     *  4 获取结果集的列总数
     *  5 遍历结果集
     *  6 使用反射创建对象
     *  7 遍历每一列
     *  8 根据列标签名称获取结果集数据
     *  9 使用暴力破解将将结果集数据注入到对象
     *  10 对象添加到List集合
     *  11 返回集合
     * @param sql SQL语句
     * @return 数据库查询的结果集转换为List列表
     * @throws Exception
     */
    public List<T> query(String sql) throws Exception {
        try (
                Connection conn = DruidUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ) {
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                T type = (T)clazz.newInstance();
                for (int i = 0; i < count; i++) {
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Object data = rs.getObject(columnLabel);
                    String fieldName = StrUtil.toCamelCase(columnLabel);
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(type, data);
                }
                list.add(type);
            }
            return list;
        }
    }
}
