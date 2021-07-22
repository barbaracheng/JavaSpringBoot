package com.sly.test;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * TODO
 * 回顾：
 * 1 磁盘上的字节码加载到JVM的元空间内存就是一个入射，如何判断字节码是否加载成功呢？当元空间有对应的Class对象就
 * 说明字节码加载成功了。
 * 2 反射是在程序运行期间动态获取Class对象的信息，动态的创建对象调用方法
 *  获取信息：包、类型、父类信息、接口、属性、方法信息、构造方法信息等等
 * 3 Class常用方法
 *      getPackage() 获取包信息
 *      getSuperClass() 获取父类型信息
 *      getIsInterface() 判断该Class类型是否为接口，true是接口，false不是接口
 *      getInterfaces() 获取Class对象实现的所有接口信息
 *      getName() 获取Class对象的全名称(包名称+类名称)
 *      getSimpleName() 获取Class对象的名称（不包括包名）
 *  4  class 定义类的关键字(Type)
 *  5  反射应该叫反思，反思Class对象(对字节码做反思)
 */
public class Reflect2Test {
    /*
    * 获取ArrayList的所有属性签名
    * 步骤：
    * 1 反射入口
    * 2 调用Class对象的getDeclaredFields()方法
    * */
    @Test
    public void getAllFiledTest() {
        Class<ArrayList> clazz = ArrayList.class;
        // getDeclaredFields()能够获取公有、私有、保护、默认的属性签名，但是不能获取父类的属性签名
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //System.out.println(field);
            // 属性签名包括：修饰符、类型、名称
            // 获取属性的修饰符
            int mod = field.getModifiers();
            String modifier = Modifier.toString(mod);
            //获取属性的类型
            String TypeName = field.getType().getSimpleName();
            //获取属性的名称
            String FieldName = field.getName();
            System.out.println(modifier + " " + TypeName + " " + FieldName + ";");

        }

    }

    /*
    * 根据属性名称获取某个属性签名对象Field
    * 步骤：
    * 1 反射入口
    * 2 调用Class对象的getDeclaredField(name)方法根据属性名称获取一个属性签名
    * 3 打印属性信息
    * 小结： clazz.getDeclaredField("size"); 根据属性名称size获取属性签名对象
    * */
    @Test
    public void getFieldByName() {
        Class<ArrayList> clazz = ArrayList.class;
        try {
            Field filed = clazz.getDeclaredField("size");
            System.out.println(filed + ";");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
