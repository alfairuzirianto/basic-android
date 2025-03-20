package com.widget.widgetapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TypeFaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_face);

        TextView textView = findViewById(R.id.custom);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "PressStart2P.ttf");
        textView.setTypeface(typeface);
    }
}