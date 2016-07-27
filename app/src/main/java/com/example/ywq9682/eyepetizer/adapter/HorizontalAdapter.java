package com.example.ywq9682.eyepetizer.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.bean.DiscoverBean;
import com.example.ywq9682.eyepetizer.main.MyApp;

/**
 * Created by dllo on 16/7/26.
 */
public class HorizontalAdapter extends PagerAdapter {
    private DiscoverBean.ItemListBean.DataBean discoverBean;
    private Context context;

    public HorizontalAdapter(Context context) {
        this.context = context;
    }

    public void setDiscoverBean(DiscoverBean.ItemListBean.DataBean discoverBean) {
        this.discoverBean = discoverBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        //设置无上限  可以随意滑动
        return discoverBean == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(MyApp.context);
        Glide.with(context).load(discoverBean.getItemList().get(position % 2).getData().getImage()).into(imageView);
        container.addView(imageView);
        return container;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position % 2) == object) {
            container.removeViewAt(position % 2);
        }

    }
}
