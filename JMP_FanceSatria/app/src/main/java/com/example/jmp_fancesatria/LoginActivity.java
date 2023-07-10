package com.example.jmp_fancesatria;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jmp_fancesatria.databinding.ActivityLoginBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        load();

        ActivityCompat.requestPermissions(LoginActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        bind.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validasi()){
                    Toast.makeText(LoginActivity.this, "Pastikan data terisi semua", Toast.LENGTH_SHORT).show();
                } else {
//                    login();
                    Intent i = new Intent(LoginActivity.this, MainActivity2.class);
                    i.putExtra("email", bind.username.getText().toString());
                    startActivity(i);

                    simpanFileLogin();
                }

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

    public void login(){
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, bind.username.getText().toString());

        if(file.exists()){
            StringBuilder text = new StringBuilder();

            try{
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = br.readLine();

                while(line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();

            } catch(Exception e){
                Log.d("Error", "login: "+e.getMessage());
            }

            String data = text.toString();
            String[] dataUser = data.split(";");

            if(dataUser[2].equals(bind.password.getText().toString())){
                Intent i = new Intent(LoginActivity.this, MainActivity2.class);
                i.putExtra("email", bind.username.getText().toString());
                startActivity(i);

                simpanFileLogin();
            } else {
                Toast.makeText(this, "Password salah", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, "user tak ditemukan", Toast.LENGTH_SHORT).show();
        }


    }

    public void simpanFileLogin(){
        String isi = bind.username.getText().toString()+";"+bind.password.getText().toString();
        String state = Environment.getExternalStorageState();

        if(!Environment.MEDIA_MOUNTED.equals(state)){
            return;
        }

        File file = new File(Environment.getExternalStorageDirectory(), Global.FILE_NAME);
        FileOutputStream fileOutputStream = null;

        try{
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(isi.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();


        } catch (Exception e){
            Log.d("Error", "simpanFileLogin: "+e .getMessage());
        }

        Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }


    public boolean validasi(){
        if(bind.username.getText().toString().isEmpty() || bind.password.getText().toString().isEmpty()){
            return true;
        } else {
            return false;
        }
    }


}