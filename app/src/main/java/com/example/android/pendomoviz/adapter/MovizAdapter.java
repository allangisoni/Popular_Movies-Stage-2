package com.example.android.pendomoviz.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.pendomoviz.activity.DetailsActivity;
import com.example.android.pendomoviz.R;
import com.example.android.pendomoviz.model.Moviz;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovizAdapter extends RecyclerView.Adapter<MovizViewHolder> {


    private final List<Moviz> movizlist;
    private final Context context;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Moviz movizitem);
    }

    public  MovizAdapter(List<Moviz> movizlist, Context context, OnItemClickListener listener){

        this.movizlist = movizlist;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_list, parent, false);
        return new MovizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovizViewHolder holder, int position) {

        holder.bind(movizlist.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return movizlist.size();
    }


}
