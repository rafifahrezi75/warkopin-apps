package com.komputerkit.warkopin.historydetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.komputerkit.warkopin.R;
import com.komputerkit.warkopin.historydone.HistoryDoneAdapter;
import com.komputerkit.warkopin.historydone.HistoryDoneModel;
import com.komputerkit.warkopin.response.RoundTransformation;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class HistoryDetailAdapter extends RecyclerView.Adapter<HistoryDetailAdapter.ViewHolder> {
    private OnAdaptorListener listener;
    private List<HistoryDetailModel.Result> results;
    public HistoryDetailAdapter(List<HistoryDetailModel.Result> results, HistoryDetailAdapter.OnAdaptorListener listener) {
        this.results = results;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HistoryDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.historydetail_widget, viewGroup, false);
        return new HistoryDetailAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryDetailAdapter.ViewHolder viewHolder, final int i) {
        HistoryDetailModel.Result result = results.get(i);
        viewHolder.tvnamamenuhistorydetail.setText(result.getMenu().getNama());
        viewHolder.tvkategorimenuhistorydetail.setText(result.getMenu().getKategori().getKategori());

        int harga = Integer.parseInt(result.getHargapenjualan());
        String formattedHarga = formatRupiah(harga);
        viewHolder.tvhargamenuhistorydetail.setText(formattedHarga);
        viewHolder.tvjumlahmenuhistorydetail.setText(result.getJumlah());

        Picasso.get()
                .load(result.getMenu().getImage())
                .fit()
                .transform(new RoundTransformation(12))
                .centerCrop()
                .into(viewHolder.imageViewHistoryDetail);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnamamenuhistorydetail,tvkategorimenuhistorydetail, tvhargamenuhistorydetail, tvjumlahmenuhistorydetail;
        ImageView imageViewHistoryDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnamamenuhistorydetail = itemView.findViewById(R.id.tvnamamenuhistorydetail);
            tvkategorimenuhistorydetail = itemView.findViewById(R.id.tvkategorimenuhistorydetail);
            tvhargamenuhistorydetail = itemView.findViewById(R.id.tvhargamenuhistorydetail);
            tvjumlahmenuhistorydetail = itemView.findViewById(R.id.tvjumlahmenuhistorydetail);
            imageViewHistoryDetail = itemView.findViewById(R.id.imageViewHistoryDetail);
        }
    }
    public void setData(List<HistoryDetailModel.Result> data) {
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }

    public interface OnAdaptorListener {
        void onClick(HistoryDetailModel.Result result);
    }

    private String formatRupiah(int value) {
        // Format number as Indonesian Rupiah
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        return formatRupiah.format(value);
    }
}
