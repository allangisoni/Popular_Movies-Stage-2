
package com.example.android.pendomoviz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ReviewsResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("id")
    private int id;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private List<Reviews> results;
    @SerializedName("total_results")
    private int totalResults;

    public ReviewsResponse() {
        //this.page = 0;
        this.id = 0;
       // this.totalPages = 0;
        this.results = new ArrayList<>();
        //this.totalResults = 0;
    }

    // Parcelable
    /**  @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.totalPages);
        dest.writeTypedList(this.results);
        dest.writeInt(this.totalResults);
    }

    protected ReviewsResponse(Parcel in) {
        this.page = in.readInt();
        this.totalPages = in.readInt();
        this.results = in.createTypedArrayList(Reviews.CREATOR);
        this.totalResults = in.readInt();
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
    public int getPage() {
        return page;
    }

    public int getId() {
        return id;
    }

    public  void setId(int id){

        this.id = id;
    }

   /** public int getTotalPages() {
        return totalPages;
    } **/

    public List<Reviews> getResults() {
        return results;
    }

   /** public int getTotalResults() {
        return totalResults;
    }**/
}
