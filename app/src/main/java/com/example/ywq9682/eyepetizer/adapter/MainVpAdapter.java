package com.example.ywq9682.eyepetizer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/16.
 */

public class MainVpAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment>fragments;
    private String title[]={"精选","发现","作者","我的"};


    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public MainVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments == null ? null : fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
