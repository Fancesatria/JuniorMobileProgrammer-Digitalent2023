package com.example.jmp_fancesatria.TugasAPI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jmp_fancesatria.MainActivity2;
import com.example.jmp_fancesatria.R;
import com.example.jmp_fancesatria.TugasAPI.API.deleteItem;
import com.example.jmp_fancesatria.TugasAPI.API.getAllItem;
import com.example.jmp_fancesatria.TugasSQLite.InsertUpdateSQLiteActivity;
import com.example.jmp_fancesatria.TugasSQLite.SQLiteActivity;
import com.example.jmp_fancesatria.databinding.ActivityApiactivityBinding;

public class APIActivityActivity extends AppCompatActivity {
    ActivityApiactivityBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityApiactivityBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        setSupportActionBar(bind.toolbar);

        loadItem();
    }

    public void loadItem(){
        getAllItem getAll = new getAllItem(this, bind);
        getAll.execute();
    }

    public void deleteItemAlert(int id){
        new AlertDialog.Builder(APIActivityActivity.this)
                .setTitle("Konfirmasi")
                .setMessage("yakin ingin menghapus item ini?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem delete = new deleteItem(bind, APIActivityActivity.this);
                        delete.execute(String.valueOf(id));
                        loadItem();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void editItemAlert(int id, int price, String brand, String name){
        Intent i = new Intent(APIActivityActivity.this, InsertAndUpdateAPIActivity.class);
        i.putExtra("id", id);
        i.putExtra("price", price);
        i.putExtra("brand", brand);
        i.putExtra("name", name);
        i.putExtra("editFlag", 1);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_add){
            startActivity(new Intent(APIActivityActivity.this, InsertAndUpdateAPIActivity.class));
        }

        if(item.getItemId() == R.id.menu_logout){
            startActivity(new Intent(APIActivityActivity.this, MainActivity2.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadItem();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItem();
    }
}