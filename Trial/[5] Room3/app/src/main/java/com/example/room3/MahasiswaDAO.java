package com.example.room3;

import androidx.room.*;

import java.util.List;

@Dao
public interface MahasiswaDAO {
    @Insert
    public void addMahasiswa(Mahasiswa mahasiswa);

    @Update
    public void updateMahasiswa(Mahasiswa mahasiswa);

    @Delete
    public void deleteMahasiswa(Mahasiswa mahasiswa);

    @Query("SELECT * FROM tb_mahasiswa")
    public List<Mahasiswa> getAllMahasiswa();

    @Query("SELECT * FROM tb_mahasiswa WHERE nim == :id")
    public Mahasiswa getMahasiswa(int id);
}
