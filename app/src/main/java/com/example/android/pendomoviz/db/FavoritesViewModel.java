package com.example.android.pendomoviz.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.pendomoviz.db.FavoritesDb;
import com.example.android.pendomoviz.model.Moviz;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

    private final LiveData<List<Moviz>> favoritesList;

    private FavoritesDb appDatabase;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);

        appDatabase = FavoritesDb.getAppDatabase(this.getApplication());

        favoritesList = appDatabase.favoriteMoviesList().getAllFavoriteMovies();
    }

    public LiveData<List<Moviz>> getFavoritesList() {
        return favoritesList;
    }

    public void deleteItem(Moviz favorites) {
        new deleteAsyncTask(appDatabase).execute(favorites);
    }

    private static class deleteAsyncTask extends AsyncTask<Moviz, Void, Void> {

        private FavoritesDb db;

        deleteAsyncTask(FavoritesDb appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Moviz... params) {
            db.favoriteMoviesList().delete(params[0]);
            return null;
        }

    }

}
