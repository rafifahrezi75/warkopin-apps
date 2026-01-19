package com.komputerkit.warkopin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komputerkit.warkopin.historydone.HistoryDoneAdapter;
import com.komputerkit.warkopin.historydone.HistoryDoneModel;
import com.komputerkit.warkopin.historyproses.HistoryProsesAdapter;
import com.komputerkit.warkopin.historyproses.HistoryProsesModel;
import com.komputerkit.warkopin.response.PreferencesUtil;
import com.komputerkit.warkopin.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryProsesActivity extends AppCompatActivity {
    SearchView searchViewHistoryProses;
    RecyclerView recyclerView;
    HistoryProsesAdapter adapter;
    List<HistoryProsesModel.Result> results = new ArrayList<HistoryProsesModel.Result>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyproses);

        searchViewHistoryProses = findViewById(R.id.searchViewHistoryProses);
        searchViewHistoryProses.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        fetchDataHistoryProses();
    }

    public void load() {
        recyclerView = findViewById(R.id.recyclerviewhistoryproses);
    }

    public void setUpRecyclerView(){
        adapter = new HistoryProsesAdapter(results, new HistoryProsesAdapter.OnAdaptorListener() {
            @Override
            public void onClick(HistoryProsesModel.Result result) {
                Toast.makeText(HistoryProsesActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void fetchDataHistoryProses(){

        String bearerToken = PreferencesUtil.getUserToken(this).toString();
        int iduser = Integer.parseInt(PreferencesUtil.getUserId(this).toString());

        Call<HistoryProsesModel> call = ApiClient.getApiService().getHistoryBelum("Bearer " + bearerToken, iduser);
        call.enqueue(new Callback<HistoryProsesModel>() {
            @Override
            public void onResponse(Call<HistoryProsesModel> call, Response<HistoryProsesModel> response) {
                List<HistoryProsesModel.Result> results = response.body().getResult();
                adapter.setData(results);
            }

            @Override
            public void onFailure(Call<HistoryProsesModel> call, Throwable t) {
                Toast.makeText(HistoryProsesActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void selesaipage(View view) {
        Intent intent = new Intent(HistoryProsesActivity.this, HistoryDoneActivity.class);
        startActivity(intent);
        finish();
    }
    public void homepagefhistoryproses(View view) {
        Intent intent = new Intent(HistoryProsesActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void cartpagefhistoryproses(View view) {
        Intent intent = new Intent(HistoryProsesActivity.this, CartActivity.class);
        startActivity(intent);
        finish();
    }

    public void profilepagefhistoryproses(View view) {
        Intent intent = new Intent(HistoryProsesActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    public void filter(View view) {
        Calendar calendar = Calendar.getInstance();
        int tgl = calendar.get(Calendar.DAY_OF_MONTH);
        int bln = calendar.get(Calendar.MONTH);
        int thn = calendar.get(Calendar.YEAR);

        DatePickerDialog dtp = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int thn, int bln, int tgl) {
                String formattedBln = String.format(Locale.US, "%02d", bln + 1);
                String formattedTgl = String.format(Locale.US, "%02d", tgl);
                searchViewHistoryProses.setQuery(thn+"-"+formattedBln+"-"+formattedTgl, false);
            }
        }, thn,bln,tgl);
        dtp.show();
    }
}
