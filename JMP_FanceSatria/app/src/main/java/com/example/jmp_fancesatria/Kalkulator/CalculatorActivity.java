package com.example.jmp_fancesatria.Kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.jmp_fancesatria.databinding.ActivityCalculatorBinding;

public class CalculatorActivity extends AppCompatActivity {

    ActivityCalculatorBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCalculatorBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        EditText a = bind.bil1;
        EditText b = bind.bil2;

        bind.tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double b1 = Double.parseDouble(a.getText().toString());
                double b2 = Double.parseDouble(b.getText().toString());
                double hasil = b1+b2;
                bind.hasil.setText(String.valueOf(hasil));
            }
        });

        bind.kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double b1 = Double.parseDouble(a.getText().toString());
                double b2 = Double.parseDouble(b.getText().toString());
                double hasil = b1-b2;
                bind.hasil.setText(String.valueOf(hasil));
            }
        });

        bind.kali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double b1 = Double.parseDouble(a.getText().toString());
                double b2 = Double.parseDouble(b.getText().toString());
                double hasil = b1*b2;
                bind.hasil.setText(String.valueOf(hasil));
            }
        });

        bind.bagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double b1 = Double.parseDouble(a.getText().toString());
                double b2 = Double.parseDouble(b.getText().toString());
                double hasil = b1/b2;
                bind.hasil.setText(String.valueOf(hasil));
            }
        });

        bind.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bind.hasil.setText("0");
            }
        });
    }
}