package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.komputerkit.warkopin.response.MessageResponse;
import com.komputerkit.warkopin.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LupaPasswordActivity extends AppCompatActivity {
    EditText etEmailForOtpForgotPass;
    Button btnOtpForForgotPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupapassword);

        etEmailForOtpForgotPass = findViewById(R.id.etEmailForOtpForgotPass);
        btnOtpForForgotPass = findViewById(R.id.btnOtpForForgotPass);

        btnOtpForForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOtpForgotPass();
            }
        });
    }

    private void sendOtpForgotPass() {
        String email = etEmailForOtpForgotPass.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<MessageResponse> call = ApiClient.getApiService().registerOtp(email);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();

                Intent intent = new Intent(LupaPasswordActivity.this, LupaPasswordOtpActivity.class);
                startActivity(intent);
                finish();

                Toast.makeText(LupaPasswordActivity.this, messageResponse.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(LupaPasswordActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
