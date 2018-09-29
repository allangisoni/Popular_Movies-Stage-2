package com.example.android.pendomoviz.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.pendomoviz.db.FavoritesDb;
import com.example.android.pendomoviz.model.Moviz;

public class AddFavoriteViewModel extends AndroidViewModel{


    private FavoritesDb appDatabase;

    public AddFavoriteViewModel(@NonNull Application application) {
        super(application);

        appDatabase = FavoritesDb.getAppDatabase(this.getApplication());


    }

    public void addFavorite(final Moviz moviz) {
        new addAsyncTask(appDatabase).execute(moviz);
    }

    private static class addAsyncTask extends AsyncTask<Moviz, Void, Void> {

        private FavoritesDb db;

        addAsyncTask(FavoritesDb appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Moviz... params) {
            db.favoriteMoviesList().insertOnlySingleMovie(params[0]);
            return null;
        }
    }
}
