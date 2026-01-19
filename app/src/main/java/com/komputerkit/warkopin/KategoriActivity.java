package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komputerkit.warkopin.dummy.KategoriMenuAdapter;
import com.komputerkit.warkopin.dummy.KategoriMenuModel;
import com.komputerkit.warkopin.menu.MenuAdapter;
import com.komputerkit.warkopin.menu.MenuModel;
import com.komputerkit.warkopin.response.PreferencesUtil;
import com.komputerkit.warkopin.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView tvKategoriNamaKategoriPage;
    MenuAdapter adapter;
    List<MenuModel.Result> results = new ArrayList<MenuModel.Result>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        String Kategori = getIntent().getStringExtra("Kategori");

        tvKategoriNamaKategoriPage = findViewById(R.id.tvKategoriNamaKategoriPage);

        tvKategoriNamaKategoriPage.setText(Kategori);

        load();
        setUpRecyclerView();
        fetchDataMenu();
    }

    public void load() {
        recyclerView = findViewById(R.id.recyclerviewkategori);
    }
    public void setUpRecyclerView(){
        adapter = new MenuAdapter(results, new MenuAdapter.OnAdaptorListener() {
            @Override
            public void onClick(MenuModel.Result result) {
                Toast.makeText(KategoriActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void fetchDataMenu(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String bearerToken = PreferencesUtil.getUserToken(this).toString();

        Call<MenuModel> call = ApiClient.getApiService().getMenuByKategori("Bearer " + bearerToken, id);
        call.enqueue(new Callback<MenuModel>() {
            @Override
            public void onResponse(Call<MenuModel> call, Response<MenuModel> response) {
                List<MenuModel.Result> results = response.body().getResult();
                adapter.setData(results);
            }

            @Override
            public void onFailure(Call<MenuModel> call, Throwable t) {
                Toast.makeText(KategoriActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void backdashboard(View view) {
        Intent intent = new Intent(KategoriActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}
