package com.example.ywq9682.eyepetizer.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.main.MainActivity;

/**
 * Created by dllo on 16/7/20.
 */
public class WelcomeActivity extends Activity implements Animation.AnimationListener {


        private ImageView imageView = null;
        private Animation alphaAnimation = null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);
            imageView = (ImageView) findViewById(R.id.welcome_image_view);
            alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpko);
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
