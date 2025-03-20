package com.widget.widgetapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MsgViewActivity extends AppCompatActivity implements View.OnClickListener {
    Button alert;
    Button toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_view);

        alert = findViewById(R.id.alert);
        alert.setOnClickListener((View.OnClickListener) this);
        toast = findViewById(R.id.toast);
        toast.setOnClickListener((View.OnClickListener) this);

    }

    public void onClick(View view) {
        if (view == alert) {
            new AlertDialog.Builder(this)
                    .setTitle("Disini judul")
                    .setMessage("Disini isi pesan")
                    .setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }
        else {
            Toast
                    .makeText(this, "Toast clicked!", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}