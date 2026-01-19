package com.komputerkit.warkopin.menu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.komputerkit.warkopin.DetailActivity;
import com.komputerkit.warkopin.R;
import com.komputerkit.warkopin.response.RoundTransformation;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> implements Filterable {
    private OnAdaptorListener listener;
    private List<MenuModel.Result> results;
    private List<MenuModel.Result> filteredresult;
    private ValueFilter valueFilter;
    public MenuAdapter(List<MenuModel.Result> results, MenuAdapter.OnAdaptorListener listener) {
        this.results = results;
        this.listener = listener;
        this.filteredresult = new ArrayList<>(results);
    }

    @NonNull    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menukami_widget, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final  ViewHolder viewHolder, final int i) {
        MenuModel.Result result = results.get(i);
        viewHolder.tvnamamenu.setText(result.getNama());
        viewHolder.tvkategorimenu.setText(result.getKategori().getKategori());

        int harga = Integer.parseInt(result.getHarga());
        String formattedHarga = formatRupiah(harga);
        viewHolder.tvhargamenu.setText(formattedHarga);

        Picasso.get()
                .load(result.getImage())
                .fit()
                .transform(new RoundTransformation(12))
                .centerCrop()
                .into(viewHolder.imageMenu);

        viewHolder.menuall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                // Create an Intent to start DetailMenuAdminActivity
                Intent intent = new Intent(context, DetailActivity.class);

                // Put the value of getNama() into the intent
                intent.putExtra("ID_MENU", result.getId());
                intent.putExtra("NAMA_MENU", result.getNama());
                intent.putExtra("KATEGORI_MENU", result.getKategori().getKategori());
                intent.putExtra("IMAGE_MENU", result.getImage());
                intent.putExtra("HARGA_MENU", formattedHarga);
                intent.putExtra("STOK_MENU", result.getStok());
                intent.putExtra("DESKRIPSI_MENU", result.getDeskripsi());

                // Start the new activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnamamenu,tvhargamenu, tvkategorimenu;
        LinearLayout menuall;
        ImageView imageMenu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnamamenu = itemView.findViewById(R.id.tvnamamenu);
            tvhargamenu = itemView.findViewById(R.id.tvhargamenu);
            tvkategorimenu = itemView.findViewById(R.id.tvkategorimenu);
            menuall = itemView.findViewById(R.id.menuall);
            imageMenu = itemView.findViewById(R.id.imageMenu);
        }
    }

    public void setData(List<MenuModel.Result> data) {
        filteredresult.clear();
        filteredresult.addAll(data);
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }

    public interface OnAdaptorListener {
        void onClick(MenuModel.Result result);
    }

    private String formatRupiah(int value) {
        // Format number as Indonesian Rupiah
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        return formatRupiah.format(value);
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null){
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                // Jika constraint tidak kosong, filter sesuai dengan kriteria
                List<MenuModel.Result> filterList = new ArrayList<>();
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (int i = 0; i < filteredresult.size(); i++) {
                    MenuModel.Result currentItem = filteredresult.get(i);
                    if (String.valueOf(currentItem.getId()).contains(filterPattern)
                            || currentItem.getNama().toLowerCase().contains(filterPattern)) {
                        filterList.add(currentItem);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                // Jika constraint kosong, tampilkan seluruh data
                results.count = filteredresult.size();
                results.values = filteredresult;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            MenuAdapter.this.results = (List<MenuModel.Result>) results.values;
            notifyDataSetChanged(); // Menggunakan filteredList untuk mengupdate data pada adapter
        }
    }
}
