package com.example.android.pendomoviz.fragments;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pendomoviz.BuildConfig;
import com.example.android.pendomoviz.R;
import com.example.android.pendomoviz.activity.DetailsActivity;
import com.example.android.pendomoviz.activity.MainActivity;
import com.example.android.pendomoviz.adapter.MovizAdapter;
import com.example.android.pendomoviz.adapter.ReviewsAdapter;
import com.example.android.pendomoviz.adapter.TrailersAdapter;
import com.example.android.pendomoviz.model.Moviz;
import com.example.android.pendomoviz.model.MovizResponse;
import com.example.android.pendomoviz.model.Reviews;
import com.example.android.pendomoviz.model.ReviewsResponse;
import com.example.android.pendomoviz.model.Trailers;
import com.example.android.pendomoviz.rest.TMdbApiClient;
import com.example.android.pendomoviz.rest.TMdbApiInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class ReviewsFragment extends Fragment{


    RecyclerView mRecyclerView;

    private ReviewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Moviz moviz;

    private final static String API_KEY = BuildConfig.TMDB_API_KEY;

    final TMdbApiInterface tMdbApiInterface = TMdbApiClient.getClient().create(TMdbApiInterface.class);
    List<Reviews> movizs;

    ArrayList<Reviews> myReviews;


public ReviewsFragment(){

}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View rootView = inflater.inflate(R.layout.activity_reviews_fragment, container, false);

        initViews(rootView);

         myReviews = new ArrayList<>();

        Bundle bundle = this.getArguments();

        if(bundle != null) {
             moviz = bundle.getParcelable("movieDetails");

        }


        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


       // getReviews();


        return rootView;

    }

    /**
     * This method will allow us to initialize our views from the xml and assign them to the
     * variables we have created
     */
   public void initViews(View rootView) {


       mRecyclerView = rootView.findViewById(R.id.reviewMoviz);


   }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {

            myReviews = savedInstanceState.getParcelableArrayList("myReviews");
            movizs = myReviews;
            mAdapter = new ReviewsAdapter(movizs, getContext());

            mRecyclerView.setAdapter(mAdapter);
        }
        else {
            getReviews();
        }

    }


    public void getReviews(){

        int movieId = moviz.getId();
        Call<ReviewsResponse> call = tMdbApiInterface.getReviewsById( movieId, API_KEY);
        //linlaHeaderProgress.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ReviewsResponse>() {
            @Override
            public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                if (response.isSuccessful()) {
                    movizs = response.body().getResults();
                    myReviews = (ArrayList)movizs;

                     mAdapter = new ReviewsAdapter(movizs, getContext());
                     mRecyclerView.setAdapter(mAdapter);


                    Log.d(TAG, "Number of reviews Received:" + movizs.size());



                } else {

                    Toast.makeText(getContext(), "Server returned an error", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure (Call <ReviewsResponse> call, Throwable t){
                if (t instanceof IOException) {
                    Log.e(TAG, t.toString());
                    Toast.makeText(getContext(), "Please check your internet Connection", Toast.LENGTH_SHORT).show();



                } else {
                    Toast.makeText(getContext(), "Conversion error encountered", Toast.LENGTH_SHORT).show();


                }


            }
        });



    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("myReviews", myReviews);
    }
}
