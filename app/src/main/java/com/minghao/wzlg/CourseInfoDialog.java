package com.minghao.wzlg;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.minghao.wzlg.domain.Course;

public class CourseInfoDialog extends Dialog {
    private Course course;
    private TextView tv_course_name;
    private TextView tv_course_classroom;
    public CourseInfoDialog(@NonNull Context context, Course course) {
        super(context, R.style.CourseInfoDialogStyle);
        this.course = course;
    }

    public CourseInfoDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CourseInfoDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_course_info);
        tv_course_name = findViewById(R.id.tv_course_name);
        tv_course_classroom = findViewById(R.id.tv_course_classroom);
        tv_course_name.setText(course.getName());
        tv_course_classroom.setText(course.getPlace());
    }

    public void setCourse(Course course) {
        this.course = course;
        tv_course_name.setText(course.getName());
        tv_course_classroom.setText(course.getPlace());
    }
}
