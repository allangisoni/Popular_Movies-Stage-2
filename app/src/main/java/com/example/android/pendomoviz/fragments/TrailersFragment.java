package com.example.android.pendomoviz.fragments;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pendomoviz.BuildConfig;
import com.example.android.pendomoviz.R;
import com.example.android.pendomoviz.adapter.ReviewsAdapter;
import com.example.android.pendomoviz.adapter.TrailersAdapter;
import com.example.android.pendomoviz.model.Moviz;
import com.example.android.pendomoviz.model.Reviews;
import com.example.android.pendomoviz.model.ReviewsResponse;
import com.example.android.pendomoviz.model.Trailers;
import com.example.android.pendomoviz.model.TrailersResponse;
import com.example.android.pendomoviz.rest.TMdbApiClient;
import com.example.android.pendomoviz.rest.TMdbApiInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class TrailersFragment extends Fragment {


    RecyclerView mRecyclerView;


    private TrailersAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Moviz moviz;

    ArrayList<Trailers> myTrailers;



    private final static String API_KEY = BuildConfig.TMDB_API_KEY;

    final TMdbApiInterface tMdbApiInterface = TMdbApiClient.getClient().create(TMdbApiInterface.class);
    List<Trailers> movizs;
    public TrailersFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_trailers_fragment, container, false);

        initViews(rootView);
        myTrailers = new ArrayList<>();

        Bundle bundle = this.getArguments();

        if(bundle != null) {
            moviz = bundle.getParcelable("movieDetails");

        }


        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

      //  getTrailers();


        return rootView;
    }


    public void initViews(View rootView) {


        mRecyclerView = rootView.findViewById(R.id.rvMovizTrailers);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {

            myTrailers = savedInstanceState.getParcelableArrayList("myTrailers");
            movizs = myTrailers;
            mAdapter = new TrailersAdapter(movizs, getContext(), new TrailersAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(Trailers movizitem) {

                }
            });

            mRecyclerView.setAdapter(mAdapter);
        }
        else {
            getTrailers();
        }
    }

    /**
     * This method will get the Trailers for each movie from TMDB
     */
    public void getTrailers(){

        int movieId = moviz.getId();
        Call<TrailersResponse> call = tMdbApiInterface.getTrailersById( movieId, API_KEY);
        //linlaHeaderProgress.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<TrailersResponse>() {
            @Override
            public void onResponse(Call<TrailersResponse> call, Response<TrailersResponse> response) {
                if (response.isSuccessful()) {
                    movizs = response.body().getResults();
                    myTrailers = (ArrayList)movizs;

                    mAdapter = new TrailersAdapter(movizs, getContext(), new TrailersAdapter.OnItemClickListener(){

                    @Override
                    public void onItemClick(Trailers movizitem) {

                    }
                });

                    mRecyclerView.setAdapter(mAdapter);


                    Log.d(TAG, " Number of Trailers Received:" + movizs.size());



                } else {

                    Toast.makeText(getContext(), "Server returned an error", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure (Call <TrailersResponse> call, Throwable t){
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

        outState.putParcelableArrayList("myTrailers", myTrailers);
    }
}
