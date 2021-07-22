package com.sly.test;

import org.junit.Test;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

/*
*  TODO:使用反射获取ArrayList信息
*
* */
public class Reflect1Test {
    /*
     * 使用反射获取ArrayList包信息
     * 步骤：
     *       1 使用反射获取Class对象 Class<ArrayList> clazz = ArrayList.class;
     *       2 调用Class对象的getPackage()方法获取包信息
     *       3 打印包信息
     * */
    @Test
    public void getPackageInfoTest() {
        Class<ArrayList> arrayListClass = ArrayList.class;
        //获取包信息
        Package pck = arrayListClass.getPackage();
        //获取包名称
        String packageName = pck.getName();
        //打印包名称
        System.out.println("package " + packageName +";");
    }

    /*
    * 获取类型信息
    * 步骤：
    * 1 反射入口
    * 2 获取类型修饰符 public 或默认
    * 3 获取Class关键字
    * 4 获取类名称
    * 5 打印获取的类型信息
    * */
    @Test
    public void getClassInfoTest() {
        Class clazz =  ArrayList.class;
        //获取类型的修饰符，结果为1
        int mod = clazz.getModifiers();
        //整数转换为字符串
        //1---->public
        String modifier = Modifier.toString(mod);
        //判断Class对象是否是接口类型，条件成立返回interface，否则返回class
        String typeKey = clazz.isInterface()?"interface":"class";
        //获取类型名称：包名+类名
        String typeName = clazz.getName();
        //System.out.println(typeName);
        String typeSimpleName = clazz.getSimpleName();
        //完整输出：修饰符  class 类名
        System.out.print(modifier + " " + typeKey + " " + typeSimpleName);
    }

    /*
    * 获取ArrayList父类信息
    * 步骤：
    * 1 反射入口
    * 2 调用Class对象的getSuperClass()
    * 3 打印父类名称
    * */
    @Test
    public void getSuperClassInfoTest() {
        Class<ArrayList> clazz = ArrayList.class;
        /*
        *   ? super ArrayList
            ？表示任意类型，此时对？添加了限定(？要么是ArrayList要么是ArrayList的父类)。
            super表示下界，？至少是ArrayList类型

            ？ extends ArrayList
            extends表示上界，？的类型不能超过ArrayList
        * */
        Class<? super ArrayList> superclass = clazz.getSuperclass();
        String SuperClassName = superclass.getSimpleName();
        System.out.print(" extends " + SuperClassName + " ");
    }

    /*
    *  获取ArrayList实现的接口信息
    * 步骤：
    * 1 反射入口
    * 2 调用Class对象的getInterfaces()方法，返回实现的接口信息
    * 3 遍历每个接口信息，打印接口名称
    * */
    @Test
    public void getInterfaceInfoTest() {
        Class<ArrayList> clazz = ArrayList.class;
        //一个类可以实现一个或多个接口，所以使用数组存储接口信息
        Class<?>[] interfaces = clazz.getInterfaces();
        StringBuilder sb = new StringBuilder("implements");
        for (Class<?> inter : interfaces) {
            sb.append(", ").append(inter.getSimpleName());
        }
        sb.append(" { ");
        String InterfaceInfo = sb.toString();
        InterfaceInfo = InterfaceInfo.replaceFirst(","," ");
        System.out.println(InterfaceInfo);
    }

    /**
     * 将包信息、类型信息、父类信息、接口信息整合到一起
     */
    @Test
    public void allTest() {
        this.getPackageInfoTest();
        this.getClassInfoTest();
        this.getSuperClassInfoTest();
        this.getInterfaceInfoTest();
    }
}
