package com.sly.test;

public class Student {
    private Integer id;

    private String stuName;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Student() {

    }

    public Student(Integer id, String stuName) {
        this.id = id;
        this.stuName = stuName;
    }

}
