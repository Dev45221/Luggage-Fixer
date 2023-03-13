package com.example.dev4you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Splash extends AppCompatActivity {
    TextView splashtv;
    LottieAnimationView splashlottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashtv = findViewById(R.id.splashtv);
        splashlottie = findViewById(R.id.lottie);

        splashtv.animate().translationY(-800).setDuration(3000).setStartDelay(0);
        splashlottie.animate().translationX(5000).setDuration(3000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this,Register.class));
                finish();
            }
        },5000);
    }
}