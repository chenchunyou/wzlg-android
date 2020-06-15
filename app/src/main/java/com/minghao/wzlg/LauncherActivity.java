package com.minghao.wzlg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.google.gson.Gson;
import com.minghao.wzlg.databinding.ActivityLauncherBinding;
import com.minghao.wzlg.domain.ResultInfo;
import com.minghao.wzlg.util.EncryptUtil;
import com.minghao.wzlg.util.MyUtils;
import com.minghao.wzlg.util.UpdateManager;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LauncherActivity extends AppCompatActivity {

    private ActivityLauncherBinding binding;
    private AlphaAnimation apperaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_launcher);
        apperaAnimation = new AlphaAnimation(0, 1);
        apperaAnimation.setDuration(800);

        if (binding.imgLog.getVisibility() == View.GONE) {
            binding.imgLog.startAnimation(apperaAnimation);
            binding.imgLog.setVisibility(View.VISIBLE);
        }

        if (binding.imgWzlg.getVisibility() == View.GONE) {
            binding.imgWzlg.startAnimation(apperaAnimation);
            binding.imgWzlg.setVisibility(View.VISIBLE);
        }

        /*//检查获取权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            } else start();
        } else start();*/
        start();
    }

    private void start() {
        final SharedPreferences preferences = getSharedPreferences("responseBodyStr", MODE_PRIVATE);
        final String responseBodyStr = preferences.getString("responseBodyStr", null);
        if (!TextUtils.isEmpty(responseBodyStr)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                    intent.putExtra("json_result", responseBodyStr);
                    startActivity(intent);
                    finish();
                }
            }, 800);
            return;
        }

        if (binding.btnBind.getVisibility() == View.GONE) {
            binding.btnBind.startAnimation(apperaAnimation);
            binding.btnBind.setVisibility(View.VISIBLE);
        }

        if (binding.tvSbgg.getVisibility() == View.GONE) {
            binding.tvSbgg.startAnimation(apperaAnimation);
            binding.tvSbgg.setVisibility(View.VISIBLE);
        }

        apperaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.btnBind.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.btnBind.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        binding.btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnBind.setVisibility(View.GONE);
                binding.tvSbgg.setVisibility(View.GONE);
                binding.etXuehao.startAnimation(apperaAnimation);
                binding.etXuehao.setVisibility(View.VISIBLE);
                binding.etPassword.startAnimation(apperaAnimation);
                binding.etPassword.setVisibility(View.VISIBLE);
                binding.btnLogin.startAnimation(apperaAnimation);
                binding.btnLogin.setVisibility(View.VISIBLE);
                //检查更新
                UpdateManager.checkUpdate(LauncherActivity.this);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xuehao = binding.etXuehao.getText().toString();
                String password = binding.etPassword.getText().toString();
                if (TextUtils.isEmpty(xuehao)) {
                    Toast.makeText(LauncherActivity.this, "请输入您的学号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LauncherActivity.this, "请输入您的密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                login(xuehao, password);
            }

            private void login(final String xuehao, final String password) {
                final LoadingDialog loadingDialog = new LoadingDialog(LauncherActivity.this, "登录中...");
                loadingDialog.show();
                final Handler handler = new Handler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String username = EncryptUtil.md5(xuehao);
                        String password1 = EncryptUtil.sha(xuehao + password);
                        String url = "http://47.107.156.206/lgkcb/login?username=" + username
                                + "&password=" + password1;
                        OkHttpClient httpClient = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url(url)
                                .build();
                        try (final Response response = httpClient.newCall(request).execute();) {
                            final String responseBodyStr = Objects.requireNonNull(response.body()).string();
                            if (!TextUtils.isEmpty(responseBodyStr)) {
                                // 持久化数据
                                preferences.edit().putString("responseBodyStr", responseBodyStr).apply();

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 解析数据
                                        Gson gson = new Gson();
                                        ResultInfo resultInfo = gson.fromJson(responseBodyStr, ResultInfo.class);
                                        if (!resultInfo.isFlag()) {
                                            MyUtils.updateToast(LauncherActivity.this, resultInfo.getErrorMsg(), Toast.LENGTH_SHORT);
                                            loadingDialog.dismiss();
                                        } else {
                                            // 绑定成功
                                            Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                                            intent.putExtra("json_result", responseBodyStr);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                            } else {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadingDialog.dismiss();
                                        MyUtils.updateToast(LauncherActivity.this, "服务器异常", Toast.LENGTH_SHORT);
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

}
