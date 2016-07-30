package com.example.ywq9682.eyepetizer.discover;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.DiscoverAdapter;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.example.ywq9682.eyepetizer.bean.AllBean;
import com.example.ywq9682.eyepetizer.bean.DiscoverBean;
import com.example.ywq9682.eyepetizer.discover.banner.BannerSearchActivity;
import com.example.ywq9682.eyepetizer.discover.banner.BannerWeb;
import com.example.ywq9682.eyepetizer.discover.gridview.GridviewActivity;
import com.example.ywq9682.eyepetizer.discover.top.TopActivity;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by YWQ9682 on 2016/7/16.
 */
public class DiscoverFragment extends BaseFragment {
    private HeaderGridView headerGridView;
    private DiscoverAdapter discoverAdapter;
    private DiscoverBean discoverBean;
    private Banner banner;
    private String[] imageUrls;
    private String url = AllBean.DISCOVER_URL;
    private ImageView imageTOP, imageTOPIC, imageVR;
    View view1, view2;

    @Override
    public int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView(View view) {
        headerGridView = (HeaderGridView) view.findViewById(R.id.discover_headergridview);
    }

    @Override
    protected void initData() {
        discoverAdapter = new DiscoverAdapter(getContext());
        discoverBean = new DiscoverBean();
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                discoverBean = gson.fromJson(response, DiscoverBean.class);
                initBanner();
                initHead();
                discoverAdapter.setDiscoverBean(discoverBean);
                headerGridView.setAdapter(discoverAdapter);

            }
        });
//        headerGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(context, "i:" + i, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, GridviewActivity.class);
//                intent.putExtra("gridviewId", discoverBean.getItemList().get(i + 4).getData().getId());
//                Log.d("DiscoverFragment", "discoverBean.getItemList().get(i).getData().getId():" + discoverBean.getItemList().get(i).getData().getId());
//                context.startActivity(intent);
//            }
//        });
        discoverAdapter.setClickPicture(new DiscoverAdapter.ClickPicture() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(context, GridviewActivity.class);
                Toast.makeText(context, "discoverBean.getItemList().get(position + 4).getData().getId():" + discoverBean.getItemList().get(position + 4).getData().getId(), Toast.LENGTH_SHORT).show();
                intent.putExtra("gridviewId", discoverBean.getItemList().get(position + 4).getData().getId());
                context.startActivity(intent);
            }
        });
    }

    private void initBanner() {
        view2 = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
        banner = (Banner) view2.findViewById(R.id.banner);
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);//加点
        banner.setIndicatorGravity(Banner.CENTER);//点在中间
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                discoverBean = gson.fromJson(response, DiscoverBean.class);
                imageUrls = new String[discoverBean.getItemList().get(0).getData().getItemList().size()];
                for (int i = 0; i < discoverBean.getItemList().get(0).getData().getItemList().size(); i++) {
                    imageUrls[i] = discoverBean.getItemList().get(0).getData().getItemList().get(i).getData().getImage();
                }
                banner.setImages(imageUrls);
                banner.setDelayTime(3000);
            }
        });
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {

                int pos = position - 1;

                if (discoverBean.getItemList().get(0).getData().getItemList().get(pos).getData().getActionUrl().equals("eyepetizer://webview/?title=%E9%82%A3%E4%BA%9B%E5%9C%A8%E7%BB%BC%E8%89%BA%E4%B8%AD%E8%B6%85%E7%BA%A7%E6%8B%BC%E7%9A%84%E9%A2%86%E5%AF%BC%E4%BA%BA&url=http%3A%2F%2Fwww.wandoujia.com%2Feyepetizer%2Fcollection.html%3Fname%3Dpresident%26shareable%3Dtrue")) {
                    Intent intent = new Intent(context, BannerWeb.class);
                    // intent.putExtra("actionUrl",discoverBean.getItemList().get(0).getData().getItemList().get(pos).getData().getActionUrl().substring(149,247));
                    context.startActivity(intent);
                } else if (discoverBean.getItemList().get(0).getData().getItemList().get(pos).getData().getActionUrl().equals("eyepetizer://recommend/")) {

                    Intent intent1 = new Intent(context, BannerSearchActivity.class);
                    context.startActivity(intent1);
                }
            }
        });
        headerGridView.addHeaderView(view2);

    }

    private void initHead() {

        view1 = LayoutInflater.from(context).inflate(R.layout.item_discover_head, null);
        imageTOP = (ImageView) view1.findViewById(R.id.imageTOP);
        imageTOPIC = (ImageView) view1.findViewById(R.id.imageTOPIC);
        imageVR = (ImageView) view1.findViewById(R.id.imageVR);
        Glide.with(context).load(discoverBean.getItemList().get(1).getData().getImage()).into(imageTOP);

        imageTOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, TopActivity.class);
                context.startActivity(intent);
            }
        });
        Glide.with(context).load(discoverBean.getItemList().get(2).getData().getImage()).into(imageTOPIC);
        imageTOPIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Glide.with(context).load(discoverBean.getItemList().get(3).getData().getImage()).into(imageVR);
        imageVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "它就是一个listview里面放入item,拼接就可以了", Toast.LENGTH_SHORT).show();
            }
        });
        headerGridView.addHeaderView(view1);

    }
}
