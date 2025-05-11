package com.example.latianfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailItem extends AppCompatActivity {
    TextView tvNameDetail, tvPriceDetail;
    ImageView imgDetail;
    ImageButton ibCartDetail, ibMenuDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);

        tvNameDetail = findViewById(R.id.tv_name_detail);
        tvPriceDetail = findViewById(R.id.tv_price_detail);
        imgDetail = findViewById(R.id.img_detail);

        // Get data
        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        String itemPrice = intent.getStringExtra("itemPrice");
        int itemImg = intent.getIntExtra("itemImg", 0);

        tvNameDetail.setText(itemName);
        tvPriceDetail.setText(itemPrice);
        imgDetail.setImageResource(itemImg);


        ibCartDetail = findViewById(R.id.ib_cart_detail);
        ibMenuDetail = findViewById(R.id.ib_menu_detail);

        // Toast onClick
        ibCartDetail.setOnClickListener(v -> {
            Toast.makeText(DetailItem.this, "Tombol ini belum tersedia!", Toast.LENGTH_SHORT).show();
        });

        ibMenuDetail.setOnClickListener(v -> {
            Toast.makeText(DetailItem.this, "Tombol ini belum tersedia!", Toast.LENGTH_SHORT).show();
        });
    }

    public void back(View v) {
        Intent intent = new Intent(DetailItem.this, MainActivity.class);
        startActivity(intent);
    }
}
