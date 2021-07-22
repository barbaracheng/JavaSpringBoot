package com.sly.jdbc.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO:工具类
 * 解析磁盘的XML文件，将解析的数据加载到document缓存，然后将Document缓存加载到Map缓存
 * 提供一个静态方法，让外界根据key获取Map缓存的值
 * @author leyuan
 * @date 2021/7/16 10:42
 */
public class XMLUtils {
    //定义Document对象
    private static Document document;

    //定义Map对象，容量最好定义为2的整数次幂
    private static Map<String, String> cacheMap = new ConcurrentHashMap<>(16);

    /**
     * 初始化缓存数据
     * 1 磁盘数据使用当前线程类加载器加载到IO流
     * 2 Dom4J解析IO流的数据到Document缓存
     * 3 将Document缓存数据存储到Map缓存(key='' value='')
     */
    static {
        try (
                InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("server.xml");
                ) {
            if (in == null) {
                throw new RuntimeException("XML配置文件加载失败");
            }
            //
            SAXReader sr = new SAXReader();
            document = sr.read(in);
            //获取xml文件根节点service
            Element root = document.getRootElement();
            //获取xml文件根节点的子节点sql
            List<Element> sqlList = root.elements("sql");
            //遍历sql子节点，获取键值对
            for (Element element : sqlList) {
                String key = element.attributeValue("key");
                String value = element.attributeValue("value");
//                System.out.println("key:"+key+", value:"+value);
                //将键值对存入Map
                cacheMap.put(key,value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("XML配置文件初始化失败");
        }
    }

    /**
     * 根据键返回对应的sql语句
     * @param key
     * @return sql语句
     */
    public static String getSQLByKey(String key) {
        return cacheMap.get(key);
    }
}
