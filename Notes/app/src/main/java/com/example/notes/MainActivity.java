package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.notes.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding bind;
    NotesAdapter adapter;
    List<String> data = new ArrayList<>();
    public final static String FILE_NAME = "Notes.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        bind.rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new NotesAdapter(MainActivity.this, data);
        bind.rv.setAdapter(adapter);
        addNote();

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    public void addNote(){
        bind.addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
            }
        });
    }

    public void createFile(){
        String isiNote = bind.description.getText().toString();
        String state = Environment.getExternalStorageState();
        Log.d("ExternalActivity", "Location : " + Environment.getExternalStorageDirectory());
        Log.d("ExternalActivity", isiNote);

        if(!Environment.MEDIA_MOUNTED.equals(state)){ //jika status penyimpanan tak ada dan terpasang dan dapat diakses untuk write/read
            return;
        }

        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);//mendapatkan direktori
        FileOutputStream outputStream = null;

        try{
            file.createNewFile(); //membuat file baru di direktori yang sudah ditentukan
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiNote.getBytes());
            outputStream.flush();
            outputStream.close();

            data.add(isiNote);
            bind.rv.getAdapter().notifyItemInserted(data.size());
            bind.rv.smoothScrollToPosition(data.size());

            Toast.makeText(this, "Data added succesfully", Toast.LENGTH_SHORT).show();
            bind.description.setText("");

        } catch(Exception e){
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

                    data.add(line);
                    bind.rv.getAdapter().notifyItemInserted(data.indexOf(line));
                    bind.rv.smoothScrollToPosition(data.indexOf(line));
                }
                br.close();

            } catch (Exception e){
                Log.d("Error", "readFile: "+e.getMessage());
            }

            //bind.tvData.setText(text.toString());
        }
    }

    public void deleteFile(){
        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);

        if(file.exists()){
            file.delete();

            readFile();

            //bind.tvData.setText("data");
        }
    }
}