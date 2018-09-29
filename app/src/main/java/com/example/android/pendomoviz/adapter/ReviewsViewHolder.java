package com.example.android.pendomoviz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.pendomoviz.R;

public class ReviewsViewHolder extends RecyclerView.ViewHolder {

    public TextView tvAuthor, tvReviewDescription;

    public ReviewsViewHolder(View itemView) {
        super(itemView);
        tvAuthor = itemView.findViewById(R.id.tVAuthor);
        tvReviewDescription = itemView.findViewById(R.id.tvReviewDescription);
    }
}
