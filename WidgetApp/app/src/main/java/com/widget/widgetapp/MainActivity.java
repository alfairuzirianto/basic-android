package com.widget.widgetapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button imgV,inputType,listView,msgBox,rButton,spinner,typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgV = findViewById(R.id.imgV);
        inputType = findViewById(R.id.inputType);
        listView = findViewById(R.id.listView);
        msgBox = findViewById(R.id.msgBox);
        rButton = findViewById(R.id.rButton);
        spinner = findViewById(R.id.spinner);
        typeface = findViewById(R.id.typeface);

        imgV.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ImageViewActivity.class));
        });

        inputType.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InputTypeActivity.class));
        });

        listView.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ListViewActivity.class));
        });

        msgBox.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MsgViewActivity.class));
        });

        rButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RadioButtonActivity.class));
        });

        spinner.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SpinnerActivity.class));
        });

        typeface.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TypeFaceActivity.class));
        });

    }
}