package com.example.ywq9682.eyepetizer.author.authordetial;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;

import cn.carbs.android.avatarimageview.library.AvatarImageView;

/**
 * Created by dllo on 16/7/21.
 */

/*
               _ooOoo_
               o8888888o
              88" . "88
              (| -_- |)
              O\  =  /O
           ____/`---'\____
          .'  \\|     |//  `.
          /  \\|||  :  |||//  \
         /  _||||| -:- |||||-  \
        |   | \\\  -  /// |   |
        | \_|  ''\---/''  |   |
        \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
        ."" '<  `.___\_<|>_/___.'  >'"".
        | | :  `- \`.;`\ _ /`;.`/ - ` : | |
        \  \ `-.   \_ __\ /__ _/   .-` /  /
  ======`-.____`-.___\_____/___.-`____.-'======
 `=---='
 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
*
*/
public class AuthorDetial extends BaseActivity implements OnScrollListener, View.OnClickListener {

    private RadioGroup radioGroupLayout;
    private MyScrollView myScrollView;
    private int surplusHeight;//剩余的高度
    private int mSurplusHeight;
    private int radioGroupHeight;
    LinearLayout replaceLayout, suspensionLayout;//替换的布局和悬浮的布局
    LinearLayout headLayout;//头
    private RadioButton timeRb, enjoyRb;
    private RelativeLayout titleLayout;//标题布局
    private AvatarImageView personal;
    private ImageView messageIv;
    private TextView title, name, content;
    private LinearLayout nameLinear, replace_title;
    private int nameHeight;


    @Override
    public int setLayout() {
        return R.layout.activity_authordetial;
    }

    @Override
    public void initView() {
        timeRb = (RadioButton) findViewById(R.id.time_radio);
        enjoyRb = (RadioButton) findViewById(R.id.enjoy_radio);
        personal = (AvatarImageView) findViewById(R.id.personal);
        nameLinear = (LinearLayout) findViewById(R.id.lalala_linearlayout);
        name = (TextView) findViewById(R.id.authordetial_title);
        content = (TextView) findViewById(R.id.authordetial_content);
        radioGroupLayout = (RadioGroup) findViewById(R.id.RadioGroup);
        myScrollView = (MyScrollView) findViewById(R.id.scroll);
        messageIv = (ImageView) findViewById(R.id.message_image);
        replace_title = (LinearLayout) findViewById(R.id.repalce_asd);
        title = (TextView) findViewById(R.id.asd);
        replaceLayout = (LinearLayout) findViewById(R.id.replaceLayout);
        suspensionLayout = (LinearLayout) findViewById(R.id.suspensionLayout);
        headLayout = (LinearLayout) findViewById(R.id.headLayout);
        titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
        myScrollView.setOnScrollListener(this);
        Intent bacIntent = getIntent();
        String nameTv = bacIntent.getStringExtra("bcTitle");
        String contentTv = bacIntent.getStringExtra("bcDescription");
        name.setText(nameTv);
        timeRb.setOnClickListener(this);
        enjoyRb.setOnClickListener(this);
        content.setText(contentTv);
        Glide.with(this).load(bacIntent.getStringExtra("bcIcon")).centerCrop().crossFade().into(personal);
        ViewGroup.LayoutParams params = radioGroupLayout.getLayoutParams();
        radioGroupHeight = params.height;
        ViewGroup.LayoutParams params1 = name.getLayoutParams();
        nameHeight = params1.height;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.replace(R.id.replace_layout_mine, new TimeFragment());
        fragmentTransaction1.commit();

    }

    @Override
    public void initData() {
    }


    @Override
    public void onScroll(int scrollY) {
        surplusHeight = headLayout.getBottom() - radioGroupHeight;
        if (scrollY >= surplusHeight) {
            if (radioGroupLayout.getParent() != replaceLayout) {
                suspensionLayout.removeView(radioGroupLayout);
                replaceLayout.addView(radioGroupLayout);
            }
        } else {
            if (radioGroupLayout.getParent() != suspensionLayout) {
                replaceLayout.removeView(radioGroupLayout);
                suspensionLayout.addView(radioGroupLayout);
            }
        }
        mSurplusHeight = headLayout.getBottom() - nameHeight - 20;
        if (scrollY >= mSurplusHeight) {
            if (name.getParent() != replace_title) {
                nameLinear.removeView(name);
                replace_title.addView(name);
            }
        } else {
            if (name.getParent() != nameLinear) {
                replace_title.removeView(name);
                nameLinear.addView(name);
            }
        }
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.time_radio:
                fragmentTransaction.replace(R.id.replace_layout_mine, new TimeFragment());
                break;
            case R.id.enjoy_radio:
                fragmentTransaction.replace(R.id.replace_layout_mine, new EnjoyFragment());
                break;


        }
        fragmentTransaction.commit();
    }
}