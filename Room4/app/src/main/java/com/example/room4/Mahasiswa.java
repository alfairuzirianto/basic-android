package com.example.room4;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_mahasiswa")
public class Mahasiswa {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nim")
    private int id;

    @ColumnInfo(name = "nama")
    private String name;

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
