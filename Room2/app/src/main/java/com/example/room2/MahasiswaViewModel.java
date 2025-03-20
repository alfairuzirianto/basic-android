package com.example.room2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MahasiswaViewModel extends AndroidViewModel {
    private AppRepository repo;

    public MahasiswaViewModel(@NonNull Application application) {
        super(application);
        repo = new AppRepository(application);
    }

    public void insertMahasiswa(Mahasiswa mahasiswa) {
        repo.insertMahasiswa(mahasiswa);
    }

    public void updateMahasiswa(Mahasiswa mahasiswa) {
        repo.updateMahasiswa(mahasiswa);
    }

    public void deleteMahasiswa(Mahasiswa mahasiswa) {
        repo.deleteMahasiswa(mahasiswa);
    }

    public List<Mahasiswa> getAllMahasiswa() {
        return repo.getAllMahasiswa();
    }

    public LiveData<List<Mahasiswa>> getAllMahasiswaLive() {
        return repo.getAllMahasiswaLive();
    }
}
