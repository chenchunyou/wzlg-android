package com.minghao.wzlg.domain;

import java.util.ArrayList;
import java.util.List;

public class KeTang {
    private int KeTangId;//课堂编号 0~34
    private Course course;//所对应的课程

    public KeTang(int keTangId){
        Course course = new Course();
        this.setCourse(course);
        this.setKeTangId(keTangId);
    }

    public int getKeTangId() {
        return KeTangId;
    }

    public void setKeTangId(int keTangId) {
        KeTangId = keTangId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "KeTang{" +
                "KeTangId=" + KeTangId +
                ", course=" + course +
                '}';
    }
}
