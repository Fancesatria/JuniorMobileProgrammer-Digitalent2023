package com.example.jmp_fancesatria.CatatanHarian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.jmp_fancesatria.Global;
import com.example.jmp_fancesatria.LoginActivity;
import com.example.jmp_fancesatria.R;
import com.example.jmp_fancesatria.databinding.ActivityCatatanHarianBinding;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CatatanHarianActivity extends AppCompatActivity {

    ActivityCatatanHarianBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCatatanHarianBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        setSupportActionBar(bind.toolbar);

        ActivityCompat.requestPermissions(CatatanHarianActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        bind.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> data = (Map<String, Object>) parent.getAdapter().getItem(position);

                delItem(data.get("name").toString());

                return true;
            }
        });

        bind.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CatatanHarianActivity.this, InsertAndViewActivity.class);

                Map<String, Object> data = (Map<String, Object>) parent.getAdapter().getItem(position);
                i.putExtra("filename", data.get("name").toString());
                Toast.makeText(CatatanHarianActivity.this,data.get("name").toString()+"" , Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_add){
            startActivity(new Intent(CatatanHarianActivity.this, InsertAndViewActivity.class));
        }
        if(item.getItemId() == R.id.menu_logout){
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void logout(){
        new AlertDialog.Builder(this).setTitle("Konfirmasi").setMessage("Yakin ingin keluar")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapusFile();
                        startActivity(new Intent(CatatanHarianActivity.this, LoginActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    public void delItem(final String filename){
        new AlertDialog.Builder(this).setTitle("Konfirmasi").setMessage("Yakin ingin keluar")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapusFile(filename);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListCatatan();
    }


    public void hapusFile(){
        File file = new File(Environment.getExternalStorageDirectory(), Global.FILE_NAME);

        if(file.exists()){
            file.delete();
        }

    }

    public void hapusFile(String filename){
        File file = new File(getFilesDir(), filename+Global.DIREKTORI_FILE);

        if(file.exists()){
            file.delete();
        }

    }

    public void getListCatatan(){
        String path = getFilesDir().toString() + Global.DIREKTORI_FILE;
        File directory = new File(path);
        if (directory.exists()) {
            File[] files = directory.listFiles();
            String[] filenames = new String[files.length];
            String[] dateCreated = new String[files.length];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM YYYY HH:mm:ss");
            ArrayList<Map<String, Object>> itemDataList = new ArrayList<Map<String, Object>>();

            for (int i = 0; i < files.length; i++) {
                filenames[i] = files[i].getName();
                Date lastModDate = new Date(files[i].lastModified());
                dateCreated[i] = simpleDateFormat.format(lastModDate);
                Map<String, Object> listItemMap = new HashMap<>();
                listItemMap.put("name", filenames[i]);
                listItemMap.put("date", dateCreated[i]);
                itemDataList.add(listItemMap);
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                    itemDataList, android.R.layout.simple_list_item_2,
                    new String[]{"name", "date"}, new int[]{android.R.id.text1, android.R.id.text2});
            bind.listView.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();

        }
    }
}