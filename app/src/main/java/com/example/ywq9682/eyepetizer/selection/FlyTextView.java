package com.example.ywq9682.eyepetizer.selection;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

/**
 * Created by dllo on 16/7/26.
 */
public class FlyTextView extends ViewGroup {
    private static final String TAG = "FlyTextView";
    private final static int VIEW_MARGIN = 2;
    private int textColor;
    private int textSize;
    private AnimationSet animationSet;
    private LayoutAnimationController layoutAnimationController;

    public FlyTextView(Context context) {
        this(context, null);

    }

    public FlyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public FlyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    // init
    public void init() {
        animationSet = new AnimationSet(true);
        layoutAnimationController = new LayoutAnimationController(animationSet, 0.3f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        setAnimation(null);
        setLayoutAnimation(layoutAnimationController);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        for (int index = 0; index < getChildCount(); index++) {
            final View child = getChildAt(index);
            // measure
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();//所包含的子View个数
        int row = 0;// which row lay you view relative to parent
        int lengthX = 0; // right position of child relative to parent右侧相对于父容器的位置
        int lengthY; // bottom position of child relative to parent底部相对于父容器的位置
        for (int i = 0; i < count; i++) {

            final View child = this.getChildAt(i);//每一个字
            int width = child.getMeasuredWidth();//每一个字的宽度
            int height = child.getMeasuredHeight();//每一个字的高度
            lengthX += width + VIEW_MARGIN;
            lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height;
            // if it can't drawing on a same line , skip to next line
            if (lengthX > right - left) {
                lengthX = width + VIEW_MARGIN;
                row++;
                lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height;
            }
            child.layout(lengthX - width, lengthY - height, lengthX, lengthY);
        }
    }

    // set content texts
    public void setTexts(String text) {
//        Log.v(TAG, text + "");
        removeAllViews();
        if (text != null) {

            char[] chars = text.toCharArray();
            int count = chars.length;
            for (int i = 0; i < count; i++) {
//            Log.v(TAG, chars[i] + "");
                TextView tv = new TextView(getContext());
                tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tv.setText(chars[i] + "");
                tv.setTextColor(textColor);
                tv.setTextSize(textSize);
                addView(tv);
            }
        }
    }

    // config must before setTexts
    // set texts color
    public void setTextColor(int textColor) {
        this.textColor = textColor;

    }

    // config must before setTexts
    // set text size
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setAnimation(Animation animation) {
        if (null != animation) {
            animationSet.addAnimation(animation);
        } else {
            setDefaultAnimation();
        }

    }

    // start animation
    public void startAnimation() {
        startLayoutAnimation();
    }

    // default animation
    private void setDefaultAnimation() {
        Animation animation = new AlphaAnimation(0.0f, 0.0f);
//        animation.setStartTime(2000);
        animation.setDuration(80);
        animation.setFillAfter(true);
        animationSet.addAnimation(animation);
    }
}
