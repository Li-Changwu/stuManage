package com.lcw.domain;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String age;
    private String sex;
    private String studentNo;
    private String major;

    public Student() {
    }

    public Student(String name, String age, String sex, String studentNo, String major) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.studentNo = studentNo;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSid() {
        return studentNo;
    }

    public void setSid(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
