package com.minghao.wzlg.domain;

public class Course {

    private String name;//课程名称
    private String classroom;//上课地点
    private String teacher;//上课老师
    private boolean threeLessons = false;//是否三节课
    private String qqGroup;// 课程QQ群

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public boolean isThreeLessons() {
        return threeLessons;
    }

    public void setThreeLessons(boolean threeLessons) {
        this.threeLessons = threeLessons;
    }

    public String getQqGroup() {
        return qqGroup;
    }

    public void setQqGroup(String qqGroup) {
        this.qqGroup = qqGroup;
    }
}
