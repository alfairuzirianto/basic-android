package com.example.androidsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBConfig config;
    SQLiteDatabase db;
    Cursor cursor;
    RecyclerView rvMain;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layout;

    ArrayList ids, titles;
    ImageView linkAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config = new DBConfig(this);
        linkAdd = findViewById(R.id.link_add);

        linkAdd.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
        });
    }

    @Override
    protected void onResume() {
        showData();
        super.onResume();
    }

    public void showData() {
        ids = new ArrayList<>();
        titles = new ArrayList<>();

        layout = new LinearLayoutManager(this);
        adapter = new DataAdapter(this, ids, titles);
        rvMain = findViewById(R.id.rv_main);

        rvMain.setLayoutManager(layout);
        rvMain.setHasFixedSize(true);
        rvMain.setAdapter(adapter);

        db = config.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_note", null);
        cursor.moveToFirst();

        for(int count = 0; count < cursor.getCount(); count++) {
            cursor.moveToPosition(count);
            ids.add(cursor.getString(0));
            titles.add(cursor.getString(1));
        }
    }
}