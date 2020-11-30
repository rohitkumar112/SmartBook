package com.example.smartbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.smartbook.Activities.LoginActivity;

public class MainActivity extends AppCompatActivity {
//Note This Activity Is Just Splash Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setupLogin();
            }
        }, 1500);
    }

    private void setupLogin() {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
