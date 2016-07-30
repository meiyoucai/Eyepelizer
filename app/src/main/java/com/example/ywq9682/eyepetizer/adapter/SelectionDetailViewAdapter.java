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


    private Context context;
    private ArrayList<SelectionListBean> selectionListBean;
    private int pos,positions;

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

    @Override

    public Object instantiateItem(ViewGroup container, final int position) {
        positions=position+pos;
        View view = LayoutInflater.from(context).inflate(R.layout.item_selection_detail_view, container, false);
        ImageView selectionDetailIv = (ImageView) view.findViewById(R.id.selection_detail_iv);
        ImageView selectionBlurredIv = (ImageView) view.findViewById(R.id.selection_blurred_iv);
        ImageView selectionPlay = (ImageView) view.findViewById(R.id.selection_play);
        FlyTextView selectionTitle = (FlyTextView) view.findViewById(R.id.selection_title);
        TextView selectionCategory = (TextView) view.findViewById(R.id.selection_category);
        TextView selectionDuration = (TextView) view.findViewById(R.id.selection_duration);
        FlyTextView selectionDescription = (FlyTextView) view.findViewById(R.id.selection_description);
        TextView selectionCollectionCountTv = (TextView) view.findViewById(R.id.selection_collectionCount);
        TextView selectionShareCountTv = (TextView) view.findViewById(R.id.selection_shareCount_tv);
        TextView selectionReplyCountTv = (TextView) view.findViewById(R.id.selection_reply_count_tv);
        selectionPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("urlS",selectionListBean.get(positions).getPlayUrl());
                intent.putExtra("title",selectionListBean.get(positions).getTitle());
                Log.d("ssdd", selectionListBean.get(positions).getPlayUrl());
                Log.d("ssdd", selectionListBean.get(positions).getTitle());
                context.startActivity(intent);
            }
        });

        Glide.with(context).load(selectionListBean.get(positions).getImageUrl()).into(selectionDetailIv);
        Glide.with(context).load(selectionListBean.get(positions).getBlurredUrl()).into(selectionBlurredIv);

        selectionDescription.setTextColor(Color.WHITE);
        selectionDescription.setTextSize(12);
        selectionTitle.setTextColor(Color.WHITE);
        selectionTitle.setTextSize(15);
        selectionTitle.setTexts(selectionListBean.get(positions).getTitle());
        selectionCategory.setText("#" + selectionListBean.get(positions).getCategory());
        int times = selectionListBean.get(positions).getDuration();
        selectionDuration.setText(times / 600 + "" + times % 600 / 60 + "'" + times % 60 / 10 + "" + times % 60 % 10 + "''");
        selectionDescription.setTexts(selectionListBean.get(positions).getDescription());
        selectionCollectionCountTv.setText(String.valueOf(selectionListBean.get(positions).getCollectionCount()));
        selectionShareCountTv.setText(String.valueOf(selectionListBean.get(positions).getShareCount()));
        selectionReplyCountTv.setText(String.valueOf(selectionListBean.get(positions).getReplyCount()));

        selectionTitle.startAnimation();
        selectionDescription.startAnimation();

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        selectionPlay.startAnimation(alphaAnimation);


        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(selectionDetailIv, "scaleX", 1, 1.2f);

    public Object instantiateItem(ViewGroup container, int position) {
        pos=position;
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
