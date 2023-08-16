package com.ukom_dts.listbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ukom_dts.listbox.Helper.Database;
import com.ukom_dts.listbox.databinding.ActivityInputUpdateBinding;

public class InputUpdateActivity extends AppCompatActivity {
    int id;
    Database db;
    ActivityInputUpdateBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityInputUpdateBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        db = new Database(InputUpdateActivity.this);
        db.runSQL(Database.CREATE_TABLE_MAHASISWA);

        cekIntent();

        bind.btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(InputUpdateActivity.this)
                        .setTitle("Konfirmasi")
                        .setMessage("Anda yakin untuk menyimpan data ini?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                insertOrUpdateData();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }

    public boolean cekIntent(){
        if(getIntent().getIntExtra("editFlag", 0) == 1){
            bind.textView.setText("Ubah Data Mahasiswa");
            bind.btnsimpan.setText("Simpan Perubahan");

            id = getIntent().getIntExtra("id", 0);

            bind.etNama.setText(getIntent().getStringExtra("nama"));
            bind.etAsal.setText(getIntent().getStringExtra("asal"));
            bind.etAlamat.setText(getIntent().getStringExtra("alamat"));
            bind.etPendidikanTerakhir.setText(getIntent().getStringExtra("pend_terakhir"));

            if(getIntent().getStringExtra("kelamin").equals("Peremnpuan")){
                bind.perempuan.setChecked(true);
                bind.laki.setChecked(false);
            } else {
                bind.laki.setChecked(true);
                bind.perempuan.setChecked(false);
            }
            return false;
        } else {
            bind.textView.setText("Input Data Mahasiswa");
            bind.btnsimpan.setText("Simpan");
            return true;
        }
    }

    public void insertOrUpdateData(){
        String nama = bind.etNama.getText().toString();
        String alamat = bind.etAlamat.getText().toString();
        String asal = bind.etAsal.getText().toString();
        String pend_terakhir = bind.etPendidikanTerakhir.getText().toString();
        String kelamin;

        if(bind.laki.isChecked()){
            kelamin = "Laki-laki";

        } else {
            kelamin = "Perempuan";
        }

        if(nama.isEmpty() || asal.isEmpty()|| alamat.isEmpty()|| kelamin.isEmpty()){
            Toast.makeText(this, "Pastikan data terisi semua", Toast.LENGTH_SHORT).show();
        } else {
            if(cekIntent()){
                String sql = "INSERT INTO tblmahasiswa (nama, kelamin, asal, alamat, pend_terakhir) VALUES('"+nama+"', '"+kelamin+"', '"+asal+"', '"+alamat+"','"+pend_terakhir+"')";

                if(db.runSQL(sql)){
                    Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    backToMain();
                } else {
                    Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "insertOrUpdateData: "+db.runSQL(sql));
                }
            } else {

                String sql = "UPDATE tblmahasiswa SET nama='"+nama+"', kelamin='"+kelamin+"', asal='"+asal+"', alamat='"+alamat+"', pend_terakhir='"+pend_terakhir+"' WHERE id = "+id;
                if(db.runSQL(sql)){
                    Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                    backToMain();

                } else {
                    Toast.makeText(this, "Data gagal diubah", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void backToMain(){
        startActivity(new Intent(InputUpdateActivity.this, ListActivity.class));
        finish();
    }

}