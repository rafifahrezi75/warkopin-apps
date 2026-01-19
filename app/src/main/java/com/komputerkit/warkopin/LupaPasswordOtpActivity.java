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

public class LupaPasswordOtpActivity extends AppCompatActivity {
    EditText etEmailForLupaPass, etOtpForLupaPass, etPasswordForLupaPass;
    Button btnLupaPassForOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupapasswordotp);

        etEmailForLupaPass = findViewById(R.id.etEmailForLupaPass);
        etOtpForLupaPass = findViewById(R.id.etOtpForLupaPass);
        etPasswordForLupaPass = findViewById(R.id.etPasswordForLupaPass);
        btnLupaPassForOtp = findViewById(R.id.btnLupaPassForOtp);

        btnLupaPassForOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ubahPassword();
            }
        });
    }

    public void ubahPassword() {
        String email = etEmailForLupaPass.getText().toString().trim();
        String otp = etOtpForLupaPass.getText().toString().trim();
        String password = etPasswordForLupaPass.getText().toString().trim();

        if (email.isEmpty() || otp.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email, OTP dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<MessageResponse> call = ApiClient.getApiService().forgotPassword(email, otp, password);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();

                Intent intent = new Intent(LupaPasswordOtpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                Toast.makeText(LupaPasswordOtpActivity.this, messageResponse.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(LupaPasswordOtpActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
