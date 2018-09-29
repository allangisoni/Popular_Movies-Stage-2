package com.example.android.pendomoviz.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.pendomoviz.R;

import com.example.android.pendomoviz.model.Trailers;


public class TrailersViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTrailerName;
    public TrailersViewHolder(View itemView) {
        super(itemView);

        tvTrailerName = itemView.findViewById(R.id.tvTrailerName);
    }

    public void bind(final Trailers movizitem, final TrailersAdapter.OnItemClickListener listener) {

       // Picasso.with(itemView.getContext()).load(movizitem.getPosterPath()).into(thumbnailImage);

        tvTrailerName.setText(movizitem.getName());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(movizitem);

                int position = getAdapterPosition();
                 if(position != RecyclerView.NO_POSITION){
                     String videoId = movizitem.getKey();
                     Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId));
                     itemView.getContext().startActivity(intent);
                 }


            }
        });
    }
}
