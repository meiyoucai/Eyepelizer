package com.example.ywq9682.eyepetizer.discover.top;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/29.
 */
public class TopActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TopAdapter topAdapter;

    private ArrayList<Fragment> fragments;


    @Override
    public int setLayout() {
        return R.layout.activity_top;
    }

    @Override
    public void initView() {
        fragments = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.viewpager_top);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_top);
        topAdapter = new TopAdapter(getSupportFragmentManager());
        fragments.add(new WeekFragment());
        fragments.add(new MonthFragment());
        fragments.add(new TotalFragment());
        topAdapter.setFragments(fragments);
        viewPager.setAdapter(topAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initData() {

    }
}
