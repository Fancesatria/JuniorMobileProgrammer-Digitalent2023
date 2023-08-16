package com.ukom_dts.listbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ukom_dts.listbox.Data.AdapterMahasiswa;
import com.ukom_dts.listbox.Data.ModelMahasiswa;
import com.ukom_dts.listbox.Helper.Database;
import com.ukom_dts.listbox.Helper.SPHelper;
import com.ukom_dts.listbox.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding bind;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        db = new Database(LoginActivity.this);

        bind.email.setText(getIntent().getStringExtra("email"));
        bind.password.setText(getIntent().getStringExtra("password"));

        bind.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        bind.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bind.email.getText().toString().isEmpty() || bind.password.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Data harus diisi semua", Toast.LENGTH_SHORT).show();
                } else {
                    String sql = "SELECT * FROM tbluser WHERE email='"+bind.email.getText().toString()+"' AND password = '"+bind.password.getText().toString()+"'";
                    Cursor cursor = db.select(sql);

                    Log.d("sql", "selectData: "+cursor.getCount());


                    if(cursor.getCount() > 0){//if datanya ada/ga kosong
                        //Load datanya
                        while(cursor.moveToNext()){
                            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                            String nama = cursor.getString(cursor.getColumnIndexOrThrow("nama"));
                            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));

                            if(bind.email.getText().toString().equals(email) && bind.password.getText().toString().equals(password)){
                                SPHelper sp  = new SPHelper(LoginActivity.this);
                                sp.setIdPengguna(id);
                                sp.setUsername(nama);
                                sp.setEmail(email);
                                Toast.makeText(LoginActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Email atau pasword salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Akun tak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}