package com.example.android.pendomoviz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trailers implements Parcelable{

    @SerializedName("id")
    private String id;

    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;

    public Trailers(String key, String name){

        this.key = key;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){

        this.name = name;
    }


    private Trailers(Parcel parcel){
        id= parcel.readString();
        key= parcel.readString();
        name = parcel.readString();
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(key);
        dest.writeString(name);
    }
    public static final  Parcelable.Creator<Trailers> CREATOR = new Parcelable.Creator<Trailers>(){

        @Override
        public Trailers createFromParcel(Parcel source) {
            return new Trailers(source);
        }

        @Override
        public Trailers[] newArray(int size) {
            return new Trailers[0];
        }
    };
}
