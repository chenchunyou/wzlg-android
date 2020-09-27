package com.minghao.wzlg.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Week {
    // 课堂
    private List<Course> courses = new ArrayList<>();
    // 日期列表
    private List<String> dates = new ArrayList<>();

    public Week() {
        for (int i = 0; i < 35; i++) {
            this.courses.add(new Course());
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}
