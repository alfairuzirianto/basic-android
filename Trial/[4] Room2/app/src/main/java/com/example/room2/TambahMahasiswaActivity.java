package com.example.room2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.room2.databinding.ActivityTambahMahasiswaBinding;

public class TambahMahasiswaActivity extends AppCompatActivity {
    ActivityTambahMahasiswaBinding binding;
    private String nama;
    private MahasiswaViewModel mahasiswaViewModel;
    private boolean isEdit;
    private Mahasiswa mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTambahMahasiswaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mahasiswaViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MahasiswaViewModel.class);

        if(getIntent().hasExtra("mahasiswa")) {
            binding.btnTambah.setText("Ubah");
            mahasiswa = getIntent().getParcelableExtra("mahasiswa");
            binding.etNama.setText(mahasiswa.namaMahasiswa);
            isEdit = true;
        }

        binding.btnTambah.setOnClickListener(v -> {
            nama = binding.etNama.getText().toString();
            if (isEdit) {
                mahasiswa.namaMahasiswa = nama;
                mahasiswaViewModel.updateMahasiswa(mahasiswa);
            } else {
                mahasiswa = new Mahasiswa();
                mahasiswa.namaMahasiswa = nama;
                mahasiswaViewModel.insertMahasiswa(mahasiswa);

            }
            finish();
        });
    }
}