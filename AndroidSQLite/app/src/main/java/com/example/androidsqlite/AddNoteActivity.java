package com.example.androidsqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddNoteActivity extends AppCompatActivity {

    Button btnAdd;
    EditText etJudul, etDeskripsi;
    DBConfig config;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        btnAdd = findViewById(R.id.btn_add);
        etJudul = findViewById(R.id.et_judul);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        config = new DBConfig(this);
        db = config.getReadableDatabase();

        btnAdd.setOnClickListener(v -> addNote());
    }

    private void addNote() {
        String title = etJudul.getText().toString();
        String description = etDeskripsi.getText().toString();

        if(title.isBlank() || description.isBlank()) {
            Toast.makeText(this, "Mohon isi semua kolom!", Toast.LENGTH_SHORT).show();
        } else {
            db.execSQL("INSERT INTO tb_note (title, description) VALUES ('" + title + "', '" + description + "')");
            Toast.makeText(this, "Catatan berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}