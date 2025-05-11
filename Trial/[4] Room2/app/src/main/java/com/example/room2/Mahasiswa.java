package com.example.room2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.*;

@Entity(tableName = "tb_mahasiswa")
public class Mahasiswa implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nim")
    public int idMahasiswa;

    @ColumnInfo(name = "nama")
    public String namaMahasiswa;

    protected Mahasiswa() {}

    protected Mahasiswa(Parcel in) {
        idMahasiswa = in.readInt();
        namaMahasiswa = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idMahasiswa);
        dest.writeString(namaMahasiswa);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel in) {
            return new Mahasiswa(in);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };
}
