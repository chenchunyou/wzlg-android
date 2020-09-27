package com.minghao.wzlg.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minghao.wzlg.MainActivity;
import com.minghao.wzlg.UpdateDialog;
import com.minghao.wzlg.domain.AppVersionInfo;
import com.minghao.wzlg.domain.RtnDto;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpdateManager {
    private static String domain = "http://uqubang.com/ruoyi-admin/";
    public static void checkUpdate(final Context context) {
        final Handler handler = new Handler();
        // 获取最新版本
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = domain + "app/version/latest";
                OkHttpClient httpClient = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                Response response = null;
                try {
                    response = httpClient.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response != null) {
                    // 解析数据
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        final RtnDto rtnDto = mapper.readValue(Objects.requireNonNull(response.body()).string(), RtnDto.class);
                        if (rtnDto.getCode() != 200) {
                            return;
                        }
                        final Object data = rtnDto.getData();
                        if (data == null) {
                            return;
                        }
                        LinkedHashMap dataMap = (LinkedHashMap) data;
                        final AppVersionInfo versionInfo = new AppVersionInfo();
                        versionInfo.setVersionId((int) dataMap.get("versionId"));
                        versionInfo.setVersionCode((int) dataMap.get("versionCode"));
                        versionInfo.setVersionName((String) dataMap.get("versionName"));
                        versionInfo.setUpdateContent((String) dataMap.get("updateContent"));
                        versionInfo.setUpdateRequired((int) dataMap.get("updateRequired"));
                        versionInfo.setDownloadUrl((String) dataMap.get("downloadUrl"));
                        versionInfo.setFileSize((int) dataMap.get("fileSize"));
                        versionInfo.setAppPackage((String) dataMap.get("appPackage"));
                        System.out.println(versionInfo);
                        // 最新版本号
                        int newVersionCode = versionInfo.getVersionCode();

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
                                    UpdateDialog updateDialog = new UpdateDialog(context, versionInfo);
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