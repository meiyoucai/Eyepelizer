package com.example.ywq9682.eyepetizer.tools;

import android.content.Context;

/**
 * Created by dllo on 16/8/1.
 */
public class SingtonLiteOrm {

    private static SingtonLiteOrm singtonLiteOrm;
    

    public SingtonLiteOrm(Context context) {
    }

    public static SingtonLiteOrm getSingtonLiteOrm(Context context) {
        if (singtonLiteOrm == null) {
            synchronized (SingtonLiteOrm.class) {

                if (singtonLiteOrm == null) {

                    singtonLiteOrm = new SingtonLiteOrm(context);
                }

            }

        } return singtonLiteOrm;
    }


}
