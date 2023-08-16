package com.ukom_dts.listbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ukom_dts.listbox.Helper.Database;
import com.ukom_dts.listbox.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding bind;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        db = new Database(RegisterActivity.this);
        db.runSQL(Database.CREATE_TABLE_USER);

        bind.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        bind.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bind.username.getText().toString().isEmpty() || bind.email.getText().toString().isEmpty() || bind.password.getText().toString().isEmpty() || bind.confirmpassword.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Data harus diisi semua", Toast.LENGTH_SHORT).show();
                } else{
                    if(!bind.password.getText().toString().equals(bind.confirmpassword.getText().toString())){
                        Toast.makeText(RegisterActivity.this, "Password tidak sama", Toast.LENGTH_SHORT).show();
                    } else{
                        register();
                    }
                }
            }
        });

    }

    public void register(){
        String sql = "INSERT INTO tbluser (nama, email, password) VALUES('"+bind.username.getText().toString()+"', '"+bind.email.getText().toString()+"', '"+bind.password.getText().toString()+"')";

        if(db.runSQL(sql)){
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            i.putExtra("email", bind.username.getText().toString());
            i.putExtra("password", bind.password.getText().toString());
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
            Log.d("TAG", "insertOrUpdateData: "+db.runSQL(sql));
        }
    }

}