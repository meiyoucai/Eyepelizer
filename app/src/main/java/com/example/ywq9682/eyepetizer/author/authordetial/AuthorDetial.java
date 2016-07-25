package com.example.ywq9682.eyepetizer.author.authordetial;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;

/**
 * Created by dllo on 16/7/21.
 */
public class AuthorDetial extends BaseActivity implements OnScrollListener, View.OnClickListener {
    private FrameLayout frameLayout;
    private RadioGroup radioGroupLayout;
    private MyScrollView myScrollView;
    private int surplusHeight;//剩余的高度
    private int mSurplusHeight;
    private int radioGroupHeight;
    LinearLayout replaceLayout, suspensionLayout;//替换的布局和悬浮的布局
    LinearLayout headLayout;//头
    private RelativeLayout titleLayout;//标题布局
    private RadioButton starteButton, simpleButton;
    private ImageView personal;
    private ImageView messageIv, setIv;
    private TextView asd, lalala;
    private LinearLayout lalalaLinear, replace_asd;
    private int lalalaHeight;

    @Override
    public int setLayout() {
        return R.layout.activity_authordetial;
    }

    @Override
    public void initView() {
        personal = (ImageView) findViewById(R.id.personal);
        lalalaLinear = (LinearLayout) findViewById(R.id.lalala_linearlayout);
        lalala = (TextView) findViewById(R.id.lalala);
        radioGroupLayout = (RadioGroup) findViewById(R.id.RadioGroup);
        myScrollView = (MyScrollView) findViewById(R.id.scroll);
        messageIv = (ImageView) findViewById(R.id.message_image);
        replace_asd = (LinearLayout) findViewById(R.id.repalce_asd);
        asd = (TextView) findViewById(R.id.asd);
        starteButton = (RadioButton) findViewById(R.id.enjoy_radio);
        setIv = (ImageView) findViewById(R.id.mine_set);
        replaceLayout = (LinearLayout) findViewById(R.id.replaceLayout);
        suspensionLayout = (LinearLayout) findViewById(R.id.suspensionLayout);
        simpleButton = (RadioButton) findViewById(R.id.time_radio);
        headLayout = (LinearLayout) findViewById(R.id.headLayout);
        titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
        frameLayout = (FrameLayout) findViewById(R.id.replace_layout_mine);
        simpleButton.setOnClickListener(this);
        starteButton.setOnClickListener(this);
        myScrollView.setOnScrollListener(this);
        setIv.setOnClickListener(this);
        ViewGroup.LayoutParams params = radioGroupLayout.getLayoutParams();
        radioGroupHeight = params.height;
        ViewGroup.LayoutParams params1 = lalala.getLayoutParams();
        lalalaHeight = params1.height;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.replace(R.id.replace_layout_mine, new TimeFragment());
        fragmentTransaction1.commit();
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.time_radio:
                fragmentTransaction.replace(R.id.replace_layout_mine, new TimeFragment());
                break;
            case R.id.enjoy_radio:
                fragmentTransaction.replace(R.id.replace_layout_mine, new EnjoyFragment());
                break;
        }
        fragmentTransaction.commit();
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
        mSurplusHeight = headLayout.getBottom() - lalalaHeight - 20;
        if (scrollY >= mSurplusHeight) {
            if (lalala.getParent() != replace_asd) {
                lalalaLinear.removeView(lalala);
                replace_asd.addView(lalala);
            }
        } else {
            if (lalala.getParent() != lalalaLinear) {
                replace_asd.removeView(lalala);
                lalalaLinear.addView(lalala);
            }
        }
    }
}