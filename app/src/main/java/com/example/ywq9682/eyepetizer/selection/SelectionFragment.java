package com.example.ywq9682.eyepetizer.selection;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseFragment;

/**
 * Created by YWQ9682 on 2016/7/18.
 */
public class SelectionFragment extends BaseFragment {
    private RecyclerView recyclerView;



    @Override
    public int setLayout() {
        return R.layout.fragment_selector;
    }

    @Override
    public void initView(View view) {

        recyclerView= (RecyclerView) view.findViewById(R.id.fragment_discover_recycler);

    }

    @Override
    protected void initData() {

    }
}
