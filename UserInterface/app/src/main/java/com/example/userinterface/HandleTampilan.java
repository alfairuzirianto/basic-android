package com.example.userinterface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HandleTampilan extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView btnSignup;
    private ImageButton btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLogin();
    }

    // Layout login
    @SuppressLint("MissingInflateId")
    private void showLogin() {
        setContentView(R.layout.login_relative_toggle);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);
        btnShow = findViewById(R.id.btn_show);

        // Tombol login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Jika username dan password "admin", maka ke menu utama
                if("admin".equals(etPassword.getText().toString()) && "admin".equals(etUsername.getText().toString())) {
                    showMenuUtama();
                } else {
                    Toast.makeText(HandleTampilan.this, "Periksa kembali username atau password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Icon show password
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasWindowFocus()) {
                    btnShow.setVisibility(View.VISIBLE);
                    btnShow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(btnShow.getTag().toString().equals("on")) {
                                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                btnShow.setBackgroundResource(R.drawable.ic_show_off);
                                btnShow.setTag("off");
                            } else {
                                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                btnShow.setBackgroundResource(R.drawable.ic_show_on);
                                btnShow.setTag("on");
                            }
                        }
                    });
                }
            }
        });

        // Tombol sign up
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegister();
            }
        });
    }

    // Layout registrasi
    private void showRegister() {
        setContentView(R.layout.signup_user);
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(v -> {
            showLogin();
            Toast.makeText(this, "User berhasil terdaftar", Toast.LENGTH_SHORT).show();
        });
    }

    // Layout utama
    private void showMenuUtama() {
        setContentView(R.layout.activity_menu_utama);
    }
}
