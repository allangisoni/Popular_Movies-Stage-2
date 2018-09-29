
package com.example.android.pendomoviz.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.pendomoviz.R;
import com.example.android.pendomoviz.model.Moviz;
import com.example.android.pendomoviz.model.Reviews;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsViewHolder> {

    private final List<Reviews> movizlist;
    private final Context context;


    public  ReviewsAdapter(List<Reviews> movizlist, Context context){

        this.movizlist = movizlist;
        this.context = context;

    }
    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_reviews_list, parent, false);
        return new  ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {

        Reviews moviz = movizlist.get(position);
        holder.tvAuthor.setText(moviz.getAuthor());
        holder.tvReviewDescription.setText(moviz.getContent());
    }

    @Override
    public int getItemCount() {
        return movizlist.size();
    }
}
