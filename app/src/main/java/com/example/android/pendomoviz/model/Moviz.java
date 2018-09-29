
package com.example.android.pendomoviz.model;

        import android.arch.persistence.room.Entity;
        import android.arch.persistence.room.Ignore;
        import android.arch.persistence.room.PrimaryKey;
        import android.os.Parcel;
        import android.os.Parcelable;

        import com.google.gson.annotations.SerializedName;

        import java.util.ArrayList;
        import java.util.List;
@Entity
public class Moviz implements Parcelable{
     @Ignore
    private static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w500";
    @Ignore
    @SerializedName("vote_count")
    private String voteCount;
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("video")
    private boolean video;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("title")
    private String title;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @Ignore
    @SerializedName("genre_ids")
    private List<Integer> genreIds;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @Ignore
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;

    private String isMovieFavorite;

   // @SerializedName("reviews")
   // private  Reviews reviews;

    @Ignore
    public Moviz(){


    }


    public Moviz( int id,double voteAverage, String title, String posterPath,String backdropPath,String overview, String releaseDate,Double popularity , String isMovieFavorite){
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.isMovieFavorite = isMovieFavorite;

    }


    public Moviz(String voteCount, int id, boolean video, double voteAverage, String title, double popularity, String posterPath, String originalLanguage,
                 String originalTitle, List<Integer> genreIds, String backdropPath, boolean adult, String overview, String releaseDate) {

        this.voteCount = voteCount;
        this.id = id;
        this.video = video;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
       // this.reviews = reviews;

    }




    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount){
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public  void setVideo(boolean video){
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {

       return  posterPath ;
       // return  posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return  backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getIsMovieFavorite() {
        return isMovieFavorite;
    }

    public void setIsMovieFavorite(String isMovieFavorite) {
        this.isMovieFavorite = isMovieFavorite;
    }

  //  public Reviews getReviews(){return  reviews;}

  //  public void setReviews(Reviews reviews){
   //     this.reviews = reviews;
   // }


    //constructor used for parcel
    private Moviz(Parcel parcel){

        voteCount = parcel.readString();
        id = parcel.readInt();
        video = (Boolean) parcel.readValue(getClass().getClassLoader());
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
        isMovieFavorite = parcel.readString();
       // reviews = parcel.readParcelable(Reviews.class.getClassLoader());


        //read and set saved values from parcel
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(voteCount);
        dest.writeInt(id);
        dest.writeValue(video);
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
        dest.writeString(isMovieFavorite);
        //dest.writeParcelable(reviews, flags);
    }




    public static final  Parcelable.Creator<Moviz> CREATOR = new Parcelable.Creator<Moviz>(){

        @Override
        public Moviz createFromParcel(Parcel source) {
            return new Moviz(source);
        }

        @Override
        public Moviz[] newArray(int size) {
            return new Moviz[0];
        }
    };
}
