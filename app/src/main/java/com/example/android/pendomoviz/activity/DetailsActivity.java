package com.example.android.pendomoviz.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.pendomoviz.db.FavoritesDb;
import com.example.android.pendomoviz.R;
import com.example.android.pendomoviz.adapter.ReviewsAdapter;
import com.example.android.pendomoviz.model.Moviz;
import com.example.android.pendomoviz.model.Reviews;
import com.example.android.pendomoviz.model.ReviewsResponse;
import com.example.android.pendomoviz.rest.TMdbApiClient;
import com.example.android.pendomoviz.rest.TMdbApiInterface;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvTitle, tvReleaseDate, tvDescription, tvRating, tvReviewsTrailers;

    private ImageView ivThumbnail, ivThumbnailBackdrop, ivFavorites;
    private Toolbar toolbar;

    private String backdropPath, posterPath;


    private FavoritesDb favoritesDb;
    Intent intent;

    private  boolean isFavorite;
  //  private ListView listView;

    private String[] values;

    private static FragmentManager fragmentManager;

    RecyclerView mRecyclerView;

    ReviewsAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    private final static String API_KEY = "7f10a990314c43d89d94b1380199202d";

    final TMdbApiInterface tMdbApiInterface = TMdbApiClient.getClient().create(TMdbApiInterface.class);

    List<Reviews> movizs;

    int movieId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        initUIComponents();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setMovieDetails();
        isFavorite = false;


       ivFavorites.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               setFavorite(isFavorite);



               new Thread(new Runnable() {
                   @Override
                   public void run() {


                      /** Favorites favorites = new Favorites();

                       favorites.setName(moviz.getTitle());
                       favorites.setImageUrl(moviz.getPosterPath());
                       FavoritesDb.getAppDatabase(getApplicationContext()).favoritesDao().insertOnlySingleMovie(favorites);
                       **/
                   }
               }).start();
           }
       });


        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

       getReviews();

       tvReviewsTrailers.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

              // Moviz moviz = intent.getParcelableExtra("movieDetails");

              /** Fragment argumentFragment = new ReviewsFragment();//Get Fragment Instance
               Bundle data = new Bundle();//Use bundle to pass data
               data.putParcelable("movieForDetails", moviz);//put string, int, etc in bundle with a key value
               argumentFragment.setArguments(data); **/
               startActivity(new Intent(DetailsActivity.this, DetailsMain.class));
               Moviz moviz = intent.getParcelableExtra("movieDetails");
               Intent intent = new Intent(DetailsActivity.this, DetailsMain.class);
               intent.putExtra("movieForDetails", moviz );

              // Toast.makeText(DetailsActivity.this, "Number of Top Rated Movies Received:" + moviz.getTitle(), Toast.LENGTH_SHORT).show();
               startActivity(intent);
           }
       });



        values= new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };


    }

    public void setFavorite(Boolean isMovieFavorite){
        if(isMovieFavorite == false){
            ivFavorites.setColorFilter(getResources().getColor(R.color.colorAccent));
            isFavorite = true;
        }
         else{
            ivFavorites.setColorFilter(getResources().getColor(R.color.blue));
            isFavorite = false;

        }
    }


    /**
     * This method will allow us to initialize our views from the xml and assign them to the
     * variables we have created
     */
    private void initUIComponents() {
        intent = getIntent();
        toolbar = findViewById(R.id.toolbar);
        tvTitle = findViewById(R.id.tvTitle);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        tvDescription = findViewById(R.id.tvDescription);
        ivThumbnail = findViewById(R.id.ivThumbnail);
        tvRating = findViewById(R.id.tvRating);
        ivThumbnailBackdrop = findViewById(R.id.ivThumbnailBackdrop);
        ivFavorites = findViewById(R.id.imageViewFavorite);
        tvReviewsTrailers =  findViewById(R.id.tvReviewsTrailers);
        mRecyclerView = findViewById(R.id.reviewMovizz);



    }

    /**
     * This method will set our views with the content that has been passed
     * from the main activity
     */

    private  void setMovieDetails(){

        Moviz moviz = intent.getParcelableExtra("movieDetails");

        tvTitle.setText(moviz.getTitle());
        tvReleaseDate.setText(moviz.getReleaseDate());
        tvDescription.setText(moviz.getOverview());
        tvRating.setText(moviz.getVoteAverage().toString() +" "+ "/ 10");
        Picasso.with(this).load(moviz.getBackdropPath()).placeholder(R.drawable.mtvmovies).error(R.drawable.imagenotfound3).into(ivThumbnail);
        Picasso.with(this).load(moviz.getPosterPath()).placeholder(R.drawable.mtvmovies).error(R.drawable.imagenotfound).into(ivThumbnailBackdrop);
        backdropPath = moviz.getBackdropPath();
        posterPath = moviz.getPosterPath();
        movieId = moviz.getId();






    }

    public void  getReviews(){


        Call<ReviewsResponse> call = tMdbApiInterface.getReviewsById( movieId, API_KEY);
         //linlaHeaderProgress.setVisibility(View.VISIBLE);
         call.enqueue(new Callback<ReviewsResponse>() {
        @Override
        public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
        if (response.isSuccessful()) {
        movizs = response.body().getResults();
        //movizAdapter = new MovizAdapter(movizs, getApplicationContext(), new MovizAdapter.OnItemClickListener() {
        //     @Override
        //     public void onItemClick(Moviz movizitem) {

        //    }
        //});

            Toast . makeText(getApplicationContext(), ""+ movizs.size(), Toast.LENGTH_SHORT).show();
        mAdapter = new ReviewsAdapter(movizs, getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);


        // Log.d(TAG, "Number of Top Rated Movies Received:" + movizs.size());

        //linlaHeaderProgress.setVisibility(View.GONE);


        } else {

        Toast.makeText(getApplicationContext(), "Server returned an error", Toast.LENGTH_SHORT).show();

        // linlaHeaderProgress.setVisibility(View.GONE);

        }
        }
        @Override
        public void onFailure (Call <ReviewsResponse > call, Throwable t){
        if (t instanceof IOException) {
        //Log.e(TAG, t.toString());
        Toast.makeText(DetailsActivity.this, "Please check your internet Connection", Toast.LENGTH_SHORT).show();
        //  linlaHeaderProgress.setVisibility(View.GONE);


        } else {
          Toast.makeText(DetailsActivity.this, "Conversion error encountered", Toast.LENGTH_SHORT).show();
        //linlaHeaderProgress.setVisibility(View.GONE);

        }


        }
        });



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        setMovieDetails();
        outState.putString("title", tvTitle.getText().toString());
        outState.putString("tvReleaseDate", tvReleaseDate.getText().toString());
        outState.putString("description", tvDescription.getText().toString());
        outState.putString("rating", tvRating.getText().toString());
        outState.putString("thumbnail", backdropPath);
        outState.putString("thumbnailBackdrop", posterPath);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tvTitle.setText(savedInstanceState.getString("title"));
        tvReleaseDate.setText(savedInstanceState.getString("tvReleaseDate"));
        tvDescription.setText(savedInstanceState.getString("description"));
        tvRating.setText(savedInstanceState.getString("rating"));
        Picasso.with(this).load(savedInstanceState.getString("thumbnail")).placeholder(R.drawable.mtvmovies).error(R.drawable.imagenotfound3).into(ivThumbnail);
        Picasso.with(this).load(savedInstanceState.getString("thumbnailBackdrop")).placeholder(R.drawable.mtvmovies).error(R.drawable.imagenotfound).into(ivThumbnailBackdrop);

    }




}
