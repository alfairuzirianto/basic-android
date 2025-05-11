package com.example.room3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    List<Mahasiswa> listMahasiswa;
    private EditText etNama;
    private Button btnTambah, btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama=findViewById(R.id.et_nama);
        btnTambah=findViewById(R.id.btn_tambah);
        btnGet=findViewById(R.id.btn_get);

        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
        };

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "database_mi.db").addCallback(myCallBack).build();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();

                Mahasiswa mahasiswa = new Mahasiswa(nama);
                addMahasiswa(mahasiswa);
                etNama.setText("");
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllMahasiswa();
            }
        });
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.dao().addMahasiswa(mahasiswa);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Berhasil ditambah", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void getAllMahasiswa() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                listMahasiswa = db.dao().getAllMahasiswa();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        for (Mahasiswa mahasiswa : listMahasiswa) {
                            sb.append(mahasiswa.getId()+" "+mahasiswa.getName());
                            sb.append("\n");
                        }
                        String finalData = sb.toString();
                        Toast.makeText(MainActivity.this, finalData, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}