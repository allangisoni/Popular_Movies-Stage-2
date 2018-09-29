
package com.example.android.pendomoviz.rest;

import com.example.android.pendomoviz.model.MovizResponse;
import com.example.android.pendomoviz.model.ReviewsResponse;
import com.example.android.pendomoviz.model.Trailers;
import com.example.android.pendomoviz.model.TrailersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMdbApiInterface {

    @GET("movie/top_rated")
    Call<MovizResponse> getTopratedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovizResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewsResponse> getReviewsById(@Path("movie_id") long id, @Query("api_key") String key);

    @GET("movie/{movie_id}/videos")
    Call<TrailersResponse> getTrailersById(@Path("movie_id") long id, @Query("api_key") String key);

    @GET("movie/id")
    Call<MovizResponse> getMovieDetails(@Path("id") int id , @Query("api_key") String apiKey);

    @GET("movie/{movieId}?append_to_response=reviews")
    Call<MovizResponse> getMovie(@Path("movieId") int movieId, @Query("api_key") String apiKey);
}
