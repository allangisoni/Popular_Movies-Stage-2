package com.example.android.pendomoviz.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.android.pendomoviz.model.Moviz;

@Database(entities = {Moviz.class}, version =5)
public abstract class FavoritesDb extends RoomDatabase {


    private static final String DATABASE_NAME = "TMDfavoritemovies_db";
    private static FavoritesDb INSTANCE;



    public static FavoritesDb getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), FavoritesDb.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public abstract FavoritesDao favoriteMoviesList();

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
