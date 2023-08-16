package com.ukom_dts.listbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.ukom_dts.listbox.Helper.SPHelper;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIMEOUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(cekLogin()){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }

            }
        }, SPLASH_SCREEN_TIMEOUT);

        Animation fadeOut=new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(5000);
        fadeOut.setDuration(1800);
        ImageView image=findViewById(R.id.imageView2);

        image.setAnimation(fadeOut);
    }


    public boolean cekLogin(){
        SPHelper sp = new SPHelper(SplashActivity.this);
        if(sp.getUsername().isEmpty()){
            return false;
        } else {
            return true;
        }
    }
}