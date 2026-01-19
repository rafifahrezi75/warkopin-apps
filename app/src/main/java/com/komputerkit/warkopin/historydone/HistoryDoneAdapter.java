package com.komputerkit.warkopin.historydone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.komputerkit.warkopin.HistoryDoneDetailActivity;
import com.komputerkit.warkopin.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoryDoneAdapter extends RecyclerView.Adapter<HistoryDoneAdapter.ViewHolder> implements Filterable {
    private OnAdaptorListener listener;
    private List<HistoryDoneModel.Result> results;
    private List<HistoryDoneModel.Result> filteredresult;
    private ValueFilter valueFilter;
    public HistoryDoneAdapter(List<HistoryDoneModel.Result> results, HistoryDoneAdapter.OnAdaptorListener listener) {
        this.results = results;
        this.listener = listener;
        this.filteredresult = new ArrayList<>(results);
    }

    @NonNull    @Override
    public HistoryDoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.historydone_widget, viewGroup, false);
        return new HistoryDoneAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryDoneAdapter.ViewHolder viewHolder, final int i) {
        HistoryDoneModel.Result result = results.get(i);
        viewHolder.tvkodepesananhistorydone.setText(result.getKodeorder());

        int harga = Integer.parseInt(result.getTotalharga());
        String formattedHarga = formatRupiah(harga);
        viewHolder.tvhargapesananhistorydone.setText(formattedHarga);
        viewHolder.tvtglpesananhistorydone.setText(result.getTglorder());

        int bayar = Integer.parseInt(result.getBayar());
        String formattedHargaBayar = formatRupiah(bayar);

        int kembali = Integer.parseInt(result.getKembali());
        String formattedHargaKembali = formatRupiah(kembali);

        viewHolder.historydone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                // Create an Intent to start DetailMenuAdminActivity
                Intent intent = new Intent(context, HistoryDoneDetailActivity.class);

                intent.putExtra("ID_ORDER", result.getId());
                intent.putExtra("KODE_ORDER", result.getKodeorder());
                intent.putExtra("USER_ORDER", result.getUser().getName());
                intent.putExtra("BAYAR_ORDER", formattedHargaBayar);
                intent.putExtra("KEMBALI_ORDER", formattedHargaKembali);
                intent.putExtra("TOTAL_ORDER", formattedHarga);

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
        TextView tvkodepesananhistorydone, tvhargapesananhistorydone, tvtglpesananhistorydone;
        LinearLayout historydone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvkodepesananhistorydone = itemView.findViewById(R.id.tvkodepesananhistorydone);
            tvhargapesananhistorydone = itemView.findViewById(R.id.tvhargapesananhistorydone);
            tvtglpesananhistorydone = itemView.findViewById(R.id.tvtglpesananhistorydone);
            historydone = itemView.findViewById(R.id.historydone);
        }
    }

    public void setData(List<HistoryDoneModel.Result> data) {
        filteredresult.clear();
        filteredresult.addAll(data);
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }

    public interface OnAdaptorListener {
        void onClick(HistoryDoneModel.Result result);
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
                List<HistoryDoneModel.Result> filterList = new ArrayList<>();
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (int i = 0; i < filteredresult.size(); i++) {
                    HistoryDoneModel.Result currentItem = filteredresult.get(i);
                    if (String.valueOf(currentItem.getId()).contains(filterPattern)
                            || currentItem.getTglorder().toLowerCase().contains(filterPattern)) {
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
            HistoryDoneAdapter.this.results = (List<HistoryDoneModel.Result>) results.values;
            notifyDataSetChanged(); // Menggunakan filteredList untuk mengupdate data pada adapter
        }
    }
}
