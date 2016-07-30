package com.example.ywq9682.eyepetizer.discover.top;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/29.
 */
public class TopAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    private String[] a = {"周排行", "月排行", "总排行"};

    public TopAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.size() > 0 && fragments != null ? fragments.get(position) : null;
    }

    @Override
    public int getCount() {
        return fragments.size() > 0 && fragments != null ? fragments.size() : null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return a[position];
    }
}
