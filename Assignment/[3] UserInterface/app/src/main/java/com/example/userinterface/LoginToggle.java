package com.example.userinterface;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

public class LoginToggle extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvShow, tvForgot;
    ImageButton btnShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_relative_toggle);
        TextView btnSignup = findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginToggle.this, SignupActivity.class);
            startActivity(intent);
        });

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnShow = findViewById(R.id.btn_show);
        tvForgot = findViewById(R.id.tv_forgot);

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
    }
}
