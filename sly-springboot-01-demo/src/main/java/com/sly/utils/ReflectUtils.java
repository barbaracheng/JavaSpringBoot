package com.sly.utils;

import com.sly.annotation.Annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * TODO: 反射工具类
 * 提供了若该个静态方法，包括：反射创建对象、反射调用方法、反射暴力破解
 */

public class ReflectUtils {
    /**
     * 工具方法：使用反射暴力破解
     * 步骤：
     * 1 反射入口
     * 2 根据属性名称获取属性签名
     * 3 将属性的可访问性设置为true
     * 4 使用反射暴力破解
     *
     * @param type 属性所属的对象
     * @param fieldName 属性名称
     * @param value 属性值
     * @param <T> 参数支持泛型
     */
    public static <T> void accessField(T type, String fieldName, Object value) throws Exception {
        Class<?> clazz = type.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        //将属性的可访问性设置为true
        field.setAccessible(true);
        field.set(type,value);
    }

    /**
     *工具方法：反射调用方法，此时调用带有参数的方法
     * 步骤：
     * 1 反射入口
     * 2 根据方法名称和参数类型获取方法签名
     * 3 调用带有参数的方法，将对象和实际的值注入到方法中
     * 4 返回调用结果
     * @param type 方法所属的对象
     * @param methodName 用字符串表示的方法名
     * @param params 方法参数类型
     * @param values 参数实际值
     * @param <T> 方法所属对象支持泛型
     * @param <R> 方法返回结果支持泛型
     * @return 调用结果
     * @throws Exception
     */
    public static <T,R> R invokeMethod(T type, String methodName, Class<?>[] params, Object...values) throws Exception {
        Class<?> clazz = type.getClass();
        Method method = clazz.getDeclaredMethod(methodName,params);
        return (R) method.invoke(type,values);
    }

    /**
     * 工具方法：反射调用方法，此时调用无参方法
     * 步骤：
     * 1 反射入口
     * 2 根据方法名称获取方法签名
     * 3 返回方法调用结果
     * @param type 方法所属对象
     * @param methodName 方法名称
     * @param <T> 方法支持泛型
     * @param <R> 返回结果支持泛型
     * @return 返回方法调用结果
     * @throws Exception
     */
    public static <T,R> R invokeMethod(T type, String methodName) throws Exception {
        Class<?> clazz = type.getClass();
        Method method = clazz.getDeclaredMethod(methodName);
        return (R) method.invoke(type);
    }

    /**
     * 工具方法：使用反射构建带参数构造方法的对象
     * @param className 字符串表示的类名称（包名+类名）
     * @param paramsType 参数类型
     * @param values 实际的参数值
     * @param <R> 方法支持泛型
     * @return 返回创建的对象
     * @throws Exception
     */
    public static <R> R createObject(String className, Class<?>[] paramsType, Object...values) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> structor = clazz.getConstructor(paramsType);
        return (R) structor.newInstance(values);
    }

    /**
     * 工具方法：使用反射创建不带参数的对象
     * 步骤：
     * 1 反射入口
     * 2 调用Class对象的newInstance()创建对象
     * 3 返回创建的对象
     * 缺点：
     *   不支持泛型，clazz.newInstance()在JDK8之后就会过期
     * @param className 字符串表示的类名（包名+类名）
     * @return 返回创建的对象
     * @throws Exception
     */
    public static Object createObject1(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        return clazz.newInstance();
    }

    /**
     * 工具方法：反射创建不带参数的对象，使用构造器
     * @param className 对象所属的类名称（包名+类名）
     * @param <R> 方法支持泛型
     * @return 返回创建的对象
     * @throws Exception
     */
    public static <R> R createObject(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> structor = clazz.getDeclaredConstructor();
        return (R) structor.newInstance();
    }

    /**
     * 工具方法：使用注解和反射进行暴力破解，只读属性不参与暴力破解
     * 步骤：
     * 1 反射入口，获取type的Class对象
     * 2 遍历map每个键值对
     * 3 根据键获取相应的属性签名
     * 4 根据属性签名获取对应的注解
     * 5 判断注解的只读属性
     * 6 如果是只读，就不行暴力破解
     * 7 如果不是只读，就对属性进行暴力破解
     * @param type 参与暴力破解的对象类型
     * @param map 暴力破解的键值对
     * @param <T> 暴力破解的对象支持泛型
     * @throws Exception
     */
    public static <T> void accessAllField(T type, Map<String,Object> map) throws Exception {
        Class<?> clazz = type.getClass();
        //遍历map每个元素
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            //获取map中的键和值
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();
            //根据属性名获取属性签名
            Field field = clazz.getDeclaredField(fieldName);
            //获取属性对应的注解
            Annotation an = field.getAnnotation(Annotation.class);
            //判断注解的readOnly属性是否为true，为true则不暴力破解
            if(an.readOnly()){
                continue;
            }
            field.setAccessible(true);
            field.set(type, fieldValue);
        }
        //打印暴力破解之后的对象
        System.out.println(type);
    }


}
