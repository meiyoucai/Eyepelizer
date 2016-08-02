package com.example.ywq9682.eyepetizer.video;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by YWQ9682 on 2016/7/29.
 *
 */
public class Util {
    private static Toast toast;
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
    public static void showLog(String name, String contest){
        Log.d(name, contest);
    }


}


