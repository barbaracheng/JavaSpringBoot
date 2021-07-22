package com.sly.annotation;

import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO:自定义注解
 * 场景：使用自定义注解来描述Student
 * 要求：
 *  1 注解和反射结合使用，创建Student对象
 *  2 只读属性不参与反射
 *
 * */
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Annotation {
    String test() default "";

    /*
    * 描述：描述类、属性和方法
    * @return 返回描述
    * */
    String desc() default "";

    /*
    * 描述：类、属性、方法是否只读
    * true：只读
    * false：不是只读
    * 默认为false
    * @return 是否只读
    * */
    boolean readOnly() default false;
}

enum TestEnum {
    ONE;
}