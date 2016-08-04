package com.example.ywq9682.eyepetizer.discover;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.DiscoverAdapter;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.example.ywq9682.eyepetizer.bean.AllBean;
import com.example.ywq9682.eyepetizer.bean.DiscoverBean;
import com.example.ywq9682.eyepetizer.discover.banner.BannerSearchActivity;
import com.example.ywq9682.eyepetizer.discover.banner.BannerWeb;
import com.example.ywq9682.eyepetizer.discover.banner.DeckViewSampleActivity;
import com.example.ywq9682.eyepetizer.discover.gridview.GridviewActivity;
import com.example.ywq9682.eyepetizer.discover.top.TopActivity;
import com.example.ywq9682.eyepetizer.discover.topic.TopicActivity;
import com.example.ywq9682.eyepetizer.discover.vr.VrActivity;
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

        discoverAdapter.setClickPicture(new DiscoverAdapter.ClickPicture() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(context, GridviewActivity.class);
                // Toast.makeText(context, "discoverBean.getItemList().get(position + 4).getData().getId():" + discoverBean.getItemList().get(position + 4).getData().getId(), Toast.LENGTH_SHORT).show();
                intent.putExtra("gridviewId", discoverBean.getItemList().get(position + 4).getData().getId());
                context.startActivity(intent);
            }
        });
    }

    private void initBanner() {
        view2 = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
//        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
//        layoutParams.height = Utils.getScreenHeight(context);
//        layoutParams.width = (int) (Utils.getScreenWidth(context) * 0.5f);
//        view2.setLayoutParams(layoutParams);
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

                if (discoverBean.getItemList().get(0).getData().getItemList().get(pos).getData().getActionUrl().equals("eyepetizer://webview/?title=%E5%8E%9F%E6%9D%A5%E4%BD%A0%E4%B9%9F%E6%98%AF%E4%B8%80%E5%8F%AA%E7%8C%AB%E5%A5%B4&url=http%3A%2F%2Fwww.wandoujia.com%2Feyepetizer%2Farticle.html%3Fnid%3D935%26shareable%3Dtrue")) {
                    Intent intent = new Intent(context, BannerWeb.class);
                    // intent.putExtra("actionUrl",discoverBean.getItemList().get(0).getData().getItemList().get(pos).getData().getActionUrl().substring(149,247));
                    context.startActivity(intent);

                } else if (discoverBean.getItemList().get(0).getData().getItemList().get(pos).getData().getActionUrl().equals("eyepetizer://recommend/")) {

                    Intent intent1 = new Intent(context, BannerSearchActivity.class);
                    context.startActivity(intent1);
                } else if (discoverBean.getItemList().get(0).getData().getItemList().get(pos).getData().getActionUrl().equals("eyepetizer://webview/?title=%E5%85%B3%E4%BA%8E%E5%A4%8F%E5%A4%A9%E7%9A%84%E6%89%80%E6%9C%89%E9%81%90%E6%83%B3&url=http%3A%2F%2Fwww.wandoujia.com%2Feyepetizer%2Fcollection.html%3Fname%3Dsummer%26shareable%3Dtrue")) {

                    Intent intent2 = new Intent(context, DeckViewSampleActivity.class);
                    context.startActivity(intent2);

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
                Intent intent = new Intent(context, TopicActivity.class);
                context.startActivity(intent);
            }
        });

        Glide.with(context).load(discoverBean.getItemList().get(3).getData().getImage()).into(imageVR);
        imageVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VrActivity.class);
                context.startActivity(intent);


            }
        });
        headerGridView.addHeaderView(view1);

    }
}
