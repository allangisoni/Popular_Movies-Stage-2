package com.example.android.pendomoviz.rest;



import com.example.android.pendomoviz.NetworkConnection.App;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TMdbApiClient {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(App.provideOkHttpClient())
                    .build();


        }

        return retrofit;
    }



}
