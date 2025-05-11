package com.example.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginRelativeAssetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_relative_asset);
        TextView btnSignup = findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginRelativeAssetActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}
