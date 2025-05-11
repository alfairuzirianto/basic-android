package com.example.room2;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private AppDatabase db;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AppRepository(Context context) {
        db = AppDatabase.getInstance(context);
    }

    public void insertMahasiswa(Mahasiswa mahasiswa) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.dao().insertMahasiswa(mahasiswa);
            }
        });
    }

    public void updateMahasiswa(Mahasiswa mahasiswa) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.dao().updateMahasiswa(mahasiswa);
            }
        });
    }

    public void deleteMahasiswa(Mahasiswa mahasiswa) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.dao().deleteMahasiswa(mahasiswa);
            }
        });
    }

    public List<Mahasiswa> getAllMahasiswa() {
        return db.dao().getAllMahasiswa();
    }

    public LiveData<List<Mahasiswa>> getAllMahasiswaLive() {
        return db.dao().getAllMahasiswaLive();
    }
}
