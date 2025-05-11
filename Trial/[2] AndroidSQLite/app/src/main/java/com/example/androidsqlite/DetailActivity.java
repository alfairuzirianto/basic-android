package com.example.androidsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity {
    String id;
    TextView tvTitle, tvDescription;
    DBConfig config;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        config = new DBConfig(this);
        db = config.getReadableDatabase();
        tvTitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_description);

        id = getIntent().getExtras().getString("id");
        showData();
    }

    private void showData() {
        db = config.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_note WHERE id ='" + id + "'", null);
        cursor.moveToFirst();
        tvTitle.setText(cursor.getString(1));
        tvDescription.setText(cursor.getString(2));
    }
}