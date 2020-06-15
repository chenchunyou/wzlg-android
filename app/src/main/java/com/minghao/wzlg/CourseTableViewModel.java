package com.minghao.wzlg;

import android.content.Context;
import android.text.TextUtils;

import androidx.lifecycle.ViewModel;

import com.minghao.wzlg.domain.Course;
import com.minghao.wzlg.domain.Week;

public class CourseTableViewModel extends ViewModel {
    // TODO: Implement the ViewModel


    private Week week;
    private Context context;
    private CourseInfoDialog courseInfoDialog;

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void click(int courseId) {
        if (courseInfoDialog != null && courseInfoDialog.isShowing()) {
            courseInfoDialog.dismiss();
        }
        Course course = week.getKeTangs().get(courseId).getCourse();
        if (!TextUtils.isEmpty(course.getName())) {
            if (courseInfoDialog == null) {
                courseInfoDialog = new CourseInfoDialog(context, course);
            } else {
                courseInfoDialog.setCourse(course);
            }
            courseInfoDialog.show();
        }
    }

}
