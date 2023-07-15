package com.example.jmp_fancesatria.TugasSQLite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jmp_fancesatria.DBHelper.Database;
import com.example.jmp_fancesatria.MainActivity2;
import com.example.jmp_fancesatria.R;
import com.example.jmp_fancesatria.databinding.ActivitySqliteBinding;

import java.util.ArrayList;
import java.util.List;

public class SQLiteActivity extends AppCompatActivity {
    ActivitySqliteBinding bind;
    Database db;
    List<ModelBarang> dataBarang = new ArrayList<>();
    AdapterBarang adapterBarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivitySqliteBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        setSupportActionBar(bind.toolbar);

        db = new Database(SQLiteActivity.this);
        selectData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_add){
            startActivity(new Intent(SQLiteActivity.this, InsertUpdateSQLiteActivity.class));
        }

        if(item.getItemId() == R.id.menu_logout){
            startActivity(new Intent(SQLiteActivity.this, MainActivity2.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectData(){
        String sql = "SELECT * FROM tblbarang";
        Cursor cursor = db.select(sql);

        Log.d("sql", "selectData: "+cursor.getCount());

        dataBarang.clear();//biar data yang ada sebelumnya ga numpuk sama data yg barusan diload
        if(cursor.getCount() > 0){//if datanya ada/ga kosong
            //Load datanya
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String barang = cursor.getString(cursor.getColumnIndexOrThrow("nama"));
                int stok = cursor.getInt(cursor.getColumnIndexOrThrow("stok"));

                //masukan ke model
                dataBarang.add(new ModelBarang(id, stok, barang));
            }

            //Load to adapter
            adapterBarang = new AdapterBarang(SQLiteActivity.this, dataBarang);
            bind.lv.setAdapter(adapterBarang);
            adapterBarang.notifyDataSetChanged();
        }
    }

    public void deleteItemAlert(int id){
        new AlertDialog.Builder(SQLiteActivity.this)
                .setTitle("Konfirmasi")
                .setMessage("yakin ingin menghapus item ini?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "DELETE FROM tblbarang WHERE id="+id;

                        if(db.runSQL(sql)){
                            Toast.makeText(SQLiteActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                            selectData();
                        } else {
                            Toast.makeText(SQLiteActivity.this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void editItemAlert(int id, int stok, String namaBarang){
        Intent i = new Intent(SQLiteActivity.this, InsertUpdateSQLiteActivity.class);
        i.putExtra("id", id);
        i.putExtra("stok", stok);
        i.putExtra("namaBarang", namaBarang);
        i.putExtra("editFlag", 1);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectData();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        selectData();
    }
}