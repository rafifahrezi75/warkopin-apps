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

public class MenuDashboardAdapter extends RecyclerView.Adapter<MenuDashboardAdapter.ViewHolder> {
    private Context context;
    private List<MenuDashboardModel> menuDashboardList;
    public MenuDashboardAdapter(Context context,List<MenuDashboardModel> menuDashboardList) {
        this.context = context;
        this.menuDashboardList = menuDashboardList;
    }

    @NonNull
    @Override
    public MenuDashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menudashboard_widget, viewGroup, false);
        return new MenuDashboardAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuDashboardAdapter.ViewHolder viewHolder, final int i) {
        MenuDashboardModel menuDashboard = menuDashboardList.get(i);
        viewHolder.tvnamamenudashboard.setText(menuDashboard.getNamaMenuDashboard());
        viewHolder.tvkategorimenudashboard.setText(menuDashboard.getKategoriMenuDashboard());
        viewHolder.tvhargamenudashboard.setText(menuDashboard.getHargaMenuDashboard());
        viewHolder.menudashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuDashboardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnamamenudashboard, tvkategorimenudashboard, tvhargamenudashboard;
        LinearLayout menudashboard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnamamenudashboard = itemView.findViewById(R.id.tvnamamenudashboard);
            tvkategorimenudashboard = itemView.findViewById(R.id.tvkategorimenudashboard);
            tvhargamenudashboard = itemView.findViewById(R.id.tvhargamenudashboard);
            menudashboard = itemView.findViewById(R.id.menudashboard);
        }
    }
}
