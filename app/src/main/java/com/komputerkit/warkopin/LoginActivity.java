package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {
    TextView tvBelumAkun, lupaPass;
    private EditText etEmail, etPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUserPengguna();
            }
        });

        load();
    }

    private void loginUserPengguna() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<LoginResponse> call = ApiClient.getApiService().loginUser(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    // Handle login success
                    LoginResponse loginResponse = response.body();

                    boolean success = loginResponse.isSuccess();
                    LoginResponse.User user = loginResponse.getUser();
                    String token = loginResponse.getToken();

                    // Simpan data pengguna ke dalam SharedPreferences
                    PreferencesUtil.saveUserData(LoginActivity.this, user.getId(), user.getName(), user.getEmail(), token, user.getRole());

                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();

                    // Menampilkan informasi menggunakan Toast
                    String toastMessage = "Selamat Datang " + user.getName();

                    Toast.makeText(LoginActivity.this, toastMessage, Toast.LENGTH_LONG).show();
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

                    Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Handle failure to connect to the server
                Toast.makeText(LoginActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void load(){
        tvBelumAkun = findViewById(R.id.tvBelumAkun);
        tvBelumAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lupaPass = findViewById(R.id.lupaPass);
        lupaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LupaPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
