package com.minghao.wzlg;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minghao.wzlg.databinding.CourseTableFragmentBinding;
import com.minghao.wzlg.domain.Week;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CourseTableFragment extends Fragment {

    private CourseTableViewModel mViewModel;
    private CourseTableFragmentBinding binding;
    private Week week;

    static CourseTableFragment newInstance(Week week) {
        CourseTableFragment courseTableFragment = new CourseTableFragment();
        courseTableFragment.setWeek(week);
        return courseTableFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(CourseTableViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.setContext(requireContext());
        mViewModel.setWeek(week);
        binding = DataBindingUtil.inflate(inflater, R.layout.course_table_fragment, container, false);
        binding.setWeek(week);
        binding.setToday(new SimpleDateFormat("MM-dd", Locale.CHINA).format(new Date()));
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public void setWeenkendVisibility(boolean weekendVisibility) {
        binding.layoutSaturday.setVisibility(weekendVisibility ? View.VISIBLE : View.GONE);
        binding.layoutSunday.setVisibility(weekendVisibility ? View.VISIBLE : View.GONE);
    }
}
