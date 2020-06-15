package com.minghao.wzlg.util;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.minghao.wzlg.domain.ResultInfo;
import com.minghao.wzlg.domain.Student;

public class MyUtils {

    public static Student getStudentByInfoJson(String json_info){

        Gson gson = new Gson();
        ResultInfo info = gson.fromJson(json_info, ResultInfo.class);
        String json_student = ((LinkedTreeMap) info.getData()).toString();

        return gson.fromJson(json_student, Student.class);
    }

    public static void updateToast(Context context, String text, int duration){
        Toast toast = new Toast(context);
        View view = Toast.makeText(context, "", Toast.LENGTH_SHORT).getView();
        toast.setView(view);
        toast.setText(text);
        toast.setDuration(duration);
        toast.show();
    }



}
