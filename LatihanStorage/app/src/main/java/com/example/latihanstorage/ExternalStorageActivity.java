package com.example.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.example.latihanstorage.databinding.ActivityExternalStorageBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class ExternalStorageActivity extends AppCompatActivity {

    // PENYIMPANAN DI LUAR APLIKASI
    ActivityExternalStorageBinding bind;
    public final static String FILE_NAME = "LatihanStorageExternal.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityExternalStorageBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        click();

        ActivityCompat.requestPermissions(ExternalStorageActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    public void click(){
        bind.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
            }
        });

        bind.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFile();
            }
        });

        bind.read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });

        bind.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile();
            }
        });
    }


    public void createFile(){
        String isiFile = "coba isi data file text untuk external\n";
        String state = Environment.getExternalStorageState();
        Log.d("ExternalActivity", "Location : " + Environment.getExternalStorageDirectory());

        if(!Environment.MEDIA_MOUNTED.equals(state)){ //jika status penyimpanan tak ada dan terpasang dan dapat diakses untuk write/read
            return;
        }

        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);//mendapatkan direktori
        FileOutputStream outputStream = null;

        try{
            file.createNewFile(); //membuat file baru di direktori yang sudah ditentukan
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

            readFile();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateFile(){
        String isiFile = "Update isi data file external\n";
        String state = Environment.getExternalStorageState();

        if(!Environment.MEDIA_MOUNTED.equals(state)){
            return;
        }

        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
        FileOutputStream outputStream = null;

        try{
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

            readFile();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readFile(){
        File sdcard = Environment.getExternalStorageDirectory();//mendapatkan direktori penyimpanan file
        File file = new File(sdcard, FILE_NAME);// mencari file berdasarkan nama file yang sudah disimpan sebelumnya

        if(file.exists()){//jika file tersebut ada
            StringBuilder text = new StringBuilder();

            try{
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = br.readLine();

                while(line != null){
                    text.append(line+"\n");
                    line = br.readLine();
                }
                br.close();

            } catch (Exception e){
                Log.d("Error", "readFile: "+e.getMessage());
            }

            bind.tvData.setText(text.toString());
        }
    }

    public void deleteFile(){
        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);

        if(file.exists()){
            file.delete();

            readFile();

            bind.tvData.setText("data");
        }
    }
}