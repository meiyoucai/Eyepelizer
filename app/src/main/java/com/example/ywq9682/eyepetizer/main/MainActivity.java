package com.example.ywq9682.eyepetizer.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.MainVpAdapter;
import com.example.ywq9682.eyepetizer.author.AuthorFragment;
import com.example.ywq9682.eyepetizer.base.BaseActivity;
import com.example.ywq9682.eyepetizer.bean.AllBean;
import com.example.ywq9682.eyepetizer.discover.DiscoverFragment;
import com.example.ywq9682.eyepetizer.my.MyFragment;
import com.example.ywq9682.eyepetizer.selection.SelectionFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragments;
    private MainVpAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_main);
        fragments = new ArrayList<>();
        fragments.add(new SelectionFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new AuthorFragment());
        fragments.add(new MyFragment());
        adapter = new MainVpAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorHeight(0);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        initTabIction();
    }

    private void initTabIction() {
        int[] selectors = {R.drawable.tab_selector_selection, R.drawable.tab_selector_discover,
                R.drawable.tab_selector_author, R.drawable.tab_selector_my};
        for (int i = 0; i < selectors.length; i++) {
            tabLayout.getTabAt(i).setIcon(selectors[i]);
        }
    }

    @Override
    public void initData() {

    }
}
