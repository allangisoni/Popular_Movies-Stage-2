package com.example.android.pendomoviz.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.pendomoviz.activity.DetailsMain;
import com.example.android.pendomoviz.R;
import com.example.android.pendomoviz.model.Moviz;
import com.squareup.picasso.Picasso;

import static android.content.ContentValues.TAG;

public class FavoritesViewHolder extends RecyclerView.ViewHolder {


    private final ImageView thumbnailImage;
    private static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w500";



    public FavoritesViewHolder(View itemView) {
        super(itemView);
        thumbnailImage = itemView.findViewById(R.id.favoritethumbnail_image);

    }

    public void bind(final Moviz favoritemovies, final FavoritesAdapter.OnItemClickListener listener) {
       // Toast.makeText(itemView.getContext(), "" + favoritemovies.getPosterPath(), Toast.LENGTH_LONG).show();
        Picasso.with(itemView.getContext()).load(IMAGE_URL_BASE_PATH + favoritemovies.getPosterPath()).into(thumbnailImage);
        Log.d(TAG, favoritemovies.getBackdropPath());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(favoritemovies);
                Intent intent = new Intent(itemView.getContext(), DetailsMain.class);
                favoritemovies.setPopularity(2.0);
                intent.putExtra("movieDetails", favoritemovies);
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
