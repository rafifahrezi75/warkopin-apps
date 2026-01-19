package com.komputerkit.warkopin.historyproses;

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
import com.komputerkit.warkopin.HistoryProsesDetailActivity;
import com.komputerkit.warkopin.R;
import com.komputerkit.warkopin.historydone.HistoryDoneAdapter;
import com.komputerkit.warkopin.historydone.HistoryDoneModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoryProsesAdapter extends RecyclerView.Adapter<HistoryProsesAdapter.ViewHolder> implements Filterable {
    private OnAdaptorListener listener;
    private List<HistoryProsesModel.Result> results;
    private List<HistoryProsesModel.Result> filteredresult;
    private ValueFilter valueFilter;

    public HistoryProsesAdapter(List<HistoryProsesModel.Result> results, HistoryProsesAdapter.OnAdaptorListener listener) {
        this.results = results;
        this.listener = listener;
        this.filteredresult = new ArrayList<>(results);
    }

    @NonNull
    @Override
    public HistoryProsesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.historyproses_widget, viewGroup, false);
        return new HistoryProsesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryProsesAdapter.ViewHolder viewHolder, final int i) {
        HistoryProsesModel.Result result = results.get(i);
        viewHolder.tvkodepesananhistoryproses.setText(result.getKodeorder());
        viewHolder.tvtglpesananhistoryproses.setText(result.getTglorder());

        int harga = Integer.parseInt(result.getTotalharga());
        String formattedHarga = formatRupiah(harga);
        viewHolder.tvhargapesananhistoryproses.setText(formattedHarga);

        viewHolder.historyproses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                // Create an Intent to start DetailMenuAdminActivity
                Intent intent = new Intent(context, HistoryProsesDetailActivity.class);

                intent.putExtra("ID_ORDER", result.getId());
                intent.putExtra("KODE_ORDER", result.getKodeorder());
                intent.putExtra("USER_ORDER", result.getUser().getName());
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
        TextView tvkodepesananhistoryproses, tvhargapesananhistoryproses, tvtglpesananhistoryproses;
        LinearLayout historyproses;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvkodepesananhistoryproses = itemView.findViewById(R.id.tvkodepesananhistoryproses);
            tvhargapesananhistoryproses = itemView.findViewById(R.id.tvhargapesananhistoryproses);
            tvtglpesananhistoryproses = itemView.findViewById(R.id.tvtglpesananhistoryproses);
            historyproses = itemView.findViewById(R.id.historyproses);
        }
    }
    public void setData(List<HistoryProsesModel.Result> data) {
        filteredresult.clear();
        filteredresult.addAll(data);
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }

    public interface OnAdaptorListener {
        void onClick(HistoryProsesModel.Result result);
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
                List<HistoryProsesModel.Result> filterList = new ArrayList<>();
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (int i = 0; i < filteredresult.size(); i++) {
                    HistoryProsesModel.Result currentItem = filteredresult.get(i);
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
            HistoryProsesAdapter.this.results = (List<HistoryProsesModel.Result>) results.values;
            notifyDataSetChanged(); // Menggunakan filteredList untuk mengupdate data pada adapter
        }
    }
}
