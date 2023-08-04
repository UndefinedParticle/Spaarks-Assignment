package com.example.spaarksassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Thread thread=new Thread(){
            public void run(){
                try {
                    sleep(3000);

                }catch (Exception e){
                    e.printStackTrace();

                }finally {

                    // Get the data from SharedPreferences
                    SharedPreferences preferences = getSharedPreferences("UserData", MODE_PRIVATE);
                    boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

                    // Check if the user is logged in
                    if (isLoggedIn) {
                        // User is logged in, start MainActivity
                        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                    } else {
                        // User is not logged in, start LogInActivity
                        Intent loginIntent = new Intent(SplashActivity.this, LogInActivity.class);
                        startActivity(loginIntent);
                    }

                    finish();
                }
            }

        };thread.start();
    }


}