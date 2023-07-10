package com.example.jmp_fancesatria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.jmp_fancesatria.databinding.ActivityRegisterBinding;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        //Toolbar
        setSupportActionBar(bind.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Register");

        ActivityCompat.requestPermissions(RegisterActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        bind.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                finish();

                if(validasi()){
                    Toast.makeText(RegisterActivity.this, "Pastikan semua data terisi", Toast.LENGTH_SHORT).show();

                } else {
                    simpan();
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    i.putExtra("email", bind.username.getText().toString());
                    i.putExtra("password", bind.password.getText().toString());
                    startActivity(i);
                    finish();
                }


            }
        });

        bind.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();

            }
        });


    }

    public boolean validasi(){
        if(bind.username.getText().toString().isEmpty() || bind.email.getText().toString().isEmpty() ||
         bind.password.getText().toString().isEmpty() || bind.telp.getText().toString().isEmpty()){

            return true;
        } else {
            return false;
        }
    }

    public void simpan(){
        String isi = bind.username.getText().toString()+";"+bind.email.getText().toString()+";"+bind.password.getText().toString()+";"+bind.telp.getText().toString();
        String state = Environment.getExternalStorageState();

        if(!Environment.MEDIA_MOUNTED.equals(state)){
            return;
        }

        File file = new File(Environment.getExternalStorageDirectory(), bind.username.getText().toString());//nama file diambil dari username
        FileOutputStream fileOutputStream = null;

        try{
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(isi.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();

            onBackPressed();

        } catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(this, "Register berhasil", Toast.LENGTH_SHORT).show();
    }
}