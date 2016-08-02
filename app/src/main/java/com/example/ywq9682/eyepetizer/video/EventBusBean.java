package com.example.ywq9682.eyepetizer.video;

/**
 * Created by YWQ9682 on 2016/7/29.
 */
public class EventBusBean {
    private float process;
    private String title;
private boolean isDoing;

    public boolean isDoing() {
        return isDoing;
    }

    public void setDoing(boolean doing) {
        isDoing = doing;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getProcess() {
        return process;
    }

    public void setProcess(float process) {
        this.process = process;
    }
}
