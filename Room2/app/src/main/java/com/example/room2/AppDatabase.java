package com.example.room2;

import android.content.Context;

import androidx.room.*;

@Database(entities = {Mahasiswa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static volatile AppDatabase INSTANCE;
    public static final String DB_NAME = "database_mi.db";
    public abstract MahasiswaDAO dao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME).build();
                }
            }
        }
        return INSTANCE;
    }
}
