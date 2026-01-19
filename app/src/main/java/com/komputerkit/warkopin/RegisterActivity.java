package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.komputerkit.warkopin.response.LoginResponse;
import com.komputerkit.warkopin.response.MessageResponse;
import com.komputerkit.warkopin.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextView tvLoginAkun;
    private Button sendOtpButton;
    private EditText etEmailSendOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sendOtpButton = findViewById(R.id.sendOtpButton);
        etEmailSendOtp = findViewById(R.id.etEmailSendOtp);

        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOtp();
            }
        });

        load();
    }

    public void load(){
        tvLoginAkun = findViewById(R.id.tvLoginAkun);
        tvLoginAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sendOtp() {
        String email = etEmailSendOtp.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<MessageResponse> call = ApiClient.getApiService().registerOtp(email);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();

                Intent intent = new Intent(RegisterActivity.this, RegisterOtpActivity.class);
                startActivity(intent);
                finish();

                Toast.makeText(RegisterActivity.this, messageResponse.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
