package com.minghao.wzlg;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.minghao.wzlg.domain.File;
import com.minghao.wzlg.domain.UpdateInfo;
import com.minghao.wzlg.domain.Version;
import com.minghao.wzlg.util.DownLoadUtil;
import com.minghao.wzlg.util.MyUtils;

import java.util.Objects;

public class UpdateDialog extends Dialog {

    private UpdateInfo info;
    private Button btn_yes;
    private Button btn_next;
    private TextView tv_modify_content;
    private ProgressBar progressBar;

    public UpdateDialog(@NonNull Context context, UpdateInfo info) {
        super(context, R.style.UpdateDialogStyle);
        this.info = info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_dialog);

        tv_modify_content = findViewById(R.id.tv_modifycontent);
        btn_yes = findViewById(R.id.btn_yes);
        btn_next = findViewById(R.id.btn_next);
        progressBar = findViewById(R.id.progressBar);
        Version version = info.getVersion();
        final File file = info.getFile();
        tv_modify_content.setText(version.getModifyContent());
        this.setCancelable(false);

        String houbian = file.getUrl().replace("http47.107.156.206profileupload", "");
        String year = houbian.substring(0, 4);
        String mouth = houbian.substring(4, 6);
        String day = houbian.substring(6, 8);
        String apk = houbian.substring(8);

        final String downLoadUrl =  "http://47.107.156.206/profile/upload/"
                + year +"/"
                + mouth + "/"
                + day + "/"
                + apk;

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_yes.setClickable(false);
                btn_next.setClickable(false);
                // 更新
                progressBar.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                // 下载线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //储存下载文件的目录
                        String destFileDir = getContext().getCacheDir().getPath();
                        String destFileName = file.getOriginalName();
                        DownLoadUtil.get().download(downLoadUrl, destFileDir, destFileName, new DownLoadUtil.OnDownloadListener() {
                            @Override
                            public void onDownloadSuccess(final java.io.File file) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        installAPK(file);
                                        dismiss();
                                    }
                                });
                            }

                            private void installAPK(java.io.File apkFile) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    Uri apkUri = FileProvider.getUriForFile(getContext(), "com.minghao.wzlg.fileprovider", apkFile);
                                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                                } else {
                                    intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
                                }
                                getContext().startActivity(intent);
                            }

                            @Override
                            public void onDownloading(final int progress) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progress);
                                    }
                                });
                            }

                            @Override
                            public void onDownloadFailed(Exception e) {
                                //Log.e("TAG", Objects.requireNonNull(e.getLocalizedMessage()));
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        MyUtils.updateToast(getContext(), "更新失败，服务器异常", Toast.LENGTH_SHORT);
                                        dismiss();
                                    }
                                });
                            }
                        });

                    }
                }).start();

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 不更新
                btn_yes.setClickable(false);
                btn_next.setClickable(false);
                dismiss();
            }
        });
    }
}
