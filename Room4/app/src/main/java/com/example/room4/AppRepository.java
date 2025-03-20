package com.example.room4;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppRepository {
    private AppDatabase db;
    private MahasiswaDAO dao;
    private LiveData<List<Mahasiswa>> listMahasiswa;

    public AppRepository(Application application) {
        db = AppDatabase.getDatabase(application);
        dao = db.dao();
        listMahasiswa = dao.getListMahasiswa();
    }

    public LiveData<List<Mahasiswa>> getListMahasiswa() {
        return db.dao().getListMahasiswa();
    }

    public void insertMahasiswa(Mahasiswa mahasiswa) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.dao().insertMahasiswa(mahasiswa);
                return null;
            }
        }.execute();
    }

    public void updateMahasiswa(Mahasiswa mahasiswa) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.dao().updateMahasiswa(mahasiswa);
                return null;
            }
        }.execute();
    }

    public void deleteMahasiswa(Mahasiswa mahasiswa) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.dao().deleteMahasiswa(mahasiswa);
                return null;
            }
        }.execute();
    }

}
