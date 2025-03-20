package com.example.djmag;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvDjs;
    private ArrayList<Dj> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        rvDjs = findViewById(R.id.rv_djs);
        rvDjs.setHasFixedSize(true);

        list.addAll(getListDjs());
        showRecyclerList();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void goToAbout(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public ArrayList<Dj> getListDjs() {
        String[] djsRank = getResources().getStringArray(R.array.djs_rank);
        String[] djsName = getResources().getStringArray(R.array.djs_name);
        String[] djsDescription = getResources().getStringArray(R.array.djs_desc);
        String[] djsLink = getResources().getStringArray(R.array.djs_link);
        String[] songsTitle = getResources().getStringArray(R.array.songs_title);
        String[] songsArtists = getResources().getStringArray(R.array.songs_artists);
        String[] songsLink = getResources().getStringArray(R.array.songs_link);
        TypedArray djsPhoto = getResources().obtainTypedArray(R.array.djs_photo);

        ArrayList<Dj> listDj = new ArrayList<>();
        for (int i = 0; i < djsName.length; i++) {
            Dj dj = new Dj();
            dj.setName(djsName[i]);
            dj.setDescription(djsDescription[i]);
            dj.setRank(djsRank[i]);
            dj.setPhoto(djsPhoto.getResourceId(i, -1));
            dj.setSong(songsTitle[i]);
            dj.setSongArtists(songsArtists[i]);
            dj.setDjLink(djsLink[i]);
            dj.setSongLink(songsLink[i]);

            listDj.add(dj);
        }
        return listDj;
    }

    private void showRecyclerList() {
        rvDjs.setLayoutManager(new LinearLayoutManager(this));
        ListDjAdapter listDjAdapter = new ListDjAdapter(list);
        rvDjs.setAdapter(listDjAdapter);
    }
}