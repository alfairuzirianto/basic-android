package com.example.androidsqlite;

import android.database.Cursor;
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

public class EditActivity extends AppCompatActivity {
    DBConfig config;
    SQLiteDatabase db;
    Cursor cursor;

    EditText etJudul, etDeskripsi;
    Button btnSave;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        config = new DBConfig(this);
        db = config.getReadableDatabase();
        id = getIntent().getStringExtra("id");

        etJudul = findViewById(R.id.et_judul);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(v -> editData());

        showData();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void editData() {
        String title = etJudul.getText().toString();
        String description = etDeskripsi.getText().toString();

        if(title.isBlank() || description.isBlank()) {
            Toast.makeText(this, "Mohon isi semua kolom!", Toast.LENGTH_SHORT).show();
        } else {
            db.execSQL("UPDATE tb_note SET title ='"+title+"', description='"+description+"'  WHERE id='"+id+"'");
            Toast.makeText(this, "Berhasil update!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void showData() {
        db = config.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_note WHERE id ='" + id + "'", null);
        cursor.moveToFirst();
        etJudul.setText(cursor.getString(1));
        etDeskripsi.setText(cursor.getString(2));
    }
}