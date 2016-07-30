package com.example.ywq9682.eyepetizer.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.bean.SelectionListBean;
import com.example.ywq9682.eyepetizer.selection.FlyTextView;
import com.example.ywq9682.eyepetizer.video.VideoPlayerActivity;


import java.util.ArrayList;

/**
 * Created by dllo on 16/7/21.
 */
public class SelectionDetailViewAdapter extends PagerAdapter {

    private int pos;

    public void setPos(int pos) {
        this.pos = pos;
    }
    private Context context;
    private ArrayList<SelectionListBean> selectionListBean;

    public SelectionDetailViewAdapter(Context context) {
        this.context = context;
    }

    public void setSelectionListBean(ArrayList<SelectionListBean> selectionListBean) {
        this.selectionListBean = selectionListBean;
        notifyDataSetChanged();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        pos = position;
        View view = LayoutInflater.from(context).inflate(R.layout.item_selection_detail_view, container, false);
        ImageView selectionDetailIv = (ImageView) view.findViewById(R.id.selection_detail_iv);
        Glide.with(context).load(selectionListBean.get(position).getImageUrl()).into(selectionDetailIv);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(selectionDetailIv, "scaleX", 1, 1.1f);

        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(selectionDetailIv, "scaleY", 1, 1.1f);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(5000);
        objectAnimator.setDuration(5000);
        animator.start();
        objectAnimator.start();
        if (selectionListBean.get(position).getImageUrl() != null) {

            container.addView(view);
        }
        return view;
    }

    @Override
    public int getCount() {

        return Integer.MAX_VALUE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position) == object) {
            container.removeViewAt(position);
        }
    }

}
