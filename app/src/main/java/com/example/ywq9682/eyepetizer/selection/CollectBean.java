package com.example.ywq9682.eyepetizer.selection;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/8/1.
 */
public class CollectBean {
    private String titleTv;
    private String categoryTv;
    private String imageView;
    private String timeTv;
    //指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    public String getTitleTv() {
        return titleTv;
    }

    public void setTitleTv(String titleTv) {
        this.titleTv = titleTv;
    }

    public String getCategoryTv() {
        return categoryTv;
    }

    public void setCategoryTv(String categoryTv) {
        this.categoryTv = categoryTv;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getTimeTv() {
        return timeTv;
    }

    public void setTimeTv(String timeTv) {
        this.timeTv = timeTv;
    }
}
