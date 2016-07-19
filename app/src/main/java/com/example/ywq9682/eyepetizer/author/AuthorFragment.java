package com.example.ywq9682.eyepetizer.author;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.AuthorRecyAdapter;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.example.ywq9682.eyepetizer.bean.AllBean;
import com.example.ywq9682.eyepetizer.bean.Author;
import com.example.ywq9682.eyepetizer.bean.AuthorBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by YWQ9682 on 2016/7/16.
 */
public class AuthorFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private AuthorBean author;
    private AuthorRecyAdapter authorRecyAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_author;
    }

    @Override
    public void initView(View view) {

        authorRecyAdapter = new AuthorRecyAdapter(context);
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_author);
        Log.d("wwww", "sss");

    }

    @Override
    protected void initData() {
        getNet();
    }

    public void getNet() {
        OkHttpUtils.get().url(AllBean.AUTHOR_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(context, "获取网络数据失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("wwww", response);
                Gson gson = new Gson();
                author = gson.fromJson(response, AuthorBean.class);
                authorRecyAdapter.setAuthor(author);
                recyclerView.setAdapter(authorRecyAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                Log.d("wwww", "author.getCount():" + author.getCount());
            }
        });

    }
}
