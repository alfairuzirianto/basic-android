package com.widget.widgetapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputTypeActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText birthDateEditText;
    private EditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_type);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        addressEditText = findViewById(R.id.addressEditText);
        birthDateEditText = findViewById(R.id.birthDateEditText);
    }

    public void onSubmit(View view) {
        String name = nameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String birthDate = birthDateEditText.getText().toString();
        String address = addressEditText.getText().toString();

        if (name.isEmpty() || age.isEmpty() || birthDate.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Mohon isi semua kolom", Toast.LENGTH_SHORT).show();
        } else {
            String result = "Nama : " + name + "\nUmur : "+ age +
                    "\nTanggal lahir : " + birthDate + "\nAlamat : " + address;
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
    }
}