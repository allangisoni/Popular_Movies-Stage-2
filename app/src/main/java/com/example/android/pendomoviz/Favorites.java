package com.example.android.pendomoviz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;

@Entity
public class Favorites implements Parcelable {


    public Favorites() {

    }

    @PrimaryKey(autoGenerate = true)
    private int uid;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @ColumnInfo(name = "Movie_name")
    private String name;

    @ColumnInfo(name = "image_url")
    private String imageUrl;


    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    private Favorites(Parcel parcel){

        name = parcel.readString();
        imageUrl = parcel.readString();
      /**  video = (Boolean) parcel.readValue(getClass().getClassLoader());
        voteAverage = parcel.readDouble();
        title = parcel.readString();
        popularity = parcel.readDouble();
        posterPath = parcel.readString();
        originalLanguage = parcel.readString();
        originalTitle = parcel.readString();
        genreIds = new ArrayList();
        parcel.readList(this.genreIds, Integer.class.getClassLoader());
        backdropPath = parcel.readString();
        adult = (Boolean) parcel.readValue(getClass().getClassLoader());
        overview = parcel.readString();
        releaseDate = parcel.readString();
**/
        //read and set saved values from parcel
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(imageUrl);
     /**   dest.writeValue(video);
        dest.writeDouble(voteAverage);
        dest.writeString(title);
        dest.writeDouble(popularity);
        dest.writeString(posterPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeList(genreIds);
        dest.writeString(backdropPath);
        dest.writeValue(adult);
        dest.writeString(overview);
        dest.writeString(releaseDate);

      **/
    }




    public static final  Parcelable.Creator<Favorites> CREATOR = new Parcelable.Creator<Favorites>(){

        @Override
        public Favorites createFromParcel(Parcel source) {
            return new Favorites(source);
        }

        @Override
        public Favorites[] newArray(int size) {
            return new Favorites[0];
        }
    };


}
