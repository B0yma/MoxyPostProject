package com.boyma.mxsmpl.localdb.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface ImageEntityDao {
    @Query("SELECT * FROM ImageEntity")
    Maybe<List<ImageEntity>> getAll();

    @Query("SELECT photoUri FROM ImageEntity")
    Maybe<List<String>> getPhotoUris();

    @Query("SELECT * FROM ImageEntity WHERE id = :id")
    Maybe<List<ImageEntity>> getById(long id);

    @Insert
    void insert(ImageEntity employee);

    @Update
    void update(ImageEntity employee);

    @Delete
    void delete(ImageEntity employee);
}
