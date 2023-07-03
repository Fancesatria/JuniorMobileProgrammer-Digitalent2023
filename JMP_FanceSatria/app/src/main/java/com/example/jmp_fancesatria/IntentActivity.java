package com.example.jmp_fancesatria;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.example.jmp_fancesatria.databinding.ActivityIntentBinding;

public class IntentActivity extends AppCompatActivity {

    ActivityIntentBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityIntentBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());


        bind.btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDial = new Intent();
                iDial.setAction(Intent.ACTION_DIAL);
                iDial.setData(Uri.parse("tel:"+ bind.etNo.getText()));
                startActivity(iDial);
            }
        });
        bind.btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iBrowser = new Intent();
                iBrowser.setAction(Intent.ACTION_VIEW);
                iBrowser.setData(Uri.parse("http://www.google.com/"));
                startActivity(Intent.createChooser(iBrowser, "Title"));
            }
        });
        bind.btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCam = new Intent();
                iCam.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivity(iCam);
            }
        });
        bind.btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iContact = new Intent();
                iContact.setAction(Intent.ACTION_VIEW);
                iContact.setData(Uri.parse("content://contacts/people/"));
                startActivity(iContact);
            }
        });
        bind.btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallerry = new Intent();
                iGallerry.setAction(Intent.ACTION_VIEW);
                iGallerry.setData(Uri.parse("content://media/external/images/media/"));
                startActivity(iGallerry);
            }
        });


    }
}