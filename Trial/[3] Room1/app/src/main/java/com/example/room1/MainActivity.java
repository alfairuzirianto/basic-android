package com.example.room1;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> listNim;
    ArrayList<String> listNama;
    RecyclerView rvUtama;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layout;

    ImageView ivTambah;
    Dialog dialogTambah;
    Button btnTambah;
    EditText etNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUtama = findViewById(R.id.rv_utama);
        getAllData();

        ivTambah = findViewById(R.id.iv_tambah);
        ivTambah.setOnClickListener(v -> {
            dialogTambah = new Dialog(MainActivity.this);
            dialogTambah.setContentView(R.layout.dialog_tambah);
            dialogTambah.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialogTambah.show();
            insertData();
        });
    }

    class InsertAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        @Override
        protected Void doInBackground(Mahasiswa... listMahasiswa) {
            AppDatabase.getInstance(getApplicationContext())
                    .dao().insertMahasiswa(listMahasiswa[0]);
            return null;
        }
    }

    public void insertData() {
        etNama = dialogTambah.findViewById(R.id.et_nama);
        btnTambah = dialogTambah.findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(v1 -> {
            if(etNama.getText().toString().isBlank()){
                Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                Mahasiswa mahasiswa = new Mahasiswa(etNama.getText().toString());
                InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
                insertAsyncTask.execute(mahasiswa);
                dialogTambah.dismiss();
                getAllData();
            }
        });
    }

    public void getAllData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Mahasiswa> listMahasiswa = AppDatabase.getInstance(getApplicationContext())
                        .dao().getAllMahasiswa();
                runOnUiThread(() -> showData(listMahasiswa));
            }
        });
        thread.start();
    }

    public void showData(List<Mahasiswa> listMahasiswa) {
        listNim = new ArrayList<>();
        listNama = new ArrayList<>();

        layout = new LinearLayoutManager(this);
        adapter = new MahasiswaAdapter(this, listNim, listNama);

        rvUtama.setLayoutManager(layout);
        rvUtama.setHasFixedSize(true);
        rvUtama.setAdapter(adapter);

        for(Mahasiswa mahasiswa : listMahasiswa) {
            listNim.add(mahasiswa.getMahasiswaId());
            listNama.add(mahasiswa.getMahasiswaNama());
        }
    }
}