package com.example.room1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MahasiswaDAO {
    @Query("SELECT  * FROM tb_mahasiswa")
    List<Mahasiswa> getAllMahasiswa();

    @Insert
    void insertMahasiswa(Mahasiswa mahasiswa);

    @Delete
    void deleteMahasiswa(Mahasiswa mahasiswa);

    @Update
    void updateMahasiswa(Mahasiswa mahasiswa);

    @Query("SELECT * FROM tb_mahasiswa WHERE nim LIKE :nim")
    Mahasiswa findMahasiswa(int nim);
}
