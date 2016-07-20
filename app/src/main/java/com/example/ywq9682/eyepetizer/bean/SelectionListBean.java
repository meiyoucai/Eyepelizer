package com.example.ywq9682.eyepetizer.bean;

/**
 * Created by dllo on 16/7/19.
 */
public class SelectionListBean {
    private String type, text, title, category, imageUrl;
    private int duration;

    public SelectionListBean() {
    }

    public SelectionListBean(String type, String text, String title, String category, String imageUrl, int duration) {
        this.type = type;
        this.text = text;
        this.title = title;
        this.category = category;
        this.imageUrl = imageUrl;
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
