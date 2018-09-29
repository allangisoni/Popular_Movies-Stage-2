package com.example.android.pendomoviz.activity;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.pendomoviz.db.FavoritesViewModel;
import com.example.android.pendomoviz.NetworkConnection.App;
import com.example.android.pendomoviz.R;
import com.example.android.pendomoviz.adapter.FavoritesAdapter;
import com.example.android.pendomoviz.adapter.MovizAdapter;
import com.example.android.pendomoviz.model.Moviz;
import com.example.android.pendomoviz.model.MovizResponse;
import com.example.android.pendomoviz.rest.TMdbApiClient;
import com.example.android.pendomoviz.rest.TMdbApiInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private final static String API_KEY = "7f10a990314c43d89d94b1380199202d";
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    MovizAdapter movizAdapter;
    FavoritesAdapter favoritesAdapter;
    Toolbar toolbar;
    final TMdbApiInterface tMdbApiInterface = TMdbApiClient.getClient().create(TMdbApiInterface.class);
    List<Moviz> movizs;

    LinearLayout linlaHeaderProgress;
    final int numberOfColumns = 2;
    final int numberOftabletColumns = 4;

    private FavoritesViewModel favoritesViewModel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUIComponents();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);




        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        }
        else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, numberOftabletColumns));
        }


        loadMoviesFromSharedPreferences(true);


    }

    /**
     * This method will allow us to initialize our views from the xml and assign them to the
     * variables we have created
     */

    private void initUIComponents() {

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.rvMoviz);
        linlaHeaderProgress = findViewById (R.id.linlaHeaderProgress);

    }
    /**
     * This method will get the Top Rated movies from TMDB
     */

    private void requestTopRatedMovies(){



         if(API_KEY.isEmpty()){

             toastApiKeyError();

         }else {


             Call<MovizResponse> call = tMdbApiInterface.getTopratedMovies( API_KEY);
             linlaHeaderProgress.setVisibility(View.VISIBLE);
             call.enqueue(new Callback<MovizResponse>() {
                 @Override
                 public void onResponse(Call<MovizResponse> call, Response<MovizResponse> response) {
                     if (response.isSuccessful()) {
                         movizs = response.body().getResults();
                         movizAdapter = new MovizAdapter(movizs, getApplicationContext(), new MovizAdapter.OnItemClickListener() {
                             @Override
                             public void onItemClick(Moviz movizitem) {

                             }
                         });

                         recyclerView.setAdapter(movizAdapter);


                         Log.d(TAG, "Number of Top Rated Movies Received:" + movizs.size());

                         linlaHeaderProgress.setVisibility(View.GONE);


                     } else {

                         Toast.makeText(MainActivity.this, "Server returned an error", Toast.LENGTH_SHORT).show();

                         linlaHeaderProgress.setVisibility(View.GONE);

                     }

                 }

                 @Override
                 public void onFailure(Call<MovizResponse> call, Throwable t) {
                     if (t instanceof IOException) {
                         Log.e(TAG, t.toString());
                         Toast.makeText(MainActivity.this, "Please check your internet Connection", Toast.LENGTH_SHORT).show();
                         linlaHeaderProgress.setVisibility(View.GONE);


                     } else {
                         Toast.makeText(MainActivity.this, "Conversion error encountered", Toast.LENGTH_SHORT).show();
                         linlaHeaderProgress.setVisibility(View.GONE);

                     }


                 }

             });
         }
    }

    /**
     * This method will get the Most Popular movies from TMDB
     */

    private void requestMostPopularMovies(){

        if(API_KEY.isEmpty()){

            toastApiKeyError();
        } else

            {

            Call<MovizResponse> call = tMdbApiInterface.getPopularMovies(API_KEY);
            linlaHeaderProgress.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<MovizResponse>() {
                @Override
                public void onResponse(Call<MovizResponse> call, Response<MovizResponse> response) {
                    if (response.isSuccessful()) {
                        movizs = response.body().getResults();
                        movizAdapter = new MovizAdapter(movizs, getApplicationContext(), new MovizAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Moviz movizitem) {

                            }
                        });
                        recyclerView.setAdapter(movizAdapter);

                        Log.d(TAG, "Number of Popular Movies Received:" + movizs.size());
                        linlaHeaderProgress.setVisibility(View.GONE);
                    } else {

                        Toast.makeText(MainActivity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
                        linlaHeaderProgress.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onFailure(Call<MovizResponse> call, Throwable t) {
                    if (t instanceof IOException) {
                        Log.e(TAG, t.toString());
                        Toast.makeText(MainActivity.this, "Please check your internet Connection", Toast.LENGTH_SHORT).show();
                        linlaHeaderProgress.setVisibility(View.GONE);

                    } else {
                        Toast.makeText(MainActivity.this, "Conversion error encountered", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, t.toString());
                        linlaHeaderProgress.setVisibility(View.GONE);
                    }


                }


            });

        }

    }


    /**
     * This method will get Favorite movies from Room Database
     */
    private void requestFavoriteMovies() {

        favoritesAdapter = new FavoritesAdapter(new ArrayList<Moviz>(), getApplicationContext(), new FavoritesAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Moviz movizitem) {

            }

        });


        recyclerView.setAdapter(favoritesAdapter);
        favoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

        favoritesViewModel.getFavoritesList().observe(MainActivity.this, new Observer<List<Moviz>>() {
            @Override
            public void onChanged(@Nullable List<Moviz> favorites) {

                //Toast.makeText(MainActivity.this, "" + favorites.size(), Toast.LENGTH_LONG).show();
                //Toast.makeText(MainActivity.this, "" + favorites.get(0).getTitle(), Toast.LENGTH_LONG).show();
                favoritesAdapter.addFavorites(favorites);
            }
        });



    }


    /**
     * This method will display a toast error if there is no
     * api key initialized by the user
     */

    public void toastApiKeyError(){

        Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
    }

    /**
     * This method will load movies to the UI based on the user's preference
     */

    public void loadMoviesFromSharedPreferences(boolean isConnected){
        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userPreference = sharedPreferences.getString(getResources().getString(R.string.pref_sort_key), getResources().getString( R.string.pref_sort_most_popuar_value));

        if(isConnected) {
     if (userPreference.equals(getResources().getString(R.string.pref_sort_most_popuar_value))) {
         recyclerView.setAdapter(null);
         requestMostPopularMovies();

     } else if (userPreference.equals(getResources().getString(R.string.pref_sort_top_rated_value))){
         recyclerView.setAdapter(null);
         requestTopRatedMovies();

     } else {
         recyclerView.setAdapter(null);
         requestFavoriteMovies();
     }


    }else{

     Log.d(TAG, "No internet connection was found:");

    }
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.settings){
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        } else if(id == R.id.refresh){
            loadMoviesFromSharedPreferences(true);
        }
        return true;
    }



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getResources().getString(R.string.pref_sort_key))) {

            String userPreference = sharedPreferences.getString(key, null);

            if(userPreference.equals(getResources().getString(R.string.pref_sort_most_popuar_value))){
                recyclerView.setAdapter(null);
                requestMostPopularMovies();

            } else if(userPreference.equals(getResources().getString(R.string.pref_sort_top_rated_value))) {
                recyclerView.setAdapter(null);
                requestTopRatedMovies();

            }
            else {
                recyclerView.setAdapter(null);
                requestFavoriteMovies();
            }

        }
    }




    @Override
    protected void onResume() {
        super.onResume();
     PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
     App.activityResumed();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.activityPaused();

    }


}
