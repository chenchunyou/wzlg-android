package com.minghao.wzlg;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class LoadingDialog extends Dialog {

    private TextView tv_dialog_loading;
    private String text = "加载中...";

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialogStyle);
        this.setCancelable(false);
    }

    public LoadingDialog(@NonNull Context context, String text) {
        super(context, R.style.LoadingDialogStyle);
        this.setCancelable(false);
        this.text = text;
    }

    public LoadingDialog(@NonNull Context context, int themeResId, String text) {
        super(context, themeResId);
        this.setCancelable(false);
        this.text = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);

        tv_dialog_loading = findViewById(R.id.tv_dialog_loading);
        tv_dialog_loading.setText(text);

        /*LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.getBackground().setAlpha(210);*/

    }
}
