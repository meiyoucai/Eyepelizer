package com.example.ywq9682.eyepetizer.author;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.AuthorRecyAdapter;
import com.example.ywq9682.eyepetizer.allinterface.BriefOnClickListener;
import com.example.ywq9682.eyepetizer.allinterface.VideoBriefOnClickListener;
import com.example.ywq9682.eyepetizer.author.authordetial.AuthorDetial;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.example.ywq9682.eyepetizer.bean.AllBean;
import com.example.ywq9682.eyepetizer.bean.AuthorBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by YWQ9682 on 2016/7/16.
 */
public class AuthorFragment extends BaseFragment implements BriefOnClickListener, VideoBriefOnClickListener {
    private RecyclerView recyclerView;
    private AuthorBean author;
    private AuthorRecyAdapter authorRecyAdapter;
    private RelativeLayout relativeLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_author;
    }

    @Override
    public void initView(View view) {
        authorRecyAdapter = new AuthorRecyAdapter(context);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.briefcard_relative);
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_author);
        authorRecyAdapter.setBriefOnClickListener(this);
        authorRecyAdapter.setVideoBriefOnClickListener(this);
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

    @Override
    public void onClick(int position) {

        if (author.getItemList().get(position).getType().equals("briefCard")) {
            Intent intent = new Intent(context, AuthorDetial.class);
            intent.putExtra("bcId", author.getItemList().get(position).getData().getId());
            intent.putExtra("bcDescription", author.getItemList().get(position).getData().getDescription());
            intent.putExtra("bcTitle", author.getItemList().get(position).getData().getTitle());
            intent.putExtra("bcIcon", author.getItemList().get(position).getData().getIcon());
            // Log.d("lanou", "author.getItemList().get(position).getData().getId():" + author.getItemList().get(position).getData().getId());
            context.startActivity(intent);
        } else if (author.getItemList().get(position).getType().equals("videoCollectionWithBrief")) {
            Intent intent1 = new Intent(context, AuthorDetial.class);
            intent1.putExtra("bcId", author.getItemList().get(position).getData().getHeader().getId());
            intent1.putExtra("bcDescription", author.getItemList().get(position).getData().getHeader().getDescription());
            intent1.putExtra("bcTitle", author.getItemList().get(position).getData().getHeader().getTitle());
          //  Log.d("AuthorFragment", "author.getItemList().get(position).getData().getId():" + author.getItemList().get(position).getData().getId());
            intent1.putExtra("bcIcon", author.getItemList().get(position).getData().getHeader().getIcon());
            //Log.d("lanou", "author.getItemList().get(position).getData().getId():" + author.getItemList().get(position).getData().getId());
            context.startActivity(intent1);


        }

    }
}
