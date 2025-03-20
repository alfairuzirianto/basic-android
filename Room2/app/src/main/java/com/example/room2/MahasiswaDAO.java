package com.example.room2;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface MahasiswaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMahasiswa(Mahasiswa mahasiswa);

    @Update
    void updateMahasiswa(Mahasiswa mahasiswa);

    @Delete
    void deleteMahasiswa(Mahasiswa mahasiswa);

    @Query("SELECT * FROM tb_mahasiswa")
    List<Mahasiswa> getAllMahasiswa();

    @Query("SELECT * FROM tb_mahasiswa")
    LiveData<List<Mahasiswa>> getAllMahasiswaLive();
}
