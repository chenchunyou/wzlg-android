package com.minghao.wzlg.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程表类
 */
public class CourseTable {

    private List<Week> weeks;

    public CourseTable(){
        List<Week> weeks = new ArrayList<Week>();
        for (int i = 0;i < 25;i++){
            weeks.add(new Week(i));
        }
        this.setWeeks(weeks);
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }
}
