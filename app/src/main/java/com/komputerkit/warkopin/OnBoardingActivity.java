package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OnBoardingActivity extends AppCompatActivity {
    private Button button, btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        button = findViewById(R.id.btnLoginOnBoarding);
        btnsignup = findViewById(R.id.btnSignUpOnBoarding);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
