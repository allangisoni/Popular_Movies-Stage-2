package com.example.android.pendomoviz.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.pendomoviz.R;

import com.example.android.pendomoviz.model.Trailers;


public class TrailersViewHolder extends RecyclerView.ViewHolder {

    private static final String YOUTUBE_APP_PACKAGE = "com.google.android.youtube";
    //private static final String YOUTUBE_URL_APP = "vnd.youtube://";
    private static final String YOUTUBE_URL_BROWSER = "https://www.youtube.com/watch";
    private static final String VIDEO_PARAMETER = "v";
    public TextView tvTrailerName;
    public ImageView IvTrailerShare;
    public TrailersViewHolder(View itemView) {
        super(itemView);

        tvTrailerName = itemView.findViewById(R.id.tvTrailerName);
        IvTrailerShare = itemView.findViewById(R.id.IvShareTrailer);


    }

    public void bind(final Trailers movizitem, final TrailersAdapter.OnItemClickListener listener) {

       // Picasso.with(itemView.getContext()).load(movizitem.getPosterPath()).into(thumbnailImage);
       final String videoId = movizitem.getKey();
        tvTrailerName.setText(movizitem.getName());
        tvTrailerName.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(movizitem);

                int position = getAdapterPosition();
                 if(position != RecyclerView.NO_POSITION) {


                     if (itemView.getContext().getPackageManager().getLaunchIntentForPackage(YOUTUBE_APP_PACKAGE) != null) {
                        Intent  intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
                         itemView.getContext().startActivity(intent);

                     } else {
                  Intent intent;
                         Uri uri = Uri.parse(YOUTUBE_URL_BROWSER)
                                 .buildUpon()
                                 .appendQueryParameter(VIDEO_PARAMETER, videoId)
                                 .build();
                         intent = new Intent(Intent.ACTION_VIEW, uri);
                     }

                 }
            }
        });


        IvTrailerShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {


                    String shareBody = "https://www.themoviedb.org/movie/" + videoId;
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Video Url");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    itemView.getContext().startActivity(Intent.createChooser(sharingIntent,"Trailer Video"));
                }
            }
        });
    }




}
