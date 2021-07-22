package com.sly.entities;

import com.sly.annotation.Annotation;

@Annotation(desc = "学生实体类，有学生姓名，编号，年龄")
public class Student {

    @Annotation(desc = "学生编号", readOnly = true)
    private Integer id = 1;

    @Annotation(desc = "学生姓名")
    private String stuName = "Mark";

    @Annotation(desc = "学生年龄")
    private Integer age = 20;

    @Annotation(desc = "获取学生编号", readOnly = true)
    public Integer getId() {
        return id;
    }

    @Annotation(desc = "修改学生编号")
    public void setId(Integer id) {
        this.id = id;
    }

    public Student() {
    }

    public Student(Integer id, String stuName, Integer age) {
        this.id = id;
        this.stuName = stuName;
        this.age = age;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
