package com.example.jmp_fancesatria.CatatanHarian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jmp_fancesatria.Global;
import com.example.jmp_fancesatria.R;
import com.example.jmp_fancesatria.databinding.ActivityInsertAndViewBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

public class InsertAndViewActivity extends AppCompatActivity {

    ActivityInsertAndViewBinding bind;
    String filename = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityInsertAndViewBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            filename = extras.getString("filename");
            bind.editFilename.setText(filename);
            bind.btnSimpan.setText("Simpan Perubahan");
        } else {
            bind.btnSimpan.setText("Tambah Catatan");
        }

        bind.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bind.editFilename.getText().toString().isEmpty() || bind.editContent.getText().toString().isEmpty()){
                    Toast.makeText(InsertAndViewActivity.this, "Data tak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    confirmSave();
                }
            }
        });

        readFile();

    }

    public void readFile(){
        String path = getFilesDir().toString()+Global.DIREKTORI_FILE;
        File file = new File(path, bind.editFilename.getText().toString());

        if(file.exists()){
            StringBuilder text = new StringBuilder();
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();

            } catch (Exception e){
                e.printStackTrace();
            }
            bind.editContent.setText(text.toString());
        }
    }


    public void createAndUpdate(){
        String path = getFilesDir().toString()+ Global.DIREKTORI_FILE;

        File parent = new File(path);

        if(parent.exists()){
            File file = new File(path, bind.editFilename.getText().toString());
            FileOutputStream fileOutputStream = null;

            try{
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.append(bind.editContent.getText());
                outputStreamWriter.flush();
                outputStreamWriter.close();
                fileOutputStream.flush();
                fileOutputStream.close();

            } catch(Exception e){
                e.printStackTrace();
            }

        } else {
            parent.mkdir();
            File file = new File(path, bind.editFilename.getText().toString());
            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file, false);
                outputStream.write(bind.editContent.getText().toString().getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        finish();
    }

    public void confirmSave(){
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createAndUpdate();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

}