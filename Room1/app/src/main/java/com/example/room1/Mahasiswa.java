package com.example.room1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_mahasiswa")
public class Mahasiswa {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nim")
    private int mahasiswaId;

    @NonNull
    @ColumnInfo(name = "nama")
    private String mahasiswaNama;

    public Mahasiswa(@NonNull String mahasiswaNama) {
        this.mahasiswaNama = mahasiswaNama;
    }

    public int getMahasiswaId() {
        return mahasiswaId;
    }

    public void setMahasiswaId(int mahasiswaId) {
        this.mahasiswaId = mahasiswaId;
    }

    @NonNull
    public String getMahasiswaNama() {
        return mahasiswaNama;
    }

    public void setMahasiswaNama(@NonNull String mahasiswaNama) {
        this.mahasiswaNama = mahasiswaNama;
    }

    @Override
    public String toString() {
        return "Mahasiswa{" +
                "mahasiswaId=" + mahasiswaId +
                ", mahasiswaNama='" + mahasiswaNama + '\'' +
                '}';
    }
}
