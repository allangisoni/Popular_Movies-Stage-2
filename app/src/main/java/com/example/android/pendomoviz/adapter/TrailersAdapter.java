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
import com.example.android.pendomoviz.model.Trailers;

import java.util.List;

public class TrailersAdapter  extends RecyclerView.Adapter<TrailersViewHolder>{

    private final List<Trailers> movizlist;
    private final Context context;
    private final TrailersAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Trailers movizitem);
    }

    public  TrailersAdapter(List<Trailers> movizlist, Context context, TrailersAdapter.OnItemClickListener listener) {

        this.movizlist = movizlist;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_trailers_list, parent, false);

        return new TrailersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {

        holder.bind(movizlist.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return movizlist.size();
    }
}
