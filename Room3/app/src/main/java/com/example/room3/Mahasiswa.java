package com.example.room3;

import androidx.room.*;

@Entity(tableName = "tb_mahasiswa")
public class Mahasiswa {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nim")
    public int id;


    @ColumnInfo(name = "nama")
    public String name;

    public Mahasiswa() {
    }

    public Mahasiswa(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
