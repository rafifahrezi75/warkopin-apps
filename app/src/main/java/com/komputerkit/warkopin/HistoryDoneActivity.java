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
import com.komputerkit.warkopin.response.PreferencesUtil;
import com.komputerkit.warkopin.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryDoneActivity extends AppCompatActivity {
    SearchView searchViewHistoryDone;
    RecyclerView recyclerView;
    HistoryDoneAdapter adapter;
    List<HistoryDoneModel.Result> results = new ArrayList<HistoryDoneModel.Result>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historydone);

        searchViewHistoryDone = findViewById(R.id.searchViewHistoryDone);
        searchViewHistoryDone.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        fetchDataHistoryDone();
    }
    public void load() {
        recyclerView = findViewById(R.id.recyclerviewhistorydone);
    }
    public void setUpRecyclerView(){
        adapter = new HistoryDoneAdapter(results, new HistoryDoneAdapter.OnAdaptorListener() {
            @Override
            public void onClick(HistoryDoneModel.Result result) {
                Toast.makeText(HistoryDoneActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void fetchDataHistoryDone(){

        String bearerToken = PreferencesUtil.getUserToken(this).toString();
        int iduser = Integer.parseInt(PreferencesUtil.getUserId(this).toString());

        Call<HistoryDoneModel> call = ApiClient.getApiService().getHistoryTuntas("Bearer " + bearerToken, iduser);
        call.enqueue(new Callback<HistoryDoneModel>() {
            @Override
            public void onResponse(Call<HistoryDoneModel> call, Response<HistoryDoneModel> response) {
                List<HistoryDoneModel.Result> results = response.body().getResult();
                adapter.setData(results);
            }

            @Override
            public void onFailure(Call<HistoryDoneModel> call, Throwable t) {
                Toast.makeText(HistoryDoneActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void dalamprosespage(View view) {
        Intent intent = new Intent(HistoryDoneActivity.this, HistoryProsesActivity.class);
        startActivity(intent);
        finish();
    }

    public void homepagefhistorydone(View view) {
        Intent intent = new Intent(HistoryDoneActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void cartpagefhistorydone(View view) {
        Intent intent = new Intent(HistoryDoneActivity.this, CartActivity.class);
        startActivity(intent);
        finish();
    }

    public void profilepagefhistorydone(View view) {
        Intent intent = new Intent(HistoryDoneActivity.this, ProfileActivity.class);
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
                searchViewHistoryDone.setQuery(thn+"-"+formattedBln+"-"+formattedTgl, false);
            }
        }, thn,bln,tgl);
        dtp.show();
    }
}
