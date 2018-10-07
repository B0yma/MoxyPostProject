package com.boyma.mxsmpl.localdb.models;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ImageEntity.class}, version = 1,exportSchema = false)//tables
public abstract class AppDatabase extends RoomDatabase {
    public abstract ImageEntityDao imageEntityDao();
}

