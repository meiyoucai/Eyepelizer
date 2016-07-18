package com.example.ywq9682.eyepetizer.discover;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseFragment;

/**
 * Created by YWQ9682 on 2016/7/16.
 */
public class DiscoverFragment extends BaseFragment {
    private RecyclerView recyclerView;


    @Override
    public int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView(View view) {

        initHead();


    }

    private void initHead() {
        

    }

    @Override
    protected void initData() {

    }
}
