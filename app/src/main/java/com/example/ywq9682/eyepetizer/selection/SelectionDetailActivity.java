package com.example.ywq9682.eyepetizer.selection;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.adapter.SelectionDetailViewAdapter;
import com.example.ywq9682.eyepetizer.author.authordetial.detialnext.DetialNextActivity;
import com.example.ywq9682.eyepetizer.base.BaseActivity;
import com.example.ywq9682.eyepetizer.base.SingleLiteOrm;
import com.example.ywq9682.eyepetizer.bean.SelectionListBean;
import com.example.ywq9682.eyepetizer.tools.Window;
import com.example.ywq9682.eyepetizer.video.VideoPlayerActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/21.
 */
public class SelectionDetailActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, View.OnTouchListener {
    private ViewPager viewPager;
    private SelectionDetailViewAdapter selectionDetailViewAdapter;
    private ArrayList<SelectionListBean> selectionListBean;
    private RelativeLayout bottomLayout, selectionDetail;
    private ImageView selectionDetailIv, selectionBlurredIv, selectionPlay, selectionReturn,
            selectionMore, selectionAnim;
    private FlyTextView selectionTitle, selectionDescription;
    private TextView selectionCategory, selectionDuration, selectionCollectionCountTv,
            selectionShareCountTv, selectionReplyCountTv, selectionTextHeader;
    private LinearLayout selectionFavorites, selectionShare, selectionReply, selectionCache, selectionDetailLauncher;
    private float mStartX;
    private int mCurrentPostion;
    private int position;
    private CheckBox checkBox;

    @Override
    public int setLayout() {
        return R.layout.fragment_selection_detail;
    }

    @Override
    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        bottomLayout = (RelativeLayout) findViewById(R.id.bottom_layout);
        selectionDetailIv = (ImageView) findViewById(R.id.selection_detail_iv);
        selectionBlurredIv = (ImageView) findViewById(R.id.selection_blurred_iv);
        selectionPlay = (ImageView) findViewById(R.id.selection_play);
        selectionReturn = (ImageView) findViewById(R.id.selection_detail_return);
        selectionMore = (ImageView) findViewById(R.id.selection_more);
        selectionAnim = (ImageView) findViewById(R.id.selection_anim);
        selectionTitle = (FlyTextView) findViewById(R.id.selection_title);
        selectionCategory = (TextView) findViewById(R.id.selection_category);
        selectionDuration = (TextView) findViewById(R.id.selection_duration);
        selectionTextHeader = (TextView) findViewById(R.id.selection_text_header);
        selectionDescription = (FlyTextView) findViewById(R.id.selection_description);
        selectionCollectionCountTv = (TextView) findViewById(R.id.selection_collection_count);
        selectionShareCountTv = (TextView) findViewById(R.id.selection_share_count);
        selectionReplyCountTv = (TextView) findViewById(R.id.selection_reply_count);
        selectionFavorites = (LinearLayout) findViewById(R.id.selection_favorites);
        selectionShare = (LinearLayout) findViewById(R.id.selection_share);
        selectionReply = (LinearLayout) findViewById(R.id.selection_reply);
        selectionCache = (LinearLayout) findViewById(R.id.selection_cache);
        selectionDetail = (RelativeLayout) findViewById(R.id.selection_detail);
        selectionDetailLauncher = (LinearLayout) findViewById(R.id.selection_detail_lanucher);

