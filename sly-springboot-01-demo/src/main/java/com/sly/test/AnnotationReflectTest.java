package com.sly.test;

import com.sly.annotation.Annotation;
import com.sly.entities.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO:注解+反射完成暴力破解
 * 步骤：1 定义实体类
 *      2 定义注解
 *      3 使用注解描述实体类
 *      4 定义测试类来测试反射+注解完成暴力破解
 *        4.1 定义 参与暴力破解的键值对
 *        4.2 使用反射创建对象
 *        4.3 遍历每个键值对
 *        4.4 根据属性名称key获取对应属性签名
 *        4.5 获取属性签名修饰的注解
 *        4.6 判断注解是否只读，是只读不参与暴力破解，否则进行暴力破解
 *        4.7 不是只读：将属性签名可访问性设置true
 *        4.8 暴力破解为属性赋值
 *        4.9 打印暴力破解之后的对象
 *
 * @author leyuan
 * @version 1.0
 * @date 2021/7/13 10:39
 */
public class AnnotationReflectTest {
    //定义参与暴力破解的键值对
    private static Map<String, Object> map = new ConcurrentHashMap<>();

    static {
        map.put("id", 2);
        map.put("stuName", "HanMeiMei");
        map.put("age", 21);
    }

    public static void main(String[] args) {
        //使用反射创建对象
        try {
            Class<?> studentClass = Class.forName("com.sly.entities.Student");
            Constructor<?> structor = studentClass.getConstructor();
            Student stu = (Student) structor.newInstance();
            System.out.println("暴力破解之前：" + stu);
            //遍历map的每个元素
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                //根据属性名称获取对应的属性
                Field field = studentClass.getDeclaredField(key);
                Annotation an = field.getAnnotation(Annotation.class);
                if (an.readOnly()) {
                    continue;
                }
                field.setAccessible(true);
                field.set(stu, value);
            }
            System.out.println("暴力破解之后：" + stu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
