package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komputerkit.warkopin.dummy.MenuDashboardAdapter;
import com.komputerkit.warkopin.dummy.MenuDashboardModel;
import com.komputerkit.warkopin.menu.MenuAdapter;
import com.komputerkit.warkopin.menu.MenuModel;
import com.komputerkit.warkopin.response.PreferencesUtil;
import com.komputerkit.warkopin.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    TextView tvUsernameNamaUser;
    RecyclerView recyclerView;
    MenuAdapter adapter;
    List<MenuModel.Result> results = new ArrayList<MenuModel.Result>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvUsernameNamaUser = findViewById(R.id.tvUsernameNamaUser);

        // Mengambil nama pengguna dari SharedPreferences
        String username = PreferencesUtil.getUserName(this);

        tvUsernameNamaUser.setText("Welcome " + username + ",");

        load();
        setUpRecyclerView();
        fetchDataMenu();
    }

    public void load() {
        recyclerView = findViewById(R.id.recyclerviewmenudashboard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpRecyclerView(){
        adapter = new MenuAdapter(results, new MenuAdapter.OnAdaptorListener() {
            @Override
            public void onClick(MenuModel.Result result) {
                Toast.makeText(DashboardActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void fetchDataMenu(){

        String bearerToken = PreferencesUtil.getUserToken(this).toString();

        Call<MenuModel> call = ApiClient.getApiService().getMenu("Bearer " + bearerToken);
        call.enqueue(new Callback<MenuModel>() {
            @Override
            public void onResponse(Call<MenuModel> call, Response<MenuModel> response) {
                List<MenuModel.Result> results = response.body().getResult();
                adapter.setData(results);
            }

            @Override
            public void onFailure(Call<MenuModel> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void makananpage(View view) {
        String kategori = "Makanan";
        int id = 1;
        Intent intent = new Intent(DashboardActivity.this, KategoriActivity.class);
        intent.putExtra("Kategori", kategori);
        intent.putExtra("id", id);

        startActivity(intent);
        finish();
    }
    public void minumanpage(View view) {
        String kategori = "Minuman";
        int id = 2;
        Intent intent = new Intent(DashboardActivity.this, KategoriActivity.class);
        intent.putExtra("Kategori", kategori);
        intent.putExtra("id", id);

        startActivity(intent);
        finish();
    }
    public void kopipage(View view) {
        String kategori = "Kopi";
        int id = 3;
        Intent intent = new Intent(DashboardActivity.this, KategoriActivity.class);
        intent.putExtra("Kategori", kategori);
        intent.putExtra("id", id);

        startActivity(intent);
        finish();
    }
    public void camilanpage(View view) {
        String kategori = "Camilan";
        int id = 4;
        Intent intent = new Intent(DashboardActivity.this, KategoriActivity.class);
        intent.putExtra("Kategori", kategori);
        intent.putExtra("id", id);

        startActivity(intent);
        finish();
    }

    public void cartpage(View view) {
        Intent intent = new Intent(DashboardActivity.this, CartActivity.class);
        startActivity(intent);
        finish();
    }

    public void historypage(View view) {
        Intent intent = new Intent(DashboardActivity.this, HistoryDoneActivity.class);
        startActivity(intent);
        finish();
    }

    public void profilepage(View view) {
        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    public void allmenu(View view) {
        Intent intent = new Intent(DashboardActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
