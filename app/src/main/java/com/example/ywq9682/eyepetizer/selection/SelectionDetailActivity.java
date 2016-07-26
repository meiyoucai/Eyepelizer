package com.example.ywq9682.eyepetizer.selection;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.SelectionDetailAdapter;
import com.example.ywq9682.eyepetizer.base.BaseActivity;

/**
 * Created by dllo on 16/7/21.
 */
public class SelectionDetailActivity extends BaseActivity {
    private SelectionDetailAdapter selectionDetailAdapter;
    private ViewPager viewPager;

    @Override
    public int setLayout() {
        return R.layout.activity_selection_detail;
    }

    @Override
    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.fragment_view_pager);
    }

    @Override
    public void initData() {
        selectionDetailAdapter = new SelectionDetailAdapter(getSupportFragmentManager());
        viewPager.setAdapter(selectionDetailAdapter);
    }
}
