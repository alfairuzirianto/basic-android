package com.example.room2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.room2.databinding.ActivityMainBinding;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements OnClickItemInterface {
    private ActivityMainBinding binding;
    private MahasiswaViewModel mahasiswaViewModel;
    private MahasiswaAdapter adapter;
    private Dialog dialogTambah;
    private EditText etNama;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rvUtama.setLayoutManager(new LinearLayoutManager(this));
        mahasiswaViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MahasiswaViewModel.class);
        adapter = new MahasiswaAdapter(this,this, mahasiswaViewModel);
        binding.rvUtama.setAdapter(adapter);

        binding.btnTambah.setOnClickListener(v -> {
//            startActivity(new Intent(MainActivity.this, TambahMahasiswaActivity.class));
            dialogTambah = new Dialog(MainActivity.this);
            dialogTambah.setContentView(R.layout.dialog_tambah);
            dialogTambah.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialogTambah.show();
            etNama = dialogTambah.findViewById(R.id.et_nama);
            btn = dialogTambah.findViewById(R.id.btn_tambah);
            btn.setOnClickListener(v1 -> {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.namaMahasiswa = etNama.getText().toString();
                mahasiswaViewModel.insertMahasiswa(mahasiswa);
                dialogTambah.dismiss();
                Toast.makeText(this, "Berhasil ditambah", Toast.LENGTH_SHORT).show();
            });
        });

        syncData();
    }

    @Override
    public void onClickItem(Mahasiswa mahasiswa, boolean isEdit) {
        if(isEdit) {
            Intent intent = new Intent(MainActivity.this, TambahMahasiswaActivity.class);
            intent.putExtra("mahasiswa", mahasiswa);
            startActivity(intent);
        } else {
            mahasiswaViewModel.deleteMahasiswa(mahasiswa);
        }
    }

    public void syncData() {
        mahasiswaViewModel.getAllMahasiswaLive().observe(MainActivity.this, new Observer<List<Mahasiswa>>() {
            @Override
            public void onChanged(List<Mahasiswa> listMahasiswa) {
                if(listMahasiswa != null) {
                    adapter.setListMahasiswa(listMahasiswa);
                }
            }
        });
    }
}