package com.example.ywq9682.eyepetizer.selection;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.DiscoverAdapter;
import com.example.ywq9682.eyepetizer.adapter.SelectionAdapter;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.example.ywq9682.eyepetizer.bean.AllBean;
import com.example.ywq9682.eyepetizer.bean.DiscoverBean;
import com.example.ywq9682.eyepetizer.bean.SelectionBean;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by YWQ9682 on 2016/7/18.
 */
public class SelectionFragment extends BaseFragment {
    private ListView listView;
    private SelectionAdapter selectionAdapter;
    private SelectionBean selectionBean;
    private PullToRefreshListView pullToRefreshListView;


    @Override
    public int setLayout() {
        return R.layout.fragment_selection;
    }

    @Override
    public void initView(View view) {
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.selection_list_view);

    }

    @Override
    protected void initData() {
        selectionAdapter = new SelectionAdapter(context);
        selectionBean = new SelectionBean();
        OkHttpUtils.get().url(AllBean.SELECTION_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                selectionBean = gson.fromJson(response, SelectionBean.class);
                selectionAdapter.setSelectionBean(selectionBean);
                pullToRefreshListView.setAdapter(selectionAdapter);
            }
        });
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refresh();
            }


            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                loading();
            }


        });
    }

    //下拉刷新方法
    private void refresh() {
        OkHttpUtils.get().url(AllBean.SELECTION_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                selectionBean = gson.fromJson(response, SelectionBean.class);
                selectionAdapter.setSelectionBean(selectionBean);
                pullToRefreshListView.onRefreshComplete();
            }
        });
    }

    //上拉加载方法
    private void loading() {
        String nextPageUrl = selectionBean.getNextPageUrl();
        OkHttpUtils.get().url(nextPageUrl).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                selectionBean = gson.fromJson(response, SelectionBean.class);
                selectionAdapter.addSelectionBean(selectionBean);
                pullToRefreshListView.onRefreshComplete();
            }
        });
    }

}
