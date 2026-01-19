package com.komputerkit.warkopin.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.komputerkit.warkopin.R;
import com.komputerkit.warkopin.response.MessageResponse;
import com.komputerkit.warkopin.response.PreferencesUtil;
import com.komputerkit.warkopin.response.RoundTransformation;
import com.komputerkit.warkopin.retrofit.ApiClient;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private OnAdaptorListener listener;
    private List<CartModel.Result> results;
    public CartAdapter(List<CartModel.Result> results, CartAdapter.OnAdaptorListener listener) {
        this.results = results;
        this.listener = listener;
    }

    @NonNull @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cartuser_widget, viewGroup, false);
        return new CartAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.ViewHolder viewHolder, final int i) {
        CartModel.Result result = results.get(i);
        viewHolder.tvnamamenucart.setText(result.getMenu().getNama());
        viewHolder.tvjumlahcartuser.setText(result.getJumlah());

        int harga = Integer.parseInt(result.getCartharga());
        String formattedHarga = formatRupiah(harga);
        viewHolder.tvhargamenucart.setText(formattedHarga);

        Picasso.get()
                .load(result.getMenu().getImage())
                .fit()
                .transform(new RoundTransformation(12))
                .centerCrop()
                .into(viewHolder.imageViewCartUser);

        viewHolder.tvTambahJumlah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bearerToken = PreferencesUtil.getUserToken(viewHolder.itemView.getContext()).toString();
                int id = Integer.parseInt(result.getId());

                Call<MessageResponse> call = ApiClient.getApiService().tambahJumlahPesanan("Bearer " + bearerToken, id);
                call.enqueue(new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                        if (response.isSuccessful()) {
                            MessageResponse errorResponse = response.body();

                            Toast.makeText(viewHolder.itemView.getContext(), errorResponse.getMessage(), Toast.LENGTH_LONG).show();

                            String bearerToken = PreferencesUtil.getUserToken(viewHolder.itemView.getContext()).toString();
                            int iduser = Integer.parseInt(PreferencesUtil.getUserId(viewHolder.itemView.getContext()).toString());

                            Call<CartModel> calls = ApiClient.getApiService().getCart("Bearer " + bearerToken, iduser);
                            calls.enqueue(new Callback<CartModel>() {
                                @Override
                                public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                                    List<CartModel.Result> results = response.body().getResult();
                                    setData(results);
                                }

                                @Override
                                public void onFailure(Call<CartModel> call, Throwable t) {
                                    Toast.makeText(viewHolder.itemView.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(viewHolder.itemView.getContext(), "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        Toast.makeText(viewHolder.itemView.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        viewHolder.tvKurangJumlah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bearerToken = PreferencesUtil.getUserToken(viewHolder.itemView.getContext()).toString();
                int id = Integer.parseInt(result.getId());

                Call<MessageResponse> call = ApiClient.getApiService().kurangJumlahPesanan("Bearer " + bearerToken, id);
                call.enqueue(new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                        if (response.isSuccessful()) {
                            MessageResponse errorResponse = response.body();

                            Toast.makeText(viewHolder.itemView.getContext(), errorResponse.getMessage(), Toast.LENGTH_LONG).show();

                            String bearerToken = PreferencesUtil.getUserToken(viewHolder.itemView.getContext()).toString();
                            int iduser = Integer.parseInt(PreferencesUtil.getUserId(viewHolder.itemView.getContext()).toString());

                            Call<CartModel> calls = ApiClient.getApiService().getCart("Bearer " + bearerToken, iduser);
                            calls.enqueue(new Callback<CartModel>() {
                                @Override
                                public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                                    List<CartModel.Result> results = response.body().getResult();
                                    setData(results);
                                }

                                @Override
                                public void onFailure(Call<CartModel> call, Throwable t) {
                                    Toast.makeText(viewHolder.itemView.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(viewHolder.itemView.getContext(), "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        Toast.makeText(viewHolder.itemView.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        viewHolder.imageDeleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bearerToken = PreferencesUtil.getUserToken(viewHolder.itemView.getContext()).toString();
                int id = Integer.parseInt(result.getId());

                Call<Void> call = ApiClient.getApiService().removePesanan("Bearer " + bearerToken, id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(viewHolder.itemView.getContext(), "Succes", Toast.LENGTH_LONG).show();

                            String bearerToken = PreferencesUtil.getUserToken(viewHolder.itemView.getContext()).toString();
                            int iduser = Integer.parseInt(PreferencesUtil.getUserId(viewHolder.itemView.getContext()).toString());

                            Call<CartModel> calls = ApiClient.getApiService().getCart("Bearer " + bearerToken, iduser);
                            calls.enqueue(new Callback<CartModel>() {
                                @Override
                                public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                                    List<CartModel.Result> results = response.body().getResult();
                                    setData(results);
                                }

                                @Override
                                public void onFailure(Call<CartModel> call, Throwable t) {
                                    Toast.makeText(viewHolder.itemView.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(viewHolder.itemView.getContext(), "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(viewHolder.itemView.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnamamenucart, tvhargamenucart, tvjumlahcartuser, tvTambahJumlah, tvKurangJumlah;
        ImageView imageViewCartUser, imageDeleteCart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnamamenucart = itemView.findViewById(R.id.tvnamamenucart);
            tvhargamenucart = itemView.findViewById(R.id.tvhargamenucart);
            tvjumlahcartuser = itemView.findViewById(R.id.tvjumlahcartuser);
            tvTambahJumlah = itemView.findViewById(R.id.tvTambahJumlah);
            tvKurangJumlah = itemView.findViewById(R.id.tvKurangJumlah);
            imageViewCartUser = itemView.findViewById(R.id.imageViewCartUser);
            imageDeleteCart = itemView.findViewById(R.id.imageDeleteCart);
        }
    }

    public void setData(List<CartModel.Result> data) {
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }

    public interface OnAdaptorListener {
        void onClick(CartModel.Result result);
    }

    private String formatRupiah(int value) {
        // Format number as Indonesian Rupiah
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        return formatRupiah.format(value);
    }
}
