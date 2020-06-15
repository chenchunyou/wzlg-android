package com.minghao.wzlg.domain;

/**
 * 学生类
 */
public class Student {

    private String name;//姓名
    private String username; //学号
    private String password; //密码
    private CourseTable table; //课程表
    private String phone;//手机号
    private String username1;//加密后的学号
    private String password1;//加密后的密码

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CourseTable getTable() {
        return table;
    }

    public void setTable(CourseTable table) {
        this.table = table;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
