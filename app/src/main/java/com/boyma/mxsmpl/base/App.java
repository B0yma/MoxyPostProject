package com.boyma.mxsmpl.base;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.Environment;

import com.boyma.mxsmpl.R;
import com.boyma.mxsmpl.localdb.models.AppDatabase;

import java.io.File;

public class App extends Application {

    public static App instance;

    private AppDatabase database;
    private File sf;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();

        initAppFolder();
    }

    private void initAppFolder() {
        sf = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),getString(R.string.app_name));
        if(!sf.exists()){
            sf.mkdir();
        }
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public File getAppDir() {
        return sf;
    }
}
