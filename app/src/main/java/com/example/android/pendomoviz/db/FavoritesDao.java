package com.example.android.pendomoviz.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.android.pendomoviz.Favorites;
import com.example.android.pendomoviz.model.Moviz;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface FavoritesDao {

    @Query("SELECT * FROM Moviz WHERE isMovieFavorite='true'")
    LiveData<List<Moviz>> getAllFavoriteMovies();


   @Query("SELECT * FROM Moviz where id = :uid")
    Moviz getFavoriteMoviebyId(String uid);


    @Insert(onConflict = REPLACE)
    void insertOnlySingleMovie (Moviz favorites);

    @Delete()
    void delete (Moviz favorites);
}
