package com.example.android.pendomoviz.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pendomoviz.db.AddFavoriteViewModel;
import com.example.android.pendomoviz.R;
import com.example.android.pendomoviz.adapter.ReviewsAdapter;
import com.example.android.pendomoviz.db.FavoritesDb;
import com.example.android.pendomoviz.db.FavoritesViewModel;
import com.example.android.pendomoviz.model.Moviz;
import com.example.android.pendomoviz.model.Reviews;
import com.example.android.pendomoviz.rest.TMdbApiClient;
import com.example.android.pendomoviz.rest.TMdbApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieDetailsFragment extends Fragment {
    private TextView tvTitle, tvReleaseDate, tvDescription, tvRating, tvGenres;

    private ImageView ivThumbnail, ivThumbnailBackdrop, ivFavorites;
    private Toolbar toolbar;

    private String backdropPath, posterPath;


    private  boolean isFavorite;




    RecyclerView mRecyclerView;



    Moviz moviz;

    private AddFavoriteViewModel addFavoriteViewModel;
    private FavoritesViewModel favoritesViewModel;
    private String checkFavorite;

    private static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w500";




    public MovieDetailsFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.movie_details_fragment, container, false);
         initUIComponents(rootView);

        Bundle bundle = this.getArguments();

        if(bundle != null) {
            moviz = bundle.getParcelable("movieDetails");
            //Toast.makeText(getContext(), "Number of Top Rated Movies Received:" + moviz.getTitle(), Toast.LENGTH_SHORT).show();
        }
        addFavoriteViewModel = ViewModelProviders.of(this).get(AddFavoriteViewModel.class);
        favoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

     /**   if(savedInstanceState != null){
            tvTitle.setText(savedInstanceState.getString("title"));
            tvReleaseDate.setText(savedInstanceState.getString("tvReleaseDate"));
            tvDescription.setText(savedInstanceState.getString("description"));
            tvRating.setText(savedInstanceState.getString("rating"));
            Picasso.with(getContext()).load(IMAGE_URL_BASE_PATH + savedInstanceState.getString( "thumbnail")).placeholder(R.drawable.mtvmovies).error(R.drawable.imagenotfound3).into(ivThumbnail);
            Picasso.with(getContext()).load(IMAGE_URL_BASE_PATH +savedInstanceState.getString( "thumbnailBackdrop")).placeholder(R.drawable.mtvmovies).error(R.drawable.imagenotfound).into(ivThumbnailBackdrop);
            isFavorite = savedInstanceState.getBoolean("isFavorite");
            setFavorite(isFavorite);
        }else{ **/
            setMovieDetails();
       // }





        ivFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFavorite(isFavorite);

                addFavoriteViewModel.addFavorite(new Moviz(
                        moviz.getId(),
                        moviz.getVoteAverage(),
                        moviz.getTitle(),
                        moviz.getPosterPath(),
                        moviz.getBackdropPath(),
                        moviz.getOverview(),
                        moviz.getReleaseDate(),
                        moviz.getPopularity(),
                        moviz.getIsMovieFavorite()


                ));


            }
        });

        return rootView;
    }

    /**
     * This method will allow us to initialize our views from the xml and assign them to the
     * variables we have created
     */

    private void initUIComponents(View rootView) {

        toolbar = rootView.findViewById(R.id.toolbar);
        tvTitle = rootView.findViewById(R.id.tvTitle);
        tvReleaseDate = rootView.findViewById(R.id.tvReleaseDate);
        tvDescription = rootView.findViewById(R.id.tvDescription);
        ivThumbnail = rootView.findViewById(R.id.ivThumbnail);
        tvRating = rootView.findViewById(R.id.tvRating);
        ivThumbnailBackdrop = rootView.findViewById(R.id.ivThumbnailBackdrop);
        ivFavorites = rootView.findViewById(R.id.imageViewFavorite);
        //tvReviewsTrailers = rootView.findViewById(R.id.tvReviewsTrailers);
        mRecyclerView = rootView.findViewById(R.id.reviewMovizz);
        tvGenres = rootView.findViewById(R.id.tvGenres);
        tvGenres.setVisibility(View.INVISIBLE);



    }



    /**
     * This method will set our views with the content that has been passed
     * from the main activity
     */

    private  void setMovieDetails(){


        tvTitle.setText(moviz.getTitle());
        tvReleaseDate.setText(moviz.getReleaseDate());
        tvDescription.setText(moviz.getOverview());
        tvRating.setText(moviz.getVoteAverage().toString() +" "+ "/ 10");
        Picasso.with(getActivity()).load(IMAGE_URL_BASE_PATH + moviz.getBackdropPath()).placeholder(R.drawable.mtvmovies).error(R.drawable.imagenotfound3).into(ivThumbnail);
        Picasso.with(getActivity()).load( IMAGE_URL_BASE_PATH + moviz.getPosterPath()).placeholder(R.drawable.mtvmovies).error(R.drawable.imagenotfound).into(ivThumbnailBackdrop);
        backdropPath = moviz.getBackdropPath();
        posterPath = moviz.getPosterPath();
        if(moviz.getIsMovieFavorite() != null) {
            if(moviz.getIsMovieFavorite().equals("true")){
                isFavorite = false;
                setFavorite(isFavorite);
            }

        }

       // Toast.makeText(getContext(), ""+moviz.getIsMovieFavorite(), Toast.LENGTH_SHORT).show();

    }


    /**
     * This method will set a movie Favorite Status to either true or false
     */

    public void setFavorite(Boolean isMovieFavorite){
        if(isMovieFavorite == false){
            ivFavorites.setColorFilter(getResources().getColor(R.color.colorAccent));
            isFavorite = true;
            checkFavorite = "true";
            moviz.setIsMovieFavorite(checkFavorite);

            //Toast.makeText(getContext(), "" +moviz.getTitle() +"has been added to favorites ", Toast.LENGTH_SHORT).show();



        }
        else{
            ivFavorites.setColorFilter(getResources().getColor(R.color.colorWhite));
            isFavorite = false;
            checkFavorite = "false";
            moviz.setIsMovieFavorite(checkFavorite);
            //Toast.makeText(getContext(), "" +moviz.getTitle() +"has been removed from favorites ", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

       // setMovieDetails();
        outState.putString("title", tvTitle.getText().toString());
        outState.putString("tvReleaseDate", tvReleaseDate.getText().toString());
        outState.putString("description", tvDescription.getText().toString());
        outState.putString("rating", tvRating.getText().toString());
        outState.putString("thumbnail", backdropPath);
        outState.putString("thumbnailBackdrop", posterPath);
        outState.putBoolean("isfavorite", isFavorite);

    }


}
