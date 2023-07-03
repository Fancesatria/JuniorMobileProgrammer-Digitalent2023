package com.example.jmp_fancesatria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jmp_fancesatria.databinding.ActivityInputBinding;

public class InputActivity extends AppCompatActivity {

    ActivityInputBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());


        bind.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InputActivity.this, InputResultActivity.class);
                Bundle myBundle = new Bundle();
                myBundle.putString("name", bind.name.getText().toString());
                myBundle.putString("email", bind.email.getText().toString());

                i.putExtra("bundle", myBundle);
                startActivity(i);
            }
        });
    }
}