package com.example.ywq9682.eyepetizer.bean;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by dllo on 16/7/29.
 */
public class MDrawable extends  BitmapDrawable {

    Bitmap bitmap;

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(bitmap,0,0,getPaint());
    }
}
