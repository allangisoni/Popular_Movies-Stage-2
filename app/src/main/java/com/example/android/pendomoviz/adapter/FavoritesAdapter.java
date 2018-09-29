package com.example.android.pendomoviz.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.pendomoviz.Favorites;
import com.example.android.pendomoviz.R;
import com.example.android.pendomoviz.model.Moviz;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {

    private  List<Moviz> favoritesList;
    private final Context context;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Moviz movizitem);
    }

    public  FavoritesAdapter(List<Moviz> favorites, Context context, OnItemClickListener listener){

        this.favoritesList = favorites;
        this.context = context;
        this.listener = listener;

    }
    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_movies_list, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {

        holder.bind(favoritesList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    public void addFavorites(List<Moviz> favoritesMoviesList) {
        this.favoritesList =  favoritesMoviesList;
        notifyDataSetChanged();
    }
}
