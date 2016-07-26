package com.example.ywq9682.eyepetizer.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.bean.SelectionListBean;
import com.example.ywq9682.eyepetizer.selection.FlyTextView;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/21.
 */
public class SelectionDetailViewAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<SelectionListBean> selectionListBean;
    private int pos;

    public void setPos(int pos) {
        this.pos = pos;
    }

    public SelectionDetailViewAdapter(Context context) {
        this.context = context;
    }

    public void setSelectionListBean(ArrayList<SelectionListBean> selectionListBean) {
        this.selectionListBean = selectionListBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return selectionListBean == null ? 0 : selectionListBean.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position=position+pos;
        View view = LayoutInflater.from(context).inflate(R.layout.item_selection_detail_view, container, false);
        ImageView selectionDetailIv = (ImageView) view.findViewById(R.id.selection_detail_iv);
        ImageView selectionBlurredIv = (ImageView) view.findViewById(R.id.selection_blurred_iv);
        ImageView selectionPlay = (ImageView) view.findViewById(R.id.selection_play);
        FlyTextView selectionTitle = (FlyTextView) view.findViewById(R.id.selection_title);
        TextView selectionCategory = (TextView) view.findViewById(R.id.selection_category);
        TextView selectionDuration = (TextView) view.findViewById(R.id.selection_duration);
        FlyTextView selectionDescription = (FlyTextView) view.findViewById(R.id.selection_description);
        TextView selectionCollectionCountTv = (TextView) view.findViewById(R.id.selection_collectionCount_tv);
        TextView selectionShareCountTv = (TextView) view.findViewById(R.id.selection_shareCount_tv);
        TextView selectionReplyCountTv = (TextView) view.findViewById(R.id.selection_reply_count_tv);

        Glide.with(context).load(selectionListBean.get(position).getImageUrl()).into(selectionDetailIv);
        Glide.with(context).load(selectionListBean.get(position).getBlurredUrl()).into(selectionBlurredIv);

        selectionDescription.setTextColor(Color.WHITE);
        selectionDescription.setTextSize(12);
        selectionTitle.setTextColor(Color.WHITE);
        selectionTitle.setTextSize(15);
        selectionTitle.setTexts(selectionListBean.get(position).getTitle());
        selectionCategory.setText("#" + selectionListBean.get(position).getCategory());
        int times = selectionListBean.get(position).getDuration();
        selectionDuration.setText(times / 600 + "" + times % 600 / 60 + "'" + times % 60 / 10 + "" + times % 60 % 10 + "''");
        selectionDescription.setTexts(selectionListBean.get(position).getDescription());
        selectionCollectionCountTv.setText(String.valueOf(selectionListBean.get(position).getCollectionCount()));
        selectionShareCountTv.setText(String.valueOf(selectionListBean.get(position).getShareCount()));
        selectionReplyCountTv.setText(String.valueOf(selectionListBean.get(position).getReplyCount()));

        selectionTitle.startAnimation();
        selectionDescription.startAnimation();

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        selectionPlay.startAnimation(alphaAnimation);


        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(selectionDetailIv, "scaleX", 1, 1.2f);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(selectionDetailIv, "scaleY", 1, 1.2f);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(5000);
        objectAnimator.setDuration(5000);
        animator.start();
        objectAnimator.start();
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position) == object) {
            container.removeViewAt(position);
        }
    }
}
