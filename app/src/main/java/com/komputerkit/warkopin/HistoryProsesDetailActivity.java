package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komputerkit.warkopin.historydetail.HistoryDetailAdapter;
import com.komputerkit.warkopin.historydetail.HistoryDetailModel;
import com.komputerkit.warkopin.response.PreferencesUtil;
import com.komputerkit.warkopin.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryProsesDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HistoryDetailAdapter adapter;
    List<HistoryDetailModel.Result> results = new ArrayList<HistoryDetailModel.Result>();
    TextView tvKodeOrderHistoryProses, tvUserOrderHistoryProses, tvTotalHargaHistoryProses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyprosesdetail);

        String kodeOrder = getIntent().getStringExtra("KODE_ORDER");
        String userOrder = getIntent().getStringExtra("USER_ORDER");
        String totalOrder = getIntent().getStringExtra("TOTAL_ORDER");

        tvKodeOrderHistoryProses = findViewById(R.id.tvKodeOrderHistoryProses);
        tvUserOrderHistoryProses = findViewById(R.id.tvUserOrderHistoryProses);
        tvTotalHargaHistoryProses = findViewById(R.id.tvTotalHargaHistoryProses);

        tvKodeOrderHistoryProses.setText(kodeOrder);
        tvUserOrderHistoryProses.setText(userOrder);
        tvTotalHargaHistoryProses.setText(totalOrder);

        load();
        setUpRecyclerView();
        fetchDataDetailHistory();
    }
    public void load() {
        recyclerView = findViewById(R.id.recyclerviewhistoryprosesdetail);
    }
    public void setUpRecyclerView(){
        adapter = new HistoryDetailAdapter(results, new HistoryDetailAdapter.OnAdaptorListener() {
            @Override
            public void onClick(HistoryDetailModel.Result result) {
                Toast.makeText(HistoryProsesDetailActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void fetchDataDetailHistory(){

        String bearerToken = PreferencesUtil.getUserToken(this).toString();
        int idOrder = Integer.parseInt(getIntent().getStringExtra("ID_ORDER"));

        Call<HistoryDetailModel> call = ApiClient.getApiService().getHistoryDetail("Bearer " + bearerToken, idOrder);
        call.enqueue(new Callback<HistoryDetailModel>() {
            @Override
            public void onResponse(Call<HistoryDetailModel> call, Response<HistoryDetailModel> response) {
                List<HistoryDetailModel.Result> results = response.body().getResult();
                adapter.setData(results);
            }

            @Override
            public void onFailure(Call<HistoryDetailModel> call, Throwable t) {
                Toast.makeText(HistoryProsesDetailActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void backhistoryproses(View view) {
        Intent intent = new Intent(HistoryProsesDetailActivity.this, HistoryProsesActivity.class);
        startActivity(intent);
        finish();
    }
}
