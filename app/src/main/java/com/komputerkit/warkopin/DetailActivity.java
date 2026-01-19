package com.komputerkit.warkopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.komputerkit.warkopin.response.PreferencesUtil;
import com.komputerkit.warkopin.retrofit.ApiClient;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    Button btnAddCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailmenu);

        btnAddCart = findViewById(R.id.btnAddCart);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCart();
            }
        });

        String namaMenu = getIntent().getStringExtra("NAMA_MENU");
        String kategoriMenu = getIntent().getStringExtra("KATEGORI_MENU");
        String imageMenu = getIntent().getStringExtra("IMAGE_MENU");
        String hargaMenu = getIntent().getStringExtra("HARGA_MENU");
        String deskripsiMenu = getIntent().getStringExtra("DESKRIPSI_MENU");

        TextView namaMenuDetail = findViewById(R.id.tvNamaMenuDetail);
        TextView kategoriMenuDetail = findViewById(R.id.tvKategoriMenuDetail);
        ImageView imageMenuDetail = findViewById(R.id.imageMenuDetail);
        TextView hargaMenuDetail = findViewById(R.id.tvHargaMenuDetail);
        TextView deskripsiMenuDetail = findViewById(R.id.tvDeskripsiMenuDetail);

        Picasso.get()
                .load(imageMenu)
                .into(imageMenuDetail);

        namaMenuDetail.setText(namaMenu);
        kategoriMenuDetail.setText(kategoriMenu);
        hargaMenuDetail.setText(hargaMenu);
        deskripsiMenuDetail.setText(deskripsiMenu);
    }

    public void addCart() {
        String bearerToken = PreferencesUtil.getUserToken(this).toString();
        int id = Integer.parseInt(getIntent().getStringExtra("ID_MENU"));
        int idUser = Integer.parseInt(PreferencesUtil.getUserId(this).toString());

        Call<Void> call = ApiClient.getApiService().addCart("Bearer " + bearerToken, id, idUser);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetailActivity.this, "Succes", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DetailActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure to connect to the server
                Toast.makeText(DetailActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void backmenu(View view) {
        Intent intent = new Intent(DetailActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

}
