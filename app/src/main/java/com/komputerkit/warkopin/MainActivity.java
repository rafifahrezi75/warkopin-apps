package com.komputerkit.warkopin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.komputerkit.warkopin.response.PreferencesUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Cek apakah sudah ada data pengguna dalam SharedPreferences
                if (PreferencesUtil.getUserId(MainActivity.this) != null) {
                    startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                    finish();
                } else {
                    // Jika belum ada data pengguna, arahkan ke halaman OnBoarding
                    startActivity(new Intent(MainActivity.this, OnBoardingActivity.class));
                    finish();
                }
            }
        }, 1500);
    }

}