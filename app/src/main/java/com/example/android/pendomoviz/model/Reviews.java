
package com.example.android.pendomoviz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Reviews implements Parcelable{

    @SerializedName("author")
    private String author;
    @SerializedName("id")
    private String id;
    @SerializedName("content")
    private String content;
    @SerializedName("url")
    private String url;

    public Reviews() {
        this.author = "";
        this.id = "";
        this.content = "";
        this.url = "";
    }

    // Parcelable
    /**  @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.id);
        dest.writeString(this.content);
        dest.writeString(this.url);
    }

    protected Reviews(Parcel in) {
        this.author = in.readString();
        this.id = in.readString();
        this.content = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Reviews> CREATOR = new Parcelable.Creator<Reviews>() {
        @Override
        public Reviews createFromParcel(Parcel source) {
            return new Reviews(source);
        }

        @Override
        public Reviews[] newArray(int size) {
            return new Reviews[size];
        }
    }; **/

    // Getters
    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    private Reviews(Parcel parcel){
        author = parcel.readString();
        id = parcel.readString();
        content = parcel.readString();
        url = parcel.readString();


    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(id);
        dest.writeString(content);
        dest.writeString(url);
    }

    public static final  Parcelable.Creator<Reviews> CREATOR = new Parcelable.Creator<Reviews>(){

        @Override
        public Reviews createFromParcel(Parcel source) {
            return new Reviews (source);
        }

        @Override
        public Reviews [] newArray(int size) {
            return new Reviews[0];
        }
    };
}
