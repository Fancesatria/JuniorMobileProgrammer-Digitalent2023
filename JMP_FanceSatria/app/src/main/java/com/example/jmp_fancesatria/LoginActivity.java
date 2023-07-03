package com.example.jmp_fancesatria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jmp_fancesatria.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        load();

        bind.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                finish();
                Intent i = new Intent(LoginActivity.this, MainActivity2.class);
                i.putExtra("email", bind.username.getText().toString());
                startActivity(i);

                finish();
            }
        });

        bind.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });
    }

    public void load(){
        bind.username.setText(getIntent().getStringExtra("email"));
        bind.password.setText(getIntent().getStringExtra("password"));
    }



}