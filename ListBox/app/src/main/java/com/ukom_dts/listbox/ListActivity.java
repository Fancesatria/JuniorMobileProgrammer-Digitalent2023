package com.ukom_dts.listbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.ukom_dts.listbox.Data.AdapterMahasiswa;
import com.ukom_dts.listbox.Data.ModelMahasiswa;
import com.ukom_dts.listbox.Helper.Database;
import com.ukom_dts.listbox.databinding.ActivityListBinding;
import com.ukom_dts.listbox.databinding.DialogOptionBinding;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    Database db;
    List<ModelMahasiswa> data = new ArrayList<>();
    AdapterMahasiswa adapter;
    ActivityListBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        db = new Database(ListActivity.this);
        selectData();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        selectData();
    }

    public void optionDialog(int id, String nama, String kelamin, String asal, String alamat, String pend_terakhir){
        DialogOptionBinding binder = DialogOptionBinding.inflate(LayoutInflater.from(ListActivity.this));
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ListActivity.this);
        alertBuilder.setView(binder.getRoot());
        AlertDialog dialog = alertBuilder.create();
        binder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binder.show.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ListActivity.this, DetailActivity.class);
                        i.putExtra("id", id);
                        i.putExtra("nama", nama);
                        i.putExtra("kelamin", kelamin);
                        i.putExtra("asal", asal);
                        i.putExtra("alamat", alamat);
                        i.putExtra("pend_terakhir", pend_terakhir);
                        startActivity(i);
                    }
                });

                binder.update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ListActivity.this, InputUpdateActivity.class);
                        i.putExtra("id", id);
                        i.putExtra("nama", nama);
                        i.putExtra("kelamin", kelamin);
                        i.putExtra("asal", asal);
                        i.putExtra("alamat", alamat);
                        i.putExtra("pend_terakhie", pend_terakhir);
                        i.putExtra("editFlag", 1);
                        startActivity(i);
                    }
                });

                binder.del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(ListActivity.this)
                                .setTitle("Konfirmasi")
                                .setMessage("yakin ingin menghapus item ini?")
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String sql = "DELETE FROM tblmahasiswa WHERE id="+id;

                                        if(db.runSQL(sql)){
                                            Toast.makeText(ListActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(ListActivity.this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                                        }
                                        selectData();
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }
                });
            }


        });
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        selectData();
    }

    public void selectData(){
        String sql = "SELECT * FROM tblmahasiswa";
        Cursor cursor = db.select(sql);

        Log.d("sql", "selectData: "+cursor.getCount());

        data.clear();//biar data yang ada sebelumnya ga numpuk sama data yg barusan diload
        if(cursor.getCount() > 0){//if datanya ada/ga kosong
            //Load datanya
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nama = cursor.getString(cursor.getColumnIndexOrThrow("nama"));
                String kelamin = cursor.getString(cursor.getColumnIndexOrThrow("kelamin"));
                String asal = cursor.getString(cursor.getColumnIndexOrThrow("asal"));
                String alamat = cursor.getString(cursor.getColumnIndexOrThrow("alamat"));
                String pend_terakhir = cursor.getString(cursor.getColumnIndexOrThrow("pend_terakhir"));

                //masukan ke model
                data.add(new ModelMahasiswa(id, nama, kelamin, asal, alamat, pend_terakhir));
            }

            //Load to adapter
            adapter = new AdapterMahasiswa(ListActivity.this, data);
            bind.lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}