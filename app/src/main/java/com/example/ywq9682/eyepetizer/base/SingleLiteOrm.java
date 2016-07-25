package com.example.ywq9682.eyepetizer.base;

import com.example.ywq9682.eyepetizer.main.MyApp;
import com.litesuits.orm.LiteOrm;

/**
 * Created by dllo on 16/7/22.
 */
public class SingleLiteOrm {
    private static SingleLiteOrm singleLiteOrm;
    private LiteOrm liteOrm;

    //类  类型  对象
    private SingleLiteOrm() {
        liteOrm = LiteOrm.newCascadeInstance(MyApp.context, "dbName.db");
    }

    public static SingleLiteOrm getSingleLiteOrm() {
        if (singleLiteOrm == null) {
            synchronized (SingleLiteOrm.class) {
                if (singleLiteOrm == null) {
                    singleLiteOrm = new SingleLiteOrm();
                }
            }
        }
        return singleLiteOrm;
    }

    public LiteOrm getLiteOrm() {
        return liteOrm;
    }
}
