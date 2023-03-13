package com.example.dev4you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class FlightSplash extends AppCompatActivity {
    LottieAnimationView loginlottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_splash);

        loginlottie = findViewById(R.id.loginlottie);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loginlottie.animate().translationX(4000).setDuration(5000).setStartDelay(4000);
                startActivity(new Intent(FlightSplash.this,Splash.class));
                finish();
            }
        },3000);
    }
}