package com.ukom_dts.listbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ukom_dts.listbox.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.etNama.setText("Nama : "+getIntent().getStringExtra("nama"));
        bind.etAsal.setText("Asal : "+getIntent().getStringExtra("asal"));
        bind.etAlamat.setText("Alamat : "+getIntent().getStringExtra("alamat"));
        bind.etPendidikanTerakhir.setText("Pendidikan Terakhir : "+getIntent().getStringExtra("pend_terakhir"));

        if(getIntent().getStringExtra("kelamin").equals("Perempuan")){
            bind.perempuan.setChecked(true);
            bind.laki.setChecked(false);
        } else {
            bind.laki.setChecked(true);
            bind.perempuan.setChecked(false);
        }

    }
}