package com.example.jmp_fancesatria.InputNama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jmp_fancesatria.databinding.ActivityInputResultBinding;

public class InputResultActivity extends AppCompatActivity {

    ActivityInputResultBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityInputResultBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        Intent intent = getIntent();
        Bundle myBundle = intent.getBundleExtra("bundle");
        bind.name.setText("Your name :"+myBundle.getString("name"));
        bind.email.setText("Your email :"+myBundle.getString("email"));

        bind.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InputResultActivity.this, InputActivity.class));
            }
        });
    }
}