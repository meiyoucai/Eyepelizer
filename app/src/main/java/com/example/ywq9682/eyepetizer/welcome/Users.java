package com.example.ywq9682.eyepetizer.welcome;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/7/21.
 */
public class Users extends BmobUser {
    private byte[] photo;

    public Bitmap getImagePhoto() {
        if (photo == null) {
            return null;
        }
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
        return imageBitmap;
    }

    public void setImagePhoto(Bitmap image) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, output);
        photo = output.toByteArray();//转换成功了
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}