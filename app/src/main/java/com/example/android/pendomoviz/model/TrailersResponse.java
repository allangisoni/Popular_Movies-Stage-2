package com.example.android.pendomoviz.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TrailersResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<Trailers> results;

    public TrailersResponse(){

        this.id = 0;

        this.results = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public  void setId(int id){

        this.id = id;
    }

    public List<Trailers> getResults() {
        return results;
    }


}
