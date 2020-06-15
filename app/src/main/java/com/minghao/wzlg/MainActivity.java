package com.minghao.wzlg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.minghao.wzlg.domain.CourseTable;
import com.minghao.wzlg.domain.Student;
import com.minghao.wzlg.domain.Week;
import com.minghao.wzlg.util.MyUtils;
import com.minghao.wzlg.util.UpdateManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private MyFragmentPagerAdapter mAdapter;
    private List<Fragment> fragments;
    private boolean weekendVisibility = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 检查更新
        UpdateManager.checkUpdate(this);

        Intent intent = getIntent();
        Student student = null;
        if (intent != null) {
            String json_result = intent.getStringExtra("json_result");
            if (json_result != null) {
                student = MyUtils.getStudentByInfoJson(json_result);
            }
        }
        viewPager2 = findViewById(R.id.viewPager2);
        // 准备fragments
        fragments = new ArrayList<>();
        List<Week> weeks = student.getTable().getWeeks();
        for (int i = 0; i < weeks.size(); i++) {
            fragments.add(CourseTableFragment.newInstance(weeks.get(i)));
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int weekIndex = calendar.get(Calendar.WEEK_OF_YEAR) - 9;
        if (weekIndex < 0) {
            weekIndex = 0;
        }
        if (weekIndex > 24) {
            weekIndex = 24;
        }
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager2.setAdapter(mAdapter);
        viewPager2.setCurrentItem(weekIndex, false);
        setTitle("第 " + (weekIndex + 1) + " 周 (滑动页面切换)");
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setTitle("第 " + (position + 1) + " 周 (滑动页面切换)");
            }
        });
        viewPager2.setOffscreenPageLimit(3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.logout) {
            SharedPreferences preferences = getSharedPreferences("responseBodyStr", MODE_PRIVATE);
            preferences.edit().clear().apply();
            Intent intent = new Intent(MainActivity.this, LauncherActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (itemId == R.id.item_weekend_exchange) {
            CourseTableFragment fragment = (CourseTableFragment) fragments.get(viewPager2.getCurrentItem());
            if (item.getTitle().equals("隐藏周末")) {
                fragment.setWeenkendVisibility(false);
                item.setTitle("显示周末");
            } else {
                fragment.setWeenkendVisibility(true);
                item.setTitle("隐藏周末");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
