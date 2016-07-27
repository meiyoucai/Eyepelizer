package com.example.ywq9682.eyepetizer.welcome;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;
import com.example.ywq9682.eyepetizer.main.MainActivity;

/**
 * Created by dllo on 16/7/20.
 */
public class WelcomeActivity extends BaseActivity implements Animation.AnimationListener {


    private ImageView imageView = null;
    private Animation alphaAnimation = null;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);
//        imageView = (ImageView) findViewById(R.id.welcome_image_view);
//        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpko);
//        alphaAnimation.setFillEnabled(true); //启动Fill保持
//        alphaAnimation.setFillAfter(true);  //设置动画的最后一帧是保持在View上面
//        imageView.setAnimation(alphaAnimation);
//        alphaAnimation.setAnimationListener(this);  //为动画设置监听
//    }

    @Override
    public int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        imageView = (ImageView) findViewById(R.id.welcome_image_view);
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpko);
    }

    @Override
    public void initData() {
        alphaAnimation.setFillEnabled(true); //启动Fill保持
        alphaAnimation.setFillAfter(true);  //设置动画的最后一帧是保持在View上面
        imageView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(this);  //为动画设置监听
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //动画结束时结束欢迎界面并转到软件的主界面
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//在欢迎界面屏蔽BACK键
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return false;
    }
}
