package com.minghao.wzlg.domain;

// 课程对象
public class Course {
    // 课程名称
    private String name;
    // 上课地点
    private String place;
    // 教师
    private String teacher;
    // 长课标识（三节课）
    private boolean longCourse;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public boolean isLongCourse() {
        return longCourse;
    }

    public void setLongCourse(boolean longCourse) {
        this.longCourse = longCourse;
    }
}
