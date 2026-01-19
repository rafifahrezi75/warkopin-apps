package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.komputerkit.warkopin.response.LoginResponse;
import com.komputerkit.warkopin.response.MessageResponse;
import com.komputerkit.warkopin.response.PreferencesUtil;
import com.komputerkit.warkopin.retrofit.ApiClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterOtpActivity extends AppCompatActivity {
    private EditText etEmailForRegister, etNameForRegister, etOtpForRegister, etPasswordForRegister;
    private Button btnRegisterForOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerotp);

        etEmailForRegister = findViewById(R.id.etEmailForRegister);
        etNameForRegister = findViewById(R.id.etNameForRegister);
        etOtpForRegister = findViewById(R.id.etOtpForRegister);
        etPasswordForRegister = findViewById(R.id.etPasswordForRegister);
        btnRegisterForOtp = findViewById(R.id.btnRegisterForOtp);

        btnRegisterForOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerOtp();
            }
        });
    }

    private void registerOtp() {
        String email = etEmailForRegister.getText().toString().trim();
        String name = etNameForRegister.getText().toString().trim();
        String otp = etOtpForRegister.getText().toString().trim();
        String password = etPasswordForRegister.getText().toString().trim();

        if (email.isEmpty() || name.isEmpty() || otp.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<MessageResponse> call = ApiClient.getApiService().registerVerifyOtp(email, name, otp, password);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    // Handle login success
                    MessageResponse messageResponse = response.body();

                    Intent intent = new Intent(RegisterOtpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                    Toast.makeText(RegisterOtpActivity.this, messageResponse.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Handle error response
                    String errorMessage = "";

                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            MessageResponse errorResponse = gson.fromJson(response.errorBody().string(), MessageResponse.class);
                            errorMessage += errorResponse.getMessage();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Toast.makeText(RegisterOtpActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                // Handle failure to connect to the server
                Toast.makeText(RegisterOtpActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