        checkBox = (CheckBox) findViewById(R.id.check_selection);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkBox.isChecked()) {
                    Toast.makeText(SelectionDetailActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
                    CollectBean collectBean = new CollectBean();
                    collectBean.setTitleTv(selectionListBean.get(position).getTitle());
                    collectBean.setTimeTv(selectionListBean.get(position).getTime());
                    collectBean.setCategoryTv(selectionListBean.get(position).getCategory());
                    collectBean.setImageView(selectionListBean.get(position).getImageUrl());

//                    SingleLiteOrm.getSingleLiteOrm().insertSingle(collectBean);


                } else {

                    Toast.makeText(SelectionDetailActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();

//                    SingleLiteOrm.getSingleLiteOrm().delectSingleCondition(CollectBean.class, "titleTv", selectionListBean.get(position).getTitle());


                }


            }
        });
    }

    @Override
    public void initData() {
        selectionAnim.setImageResource(R.mipmap.ic_eye_white_outer);

        //注册EventBus
        EventBus.getDefault().register(this);
        //SelectionAdapter跳转的位置position
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        selectionDetailViewAdapter = new SelectionDetailViewAdapter(this);
        //获取传递的数据
        selectionListBean = getIntent().getParcelableArrayListExtra("selectionListBean");
        selectionDetailViewAdapter.setSelectionListBean(selectionListBean);
        viewPager.setAdapter(selectionDetailViewAdapter);
        viewPager.setCurrentItem(position);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnTouchListener(this);
        viewPager.addOnPageChangeListener(this);
        setData(position);
        //发送广播传值当前位置
        Intent intentList = new Intent(getPackageName() + "selectionListView");
        intentList.putExtra("itemPosition", position);
        sendBroadcast(intentList);
        //切换动画
        viewPager.setPageTransformer(true, new DepthPageTransformer());


        selectionReturn.setOnClickListener(this);
        selectionMore.setOnClickListener(this);
        selectionPlay.setOnClickListener(this);
        selectionFavorites.setOnClickListener(this);
        selectionShare.setOnClickListener(this);
        selectionReply.setOnClickListener(this);
        selectionCache.setOnClickListener(this);
    }

    //接收刷新后传递的数据
    @Subscribe
    public void getDatas(ArrayList<SelectionListBean> bean) {
        selectionListBean = bean;
        selectionDetailViewAdapter.setSelectionListBean(selectionListBean);
    }

    public void setData(int position) {
        Glide.with(this).load(selectionListBean.get(position).getBlurredUrl()).into(selectionBlurredIv);
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
        selectionTextHeader.setText(selectionListBean.get(position).getText());
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Lobster.ttf");
        selectionTextHeader.setTypeface(typeface);
        selectionTitle.startAnimation();
        selectionDescription.startAnimation();

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        selectionPlay.startAnimation(alphaAnimation);
        selectionBlurredIv.startAnimation(alphaAnimation);


//        for (CollectBean collectBean : SingleLiteOrm.getSingleLiteOrm().quaryAllSingle(CollectBean.class)) {
//            if (collectBean.getTitleTv().equals(selectionListBean.get(position).getTitle()))
//                checkBox.setChecked(true);
//        }

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
        int mScreenWidth = Window.getDisplayWidth(this);
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
//                selectionDetailLauncher.setAlpha(1 - alpha);
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
        if ("textHeader".equals(selectionListBean.get(positions).getType())
                || "banner1".equals(selectionListBean.get(positions).getType())) {
            selectionDetailLauncher.setVisibility(View.VISIBLE);
            AnimationDrawable drawable = (AnimationDrawable) selectionAnim.getBackground();
            drawable.start();
            selectionDetail.setVisibility(View.GONE);
        } else {
            selectionDetail.setVisibility(View.VISIBLE);
            selectionDetailLauncher.setVisibility(View.GONE);
        }
        mCurrentPostion = positions;
        setData(positions);
        position = positions;
        Intent intentList = new Intent(getPackageName() + "selectionListView");
        intentList.putExtra("itemPosition", position);
        sendBroadcast(intentList);
        //如果是倒数第二条数据发送广播上拉加载数据
        if (selectionListBean.size() == position + 1) {
            Intent intentRefresh = new Intent(getPackageName() + "selectionRefresh");
            sendBroadcast(intentRefresh);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selection_play:
                Intent intentVideo = new Intent(this, VideoPlayerActivity.class);
                intentVideo.putExtra("urlS", selectionListBean.get(position).getPlayUrl());
                intentVideo.putExtra("title", selectionListBean.get(position).getTitle());
                startActivity(intentVideo);
                break;
            case R.id.selection_detail_return:

                finish();
                break;
            case R.id.selection_more:
                Intent intent = new Intent(this, DetialNextActivity.class);
                intent.putExtra("ok", selectionListBean.get(position).getId());
                startActivity(intent);
                break;
            case R.id.selection_favorites:
                Toast.makeText(this, "已加入收藏夹", Toast.LENGTH_SHORT).show();
                break;
            case R.id.selection_share:
                break;
            case R.id.selection_reply:
                break;
            case R.id.selection_cache:
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
