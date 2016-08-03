package com.example.ywq9682.eyepetizer.tools;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by dllo on 16/7/30.
 */
public class Window {
    public static int displayWidth;  //屏幕宽度
    public static int displayHeight; //屏幕高度

    public static int getDisplayHeight(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        displayHeight = dm.heightPixels;
        return displayHeight;
    }

    public static int getDisplayWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        displayWidth = dm.widthPixels;
        return displayWidth;
    }
}
