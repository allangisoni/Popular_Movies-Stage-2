package com.example.android.pendomoviz.adapter;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.android.pendomoviz.activity.DetailsMain;
import com.example.android.pendomoviz.R;
import com.example.android.pendomoviz.model.Moviz;
import com.squareup.picasso.Picasso;

public class MovizViewHolder extends RecyclerView.ViewHolder {

    public final ImageView thumbnailImage;
    private static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w500";
    public MovizViewHolder(View itemView) {
        super(itemView);
        thumbnailImage = itemView.findViewById(R.id.thumbnail_image);


    }

    public void bind(final Moviz movizitem, final MovizAdapter.OnItemClickListener listener) {

        Picasso.with(itemView.getContext()).load(IMAGE_URL_BASE_PATH + movizitem.getPosterPath()).into(thumbnailImage);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(movizitem);
                Intent intent = new Intent(itemView.getContext(), DetailsMain.class);
                intent.putExtra("movieDetails", movizitem);
                itemView.getContext().startActivity(intent);
            }
        });
    }

}
