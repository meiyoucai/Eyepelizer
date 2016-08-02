package com.example.ywq9682.eyepetizer.base;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by dllo on 16/7/30.
 */
public class Utils {
    //获取屏幕宽度
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;

    }
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics outMetrics = new DisplayMetrics();

        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;

    }

}
