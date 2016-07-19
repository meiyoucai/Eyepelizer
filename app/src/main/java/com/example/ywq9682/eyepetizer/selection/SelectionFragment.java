package com.example.ywq9682.eyepetizer.selection;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.DiscoverAdapter;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.example.ywq9682.eyepetizer.bean.AllBean;
import com.example.ywq9682.eyepetizer.bean.DiscoverBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by YWQ9682 on 2016/7/18.
 */
public class SelectionFragment extends BaseFragment {



    @Override
    public int setLayout() {
        return R.layout.fragment_selector;
    }

    @Override
    public void initView(View view) {


    }

    @Override
    protected void initData() {

    }
}
