package com.example.android.pendomoviz.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trailers {

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


}
