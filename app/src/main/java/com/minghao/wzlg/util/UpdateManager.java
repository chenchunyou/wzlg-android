package com.minghao.wzlg.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.minghao.wzlg.LauncherActivity;
import com.minghao.wzlg.LoadingDialog;
import com.minghao.wzlg.MainActivity;
import com.minghao.wzlg.UpdateDialog;
import com.minghao.wzlg.databinding.ActivityLauncherBinding;
import com.minghao.wzlg.domain.File;
import com.minghao.wzlg.domain.R;
import com.minghao.wzlg.domain.ResultInfo;
import com.minghao.wzlg.domain.UpdateInfo;
import com.minghao.wzlg.domain.Version;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpdateManager {

    public static void checkUpdate(final Context context) {
        final Handler handler = new Handler();
        // 获取最新版本
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://47.107.156.206/update/version/latest";
                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = null;
                try {
                    response = httpClient.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response != null) {
                    // 转换对象
                    Gson gson = new Gson();
                    try {
                        R result = gson.fromJson(Objects.requireNonNull(response.body()).string(), R.class);
                        if (result.getCode() != 0) {
                            return;
                        }
                        LinkedTreeMap treeMap = (LinkedTreeMap) result.getData();
                        if (treeMap == null) {
                            return;
                        }
                        String s = treeMap.toString();
                        String replace = s.replace(" ", "");
                        //Log.e("TAG", replace);
                        String replace1 = replace.replace(":", "");
                        String replace2 = replace1.replace("/", "");
                        //{version={id=38.0,versionCode=3.0,versionName=1.0.0-release,modifyContent=我在理工1.0正式版发布,updateDate=2020-05-2822:19:35,forceUpdate=0.0},file={id=23.0,originalName=我在理工.apk,name=/profile/upload/2020/05/28/e764a1f6cc7cf0d1447a145e24d8fc7b.apk,type=application/vnd.android.package-archive,size=3208491.0,url=http://47.107.156.206/profile/upload/2020/05/28/e764a1f6cc7cf0d1447a145e24d8fc7b.apk,md5=9c56ddb07cc7338bec5fda5224b75657}}
                        final UpdateInfo updateInfo = gson.fromJson(replace2, UpdateInfo.class);
                        //Log.e("TAG", updateInfo.getVersion().getModifyContent());

                        Version version = updateInfo.getVersion();
                        // 最新版本号
                        int newVersionCode = version.getVersionCode();

                        //当前版本号
                        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                        long versionCode = 0;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            versionCode = packageInfo.getLongVersionCode();
                        } else {
                            versionCode = packageInfo.versionCode;
                        }

                        if (versionCode < newVersionCode) {
                            // 有更新
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    UpdateDialog updateDialog = new UpdateDialog(context, updateInfo);
                                    updateDialog.show();
                                }
                            });
                        }
                    } catch (IOException | PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

}
