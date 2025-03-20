package com.example.djmag;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Dj dataDj = getIntent().getParcelableExtra("key_dj");

        ImageView imgDetail = findViewById(R.id.img_detail);
        TextView detailName = findViewById(R.id.detail_name);
        TextView detailRank = findViewById(R.id.detail_rank);
        TextView detailDesc = findViewById(R.id.detail_description);
        TextView detailSong = findViewById(R.id.tv_song_title);
        TextView detailSongArtists = findViewById(R.id.tv_song_artists);

        // Set data DJ ke tampilan
        imgDetail.setImageResource(dataDj.getPhoto());
        detailName.setText(dataDj.getName());
        detailRank.setText(dataDj.getRank());
        detailDesc.setText(dataDj.getDescription());
        detailSong.setText(dataDj.getSong());
        detailSongArtists.setText(dataDj.getSongArtists());

        ImageButton shareAction = findViewById(R.id.action_share);
        shareAction.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, dataDj.getDjLink());
            startActivity(Intent.createChooser(intent, "Bagikan link melalui"));
        });

        ImageButton btnPlay = findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dataDj.getSongLink()));
            startActivity(intent);
        });

        ImageButton btnLike = findViewById(R.id.btn_like);
        TextView tvLike = findViewById(R.id.tv_like);
        btnLike.setOnClickListener(v -> {
            if (btnLike.getTag().equals("Unliked")) {
                btnLike.setBackground(getDrawable(R.drawable.ic_favorite_active));
                tvLike.setText("Unlike");
                btnLike.setTag("Liked");
            } else {
                btnLike.setBackground(getDrawable(R.drawable.ic_favorite));
                tvLike.setText("Like");
                btnLike.setTag("Unliked");
            }
        });
    }

    public void backToMain(View view) {
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
