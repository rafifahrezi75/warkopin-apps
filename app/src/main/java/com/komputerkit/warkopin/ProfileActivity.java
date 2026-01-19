package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.komputerkit.warkopin.response.PreferencesUtil;
import com.komputerkit.warkopin.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    TextView tvUsernameProfilePage, tvUserEmailProfilePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsernameProfilePage = findViewById(R.id.tvUsernameProfilePage);
        tvUserEmailProfilePage = findViewById(R.id.tvUserEmailProfilePage);

        String username = PreferencesUtil.getUserName(this);
        String email = PreferencesUtil.getUserEmail(this);

        tvUsernameProfilePage.setText(username);
        tvUserEmailProfilePage.setText(email);
    }

    public void homepagefprofile(View view) {
        Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void cartpagefprofile(View view) {
        Intent intent = new Intent(ProfileActivity.this, CartActivity.class);
        startActivity(intent);
        finish();
    }

    public void historypagefprofile(View view) {
        Intent intent = new Intent(ProfileActivity.this, HistoryDoneActivity.class);
        startActivity(intent);
        finish();
    }

    public void logoutUser(View view) {
        String bearerToken = PreferencesUtil.getUserToken(this).toString();

        Call<Void> call = ApiClient.getApiService().logout("Bearer " + bearerToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle logout success
                    Toast.makeText(ProfileActivity.this, "Logout successful", Toast.LENGTH_SHORT).show();

                    // Hapus shared preferences setelah logout berhasil
                    PreferencesUtil.clearUserData(ProfileActivity.this);

                    // Redirect ke halaman login
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Handle logout failure
                    Toast.makeText(ProfileActivity.this, "Logout failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
                Toast.makeText(ProfileActivity.this, "Logout failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
