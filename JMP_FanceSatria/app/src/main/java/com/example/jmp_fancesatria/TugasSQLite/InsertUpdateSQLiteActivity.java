package com.example.jmp_fancesatria.TugasSQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jmp_fancesatria.DBHelper.Database;
import com.example.jmp_fancesatria.R;
import com.example.jmp_fancesatria.databinding.ActivityInsertUpdateSqliteBinding;

public class InsertUpdateSQLiteActivity extends AppCompatActivity {
    ActivityInsertUpdateSqliteBinding bind;
    int idbarang;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityInsertUpdateSqliteBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        db = new Database(InsertUpdateSQLiteActivity.this);

        cekIntent();

        bind.btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(InsertUpdateSQLiteActivity.this)
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
            bind.textView.setText("Ubah Data Item");
            bind.btnsimpan.setText("Simpan Perubahan");

            idbarang = getIntent().getIntExtra("id", 0);

            bind.etbarang.setText(getIntent().getStringExtra("namaBarang"));
            bind.etstok.setText(getIntent().getIntExtra("stok", 0)+"");
            return false;
        } else {
            bind.textView.setText("Tambah Data Barang");
            bind.btnsimpan.setText("Simpan");
            return true;
        }
    }

    public void insertOrUpdateData(){
        String barang = bind.etbarang.getText().toString();
        int stok = Integer.parseInt(bind.etstok.getText().toString());

        if(barang.isEmpty() || String.valueOf(stok).isEmpty()){
            Toast.makeText(this, "Pastikan data terisi semua", Toast.LENGTH_SHORT).show();
        } else {
            if(cekIntent()){
                String sql = "INSERT INTO tblbarang (nama, stok) VALUES('"+barang+"', "+stok+")";

                if(db.runSQL(sql)){
                    Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    backToMain();
                } else {
                    Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "insertOrUpdateData: "+db.runSQL(sql));
                }
            } else {

                String sql = "UPDATE tblbarang SET nama='"+barang+"', stok="+stok+" WHERE id = "+idbarang;
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
        startActivity(new Intent(InsertUpdateSQLiteActivity.this, SQLiteActivity.class));
        finish();
    }

}