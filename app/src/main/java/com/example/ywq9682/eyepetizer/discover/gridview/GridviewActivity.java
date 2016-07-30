package com.example.ywq9682.eyepetizer.discover.gridview;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/29.
 */
public class GridviewActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragments;
    private GridviewAdapter gridviewAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_discover_gridview;
    }

    @Override
    public void initView() {
        gridviewAdapter = new GridviewAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.viewpager_discover_gridview);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_gridview_discover);
        fragments.add(new TimeFragment());
        fragments.add(new EnjoyFragement());
        gridviewAdapter.setFragments(fragments);
        viewPager.setAdapter(gridviewAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initData() {

    }
}
