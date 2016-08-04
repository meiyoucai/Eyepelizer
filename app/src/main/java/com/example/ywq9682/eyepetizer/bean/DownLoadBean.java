package com.example.ywq9682.eyepetizer.bean;

/**
 * Created by YWQ9682 on 2016/8/4.
 */
public class DownLoadBean {
    private String title;
    private String path;

    public DownLoadBean(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
