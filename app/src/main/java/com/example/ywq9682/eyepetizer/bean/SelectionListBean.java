package com.example.ywq9682.eyepetizer.bean;


import android.os.Parcel;
import android.os.Parcelable;

public class SelectionListBean implements Parcelable {
    private String type, text, title, time, category, imageUrl, description, playUrl, blurredUrl, image;
    private int collectionCount, shareCount, replyCount, count, id;

    public SelectionListBean() {
    }

    public SelectionListBean(String type, String text, String title, String time, String category, String imageUrl, String description, String playUrl, String blurredUrl, String image, int collectionCount, int shareCount, int replyCount, int count, int id) {
        this.type = type;
        this.text = text;
        this.title = title;
        this.time = time;
        this.category = category;
        this.imageUrl = imageUrl;
        this.description = description;
        this.playUrl = playUrl;
        this.blurredUrl = blurredUrl;
        this.image = image;
        this.collectionCount = collectionCount;
        this.shareCount = shareCount;
        this.replyCount = replyCount;
        this.count = count;
        this.id = id;
    }

    protected SelectionListBean(Parcel in) {
        type = in.readString();
        text = in.readString();
        title = in.readString();
        time = in.readString();
        category = in.readString();
        imageUrl = in.readString();
        description = in.readString();
        playUrl = in.readString();
        blurredUrl = in.readString();
        image = in.readString();
        collectionCount = in.readInt();
        shareCount = in.readInt();
        replyCount = in.readInt();
        count = in.readInt();
        id = in.readInt();
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        parcel.writeString(time);
        parcel.writeString(category);
        parcel.writeString(imageUrl);
        parcel.writeString(description);
        parcel.writeString(playUrl);
        parcel.writeString(blurredUrl);
        parcel.writeString(image);
        parcel.writeInt(collectionCount);
        parcel.writeInt(shareCount);
        parcel.writeInt(replyCount);
        parcel.writeInt(count);
        parcel.writeInt(id);
    }
}
