package com.komputerkit.warkopin.dummy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.komputerkit.warkopin.DetailActivity;
import com.komputerkit.warkopin.R;

import java.util.List;

public class KategoriMenuAdapter extends RecyclerView.Adapter<KategoriMenuAdapter.ViewHolder> {
    private Context context;
    private List<KategoriMenuModel> kategoriMenuList;
    public KategoriMenuAdapter(Context context,List<KategoriMenuModel> kategoriMenuList) {
        this.context = context;
        this.kategoriMenuList = kategoriMenuList;
    }

    @NonNull    @Override
    public KategoriMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.kategorimenu_widget, viewGroup, false);
        return new KategoriMenuAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final KategoriMenuAdapter.ViewHolder viewHolder, final int i) {
        KategoriMenuModel kategoriMenu = kategoriMenuList.get(i);
        viewHolder.tvnamamenukategori.setText(kategoriMenu.getNamakategorimenu());
        viewHolder.tvkategorimenukategori.setText(kategoriMenu.getKategorikategorimenu());
        viewHolder.tvhargamenukategori.setText(kategoriMenu.getHargakategorimenu());
        viewHolder.kategorimenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kategoriMenuList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnamamenukategori, tvhargamenukategori, tvkategorimenukategori;
        LinearLayout kategorimenu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnamamenukategori = itemView.findViewById(R.id.tvnamamenukategori);
            tvhargamenukategori = itemView.findViewById(R.id.tvhargamenukategori);
            tvkategorimenukategori = itemView.findViewById(R.id.tvkategorimenukategori);
            kategorimenu = itemView.findViewById(R.id.kategorimenu);
        }
    }

}
