package com.example.ywq9682.eyepetizer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dllo on 16/7/19.
 */
public class SelectionListBean implements Parcelable {
    private String type, text, title, category, imageUrl, description, playUrl, blurredUrl;
    private int duration, collectionCount, shareCount, replyCount;

    public SelectionListBean() {
    }

    public SelectionListBean(String type, String text, String title, String category, String imageUrl, String description, String playUrl, String blurredUrl, int duration, int collectionCount, int shareCount, int replyCount) {
        this.type = type;
        this.text = text;
        this.title = title;
        this.category = category;
        this.imageUrl = imageUrl;
        this.description = description;
        this.playUrl = playUrl;
        this.blurredUrl = blurredUrl;
        this.duration = duration;
        this.collectionCount = collectionCount;
        this.shareCount = shareCount;
        this.replyCount = replyCount;
    }

    protected SelectionListBean(Parcel in) {
        type = in.readString();
        text = in.readString();
        title = in.readString();
        category = in.readString();
        imageUrl = in.readString();
        description = in.readString();
        playUrl = in.readString();
        blurredUrl = in.readString();
        duration = in.readInt();
        collectionCount = in.readInt();
        shareCount = in.readInt();
        replyCount = in.readInt();
    }

    public static final Creator<SelectionListBean> CREATOR = new Creator<SelectionListBean>() {
        @Override
        public SelectionListBean createFromParcel(Parcel in) {
            return new SelectionListBean(in);
        }

        @Override
        public SelectionListBean[] newArray(int size) {
            return new SelectionListBean[size];
        }
    };

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getBlurredUrl() {
        return blurredUrl;
    }

    public void setBlurredUrl(String blurredUrl) {
        this.blurredUrl = blurredUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(text);
        parcel.writeString(title);
        parcel.writeString(category);
        parcel.writeString(imageUrl);
        parcel.writeString(description);
        parcel.writeString(playUrl);
        parcel.writeString(blurredUrl);
        parcel.writeInt(duration);
        parcel.writeInt(collectionCount);
        parcel.writeInt(shareCount);
        parcel.writeInt(replyCount);
    }
}
