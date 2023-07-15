package com.example.jmp_fancesatria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jmp_fancesatria.CatatanHarian.CatatanHarianActivity;
import com.example.jmp_fancesatria.InputNama.InputActivity;
import com.example.jmp_fancesatria.Kalkulator.CalculatorActivity;
import com.example.jmp_fancesatria.TugasSQLite.SQLiteActivity;
import com.example.jmp_fancesatria.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.email.setText("Your Email : "+getIntent().getStringExtra("email"));

        bind.dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });

        bind.intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, IntentActivity.class));
            }
        });

        bind.listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, ListviewActivity.class));
            }
        });

        bind.kalkulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, CalculatorActivity.class));
            }
        });

        bind.inputNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, InputActivity.class));
            }
        });

        bind.notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, CatatanHarianActivity.class));
            }
        });

        bind.sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, SQLiteActivity.class));
            }
        });
    }
}