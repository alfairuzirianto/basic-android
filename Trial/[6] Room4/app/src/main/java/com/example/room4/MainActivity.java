package com.example.room4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MahasiswaAdapter.ClickListener {
    RecyclerView rvUtama;
    MahasiswaAdapter adapter;
    AppViewModel viewModel;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(AppViewModel.class);
        rvUtama = findViewById(R.id.rv_utama);
        btnAdd = findViewById(R.id.btn_tambah);
        rvUtama.setLayoutManager(new LinearLayoutManager(this));
        rvUtama.setHasFixedSize(true);

        adapter = new MahasiswaAdapter(this);

        viewModel.getListMahasiswa().observe(this, new Observer<List<Mahasiswa>>() {
            @Override
            public void onChanged(List<Mahasiswa> listMahasiswa) {
                if (!listMahasiswa.isEmpty()) {
                    adapter.setData(listMahasiswa);
                    rvUtama.setAdapter(adapter);
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMahasiswa();
            }
        });
    }

    public void addMahasiswa() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.row_add, null);

        builder.setView(v);
        AlertDialog dialog = builder.create();
        dialog.show();

        Button btnTambah = v.findViewById(R.id.btn_tambah);
        EditText etNama = v.findViewById(R.id.et_nama);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNama.getText() != null) {
                    String nama = etNama.getText().toString();
                    Mahasiswa mahasiswa = new Mahasiswa();
                    mahasiswa.setName(nama);

                    viewModel.insertMahasiswa(mahasiswa);
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void updateClicked(Mahasiswa mahasiswa) {
        updateMahasiswa(mahasiswa);
    }

    @Override
    public void deleteClicked(Mahasiswa mahasiswa) {
        viewModel.deleteMahasiswa(mahasiswa);
    }

    public void updateMahasiswa(Mahasiswa mahasiswa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.row_add, null);

        builder.setView(v);
        AlertDialog dialog = builder.create();
        dialog.show();

        Button btnTambah = v.findViewById(R.id.btn_tambah);
        btnTambah.setText("Ubah");
        EditText etNama = v.findViewById(R.id.et_nama);
        etNama.setText(mahasiswa.getName());

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNama.getText() != null) {
                    String nama = etNama.getText().toString();
                    mahasiswa.setName(nama);

                    viewModel.updateMahasiswa(mahasiswa);
                    dialog.dismiss();
                }
            }
        });
    }
}