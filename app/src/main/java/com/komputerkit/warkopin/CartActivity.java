package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komputerkit.warkopin.cart.CartAdapter;
import com.komputerkit.warkopin.cart.CartModel;
import com.komputerkit.warkopin.response.MessageResponse;
import com.komputerkit.warkopin.response.PreferencesUtil;
import com.komputerkit.warkopin.retrofit.ApiClient;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CartAdapter adapter;
    List<CartModel.Result> results = new ArrayList<CartModel.Result>();
    Button btnOrderMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btnOrderMenu = findViewById(R.id.btnOrderMenu);

        btnOrderMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderMenu();
            }
        });


        load();
        setUpRecyclerView();
        fetchDataCart();
        sumHargaCart();
    }

    public void load() {
        recyclerView = findViewById(R.id.recyclerviewcart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private String formatRupiah(int value) {
        // Format number as Indonesian Rupiah
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        return formatRupiah.format(value);
    }
    public void setUpRecyclerView(){
        adapter = new CartAdapter(results, new CartAdapter.OnAdaptorListener() {
            @Override
            public void onClick(CartModel.Result result) {
                Toast.makeText(CartActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void fetchDataCart(){

        String bearerToken = PreferencesUtil.getUserToken(this).toString();
        int iduser = Integer.parseInt(PreferencesUtil.getUserId(this).toString());

        Call<CartModel> call = ApiClient.getApiService().getCart("Bearer " + bearerToken, iduser);
        call.enqueue(new Callback<CartModel>() {
            @Override
            public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                List<CartModel.Result> results = response.body().getResult();
                adapter.setData(results);
            }

            @Override
            public void onFailure(Call<CartModel> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void orderMenu() {
        String bearerToken = PreferencesUtil.getUserToken(this).toString();
        int id = Integer.parseInt(PreferencesUtil.getUserId(this).toString());

        Call<MessageResponse> call = ApiClient.getApiService().orderMenu("Bearer " + bearerToken, id);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    MessageResponse messageResponse = response.body();

                    Toast.makeText(CartActivity.this, messageResponse.getMessage(), Toast.LENGTH_LONG).show();

                    fetchDataCart();
                } else {
                    Toast.makeText(CartActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                // Handle failure to connect to the server
                Toast.makeText(CartActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sumHargaCart() {
        String bearerToken = PreferencesUtil.getUserToken(this).toString();
        int idUser = Integer.parseInt(PreferencesUtil.getUserId(this).toString());

        Call<MessageResponse> call = ApiClient.getApiService().sumHargaCart("Bearer " + bearerToken, idUser);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    MessageResponse errorResponse = response.body();
                    TextView totalPembayaranCart = findViewById(R.id.tvTotalPembayaranCart);

                    int harga = Integer.parseInt(errorResponse.getMessage());
                    String formattedHarga = formatRupiah(harga);
                    totalPembayaranCart.setText(formattedHarga);

                    Toast.makeText(CartActivity.this, "Succes", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CartActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                // Handle failure to connect to the server
                Toast.makeText(CartActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void homepagefcart(View view) {
        Intent intent = new Intent(CartActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void historypagefcart(View view) {
        Intent intent = new Intent(CartActivity.this, HistoryDoneActivity.class);
        startActivity(intent);
        finish();
    }

    public void profilepagefcart(View view) {
        Intent intent = new Intent(CartActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}
