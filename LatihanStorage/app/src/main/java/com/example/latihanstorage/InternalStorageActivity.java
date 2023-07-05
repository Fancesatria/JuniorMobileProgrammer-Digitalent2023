package com.example.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.latihanstorage.databinding.ActivityInternalStorageBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class InternalStorageActivity extends AppCompatActivity {

    //PENYIMPANAN DI DALAM APLIKASI
    ActivityInternalStorageBinding bind;
    public static final String FILE_NAME = "LatihanStorageInternal.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityInternalStorageBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        click();
    }

    public void createFile(){
        String isiFile = "Coba isi data file text untuk internal\n";
        File file = new File(getFilesDir(), FILE_NAME); //mendapat path absolut ke direktori file di internal storage

        Log.d("InternalActivity", "Location : " + getFilesDir());
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();//membuat file baru
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes()); //memasukkan data ke storage
            outputStream.flush(); //merefresh
            outputStream.close(); //menutup pembuatan storage

            readFile();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void readFile(){
        File sdcard = getFilesDir(); //mendapat path dr storage
        File file = new File(sdcard, FILE_NAME);//mencari file yang dituju

        if(file.exists()){ //program akan dijalanakn jika file ada
            StringBuilder text = new StringBuilder();

            try{
                //proses pembacaan data
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = br.readLine();
                while(line != null){
                    text.append(line+"\n");
                    line = br.readLine();
                }

                br.close();

            } catch(Exception e){
                Log.d("Error", "readFile : "+e.getMessage());
            }

            bind.tvData.setText(text.toString());
        }
    }



    public void updateFile(){
        String isiFile = "Update isi data file\n";
        File file = new File(getFilesDir(), FILE_NAME);

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

    public void deleteData(){
        File file = new File(getFilesDir(), FILE_NAME);//mencari file dengan nama variabel FILE_NAME

        FileOutputStream outputStream = null;
        if(file.exists()){//program haya dapat berjalan jika file memang benar-benar ada
            file.delete();

            bind.tvData.setText("data");
        }


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

        bind.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

        bind.read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });
    }

}