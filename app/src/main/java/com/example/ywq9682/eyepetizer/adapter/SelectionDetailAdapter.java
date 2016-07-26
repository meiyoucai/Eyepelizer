package com.example.ywq9682.eyepetizer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ywq9682.eyepetizer.selection.SelectionDetailFragment;

/**
 * Created by dllo on 16/7/21.
 */
public class SelectionDetailAdapter extends FragmentStatePagerAdapter {
    public SelectionDetailAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return SelectionDetailFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

}
