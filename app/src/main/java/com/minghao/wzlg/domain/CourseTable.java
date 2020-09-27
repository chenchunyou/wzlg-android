package com.minghao.wzlg.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 课程表对象
 */
public class CourseTable {

    private int weekCnt = 22;
    // 学期第一天
    private Date firstDay = new Date("2020/09/07");
    // 周
    private List<Week> weeks;

    public CourseTable() {
        System.out.println(weekCnt);
        this.weeks = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDay);
        for (int weekIndex = 0; weekIndex < weekCnt; weekIndex++) {
            Week week = new Week();
            for (int dayIndex = 0;dayIndex < 7;dayIndex++) {
                SimpleDateFormat format = new SimpleDateFormat("MM-dd");
                week.getDates().add(format.format(calendar.getTime()));
                calendar.add(Calendar.DATE, 1);
            }
            this.weeks.add(week);
        }
    }

    public int getWeekCnt() {
        return weekCnt;
    }

    public void setWeekCnt(int weekCnt) {
        this.weekCnt = weekCnt;
    }

    public Date getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }
}
