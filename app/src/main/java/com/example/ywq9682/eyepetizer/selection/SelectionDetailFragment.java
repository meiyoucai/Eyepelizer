package com.example.ywq9682.eyepetizer.selection;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.SelectionDetailViewAdapter;
import com.example.ywq9682.eyepetizer.author.authordetial.AuthorDetial;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.example.ywq9682.eyepetizer.bean.SelectionListBean;
import com.example.ywq9682.eyepetizer.video.VideoPlayerActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/21.
 */
public class SelectionDetailFragment extends BaseFragment implements View.OnTouchListener, ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager viewPager;
    private SelectionDetailViewAdapter selectionDetailViewAdapter;
    private ArrayList<SelectionListBean> selectionListBean;
    private RelativeLayout bottomLayout;
    private ImageView selectionDetailIv, selectionBlurredIv, selectionPlay, selectionReturn,
            selectionMore;
    private FlyTextView selectionTitle, selectionDescription;
    private TextView selectionCategory, selectionDuration, selectionCollectionCountTv,
            selectionShareCountTv, selectionReplyCountTv;
    private LinearLayout selectionFavorites, selectionShare, selectionReply, selectionCache;
    private float mStartX;
    private int mCurrentPostion;
    private int position;

    public static SelectionDetailFragment getInstance(int pos) {
        SelectionDetailFragment selectionDetailFragment = new SelectionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("p", pos);
        selectionDetailFragment.setArguments(bundle);
        return selectionDetailFragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_selection_detail;
    }


    @Override
    public void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        bottomLayout = (RelativeLayout) view.findViewById(R.id.bottom_layout);
        selectionDetailIv = (ImageView) view.findViewById(R.id.selection_detail_iv);
        selectionBlurredIv = (ImageView) view.findViewById(R.id.selection_blurred_iv);
        selectionPlay = (ImageView) view.findViewById(R.id.selection_play);
        selectionReturn = (ImageView) view.findViewById(R.id.selection_detail_return);
        selectionMore = (ImageView) view.findViewById(R.id.selection_more);
        selectionTitle = (FlyTextView) view.findViewById(R.id.selection_title);
        selectionCategory = (TextView) view.findViewById(R.id.selection_category);
        selectionDuration = (TextView) view.findViewById(R.id.selection_duration);
        selectionDescription = (FlyTextView) view.findViewById(R.id.selection_description);
        selectionCollectionCountTv = (TextView) view.findViewById(R.id.selection_collection_count);
        selectionShareCountTv = (TextView) view.findViewById(R.id.selection_share_count);
        selectionReplyCountTv = (TextView) view.findViewById(R.id.selection_reply_count);
        selectionFavorites = (LinearLayout) view.findViewById(R.id.selection_favorites);
        selectionShare = (LinearLayout) view.findViewById(R.id.selection_share);
        selectionReply = (LinearLayout) view.findViewById(R.id.selection_reply);
        selectionCache = (LinearLayout) view.findViewById(R.id.selection_cache);
    }


    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        position = intent.getIntExtra("position", 0);
        selectionDetailViewAdapter = new SelectionDetailViewAdapter(getContext());
        selectionListBean = getActivity().getIntent().getParcelableArrayListExtra("selectionListBean");
        selectionDetailViewAdapter.setSelectionListBean(selectionListBean);
        viewPager.setAdapter(selectionDetailViewAdapter);
        viewPager.setCurrentItem(position);
        viewPager.setOnTouchListener(this);
        viewPager.addOnPageChangeListener(this);
        setData(position);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        selectionReturn.setOnClickListener(this);
        selectionMore.setOnClickListener(this);
        selectionPlay.setOnClickListener(this);
        selectionFavorites.setOnClickListener(this);
        selectionShare.setOnClickListener(this);
        selectionReply.setOnClickListener(this);
        selectionCache.setOnClickListener(this);
    }

    public void setData(int position) {
        Glide.with(this).load(selectionListBean.get(position).getBlurredUrl()).into(selectionBlurredIv);
        if (selectionListBean.get(position).getBlurredUrl() == null) {
            selectionPlay.setVisibility(View.GONE);
        }
        selectionDescription.setTextColor(Color.WHITE);
        selectionDescription.setTextSize(12);
        selectionTitle.setTextColor(Color.WHITE);
        selectionTitle.setTextSize(15);
        selectionTitle.setTexts(selectionListBean.get(position).getTitle());
        selectionCategory.setText(selectionListBean.get(position).getCategory());
        selectionDuration.setText(selectionListBean.get(position).getTime());
        selectionDescription.setTexts(selectionListBean.get(position).getDescription());
        selectionCollectionCountTv.setText(String.valueOf(selectionListBean.get(position).getCollectionCount()));
        selectionShareCountTv.setText(String.valueOf(selectionListBean.get(position).getShareCount()));
        selectionReplyCountTv.setText(String.valueOf(selectionListBean.get(position).getReplyCount()));

        selectionTitle.startAnimation();
        selectionDescription.startAnimation();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        selectionPlay.startAnimation(alphaAnimation);
        selectionBlurredIv.startAnimation(alphaAnimation);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int pageWidth = selectionDetailIv.getWidth();
        // Counteract the default slide transition
        selectionDetailIv.setTranslationX(pageWidth * -position);
        // Scale the page down (between MIN_SCALE and 1)
        float scaleFactor = (1 - Math.abs(position));
        selectionDetailIv.setScaleX(scaleFactor);
        selectionDetailIv.setScaleY(scaleFactor);
        //ViewPager底部动画效果
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int mScreenWidth = dm.widthPixels;
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = motionEvent.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = (int) motionEvent.getX();
                float offX = moveX - mStartX;
                //如果是最后一页则不处理alpha动画
                if (mCurrentPostion == selectionListBean.size() - 1) {
                    return false;
                }
                float alpha = Math.abs(offX) / mScreenWidth * 1.0f;
                bottomLayout.setAlpha(1 - alpha);
                break;
            case MotionEvent.ACTION_UP:
                bottomLayout.setAlpha(1);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int positions) {
        mCurrentPostion = positions;
        setData(positions);
        position = positions;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selection_play:
                Intent intentVideo = new Intent(context, VideoPlayerActivity.class);
                intentVideo.putExtra("urlS", selectionListBean.get(position).getPlayUrl());
                intentVideo.putExtra("title", selectionListBean.get(position).getTitle());
                startActivity(intentVideo);
                break;
            case R.id.selection_detail_return:
                Intent intentList=new Intent(context.getPackageName()+"selectionListView");
                intentList.putExtra("itemPosition",position);
                context.sendBroadcast(intentList);
                getActivity().finish();
                break;
            case R.id.selection_more:
                Intent intent=new Intent(context, AuthorDetial.class);
                intent.putExtra("bcId",selectionListBean.get(position).getId());
                break;
            case R.id.selection_favorites:
                Toast.makeText(context, "已加入收藏夹", Toast.LENGTH_SHORT).show();
                break;
            case R.id.selection_share:
                break;
            case R.id.selection_reply:
                break;
            case R.id.selection_cache:
                break;
        }

    }
}
