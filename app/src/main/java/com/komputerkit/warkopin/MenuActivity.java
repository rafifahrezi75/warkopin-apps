package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komputerkit.warkopin.menu.MenuAdapter;
import com.komputerkit.warkopin.menu.MenuModel;
import com.komputerkit.warkopin.response.PreferencesUtil;
import com.komputerkit.warkopin.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MenuAdapter adapter;
    List<MenuModel.Result> results = new ArrayList<MenuModel.Result>();
    SearchView searchViewMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        searchViewMenu = findViewById(R.id.searchViewMenu);
        searchViewMenu.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        load();
        setUpRecyclerView();
        fetchDataMenu();
    }

    public void load() {
        recyclerView = findViewById(R.id.recyclerviewallmenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpRecyclerView(){
        adapter = new MenuAdapter(results, new MenuAdapter.OnAdaptorListener() {
            @Override
            public void onClick(MenuModel.Result result) {
                Toast.makeText(MenuActivity.this, "Success", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MenuActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void backhome(View view) {
        Intent intent = new Intent(MenuActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}
