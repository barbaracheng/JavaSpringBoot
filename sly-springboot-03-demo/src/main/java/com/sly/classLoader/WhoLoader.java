package com.sly.classLoader;

/**
 * TODO:类加载器
 *
 * @author leyuan
 * @date 2021/7/15 9:59
 */
public class WhoLoader {
    /**
     * 1 获取当前类的类加载器
     * 2 获取类加载器的父类加载器
     * 3 获取当前类加载器的根类加载器
     * @param args
     */
    public static void main(String[] args) {
        ClassLoader appClassLoader = WhoLoader.class.getClassLoader();
        //打印结果：sun.misc.Launcher$AppClassLoader@18b4aac2
        //WhoLoader.class是由AppClassLoader类加载器加载的，它专门加载当前工程CLASSPATH路径下面的class文件
        //小结：WhoLoader的类加载器是AppClassLoader
        System.out.println(appClassLoader);

        //打印结果：sun.misc.Launcher$ExtClassLoader@1b6d3586
        //ExtClassLoader专门加载JDK安装路径/jre/lib/ext目录下所有的class文件
        //小结：AppClassLoader类加载器的父类加载器是ExtClassLoader
        ClassLoader extClassLoader = appClassLoader.getParent();
        System.out.println(extClassLoader);

        //打印结果：null
        /**
         * BootstrapClassLoader所有类加载器的父类(根)，
         * BootstrapClassLoader是jvm.dll文件启动的时候创建的,
         * 它专门加载JDK安装路径/jre/lib/目录下所有的class文件
         */
        //小结：因为BootstrapClassLoader是C++编写的，所以打印出来是空
        ClassLoader bootstrapClassLoaderParent = extClassLoader.getParent();
        System.out.println(bootstrapClassLoaderParent);
    }
}
