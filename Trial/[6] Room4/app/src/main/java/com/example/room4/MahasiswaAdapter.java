package com.example.room4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    private List<Mahasiswa> listMahasiswa;
    private Context context;
    private ClickListener listener;

    public MahasiswaAdapter(ClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Mahasiswa> listMahasiswa) {
        this.listMahasiswa = listMahasiswa;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mahasiswa mahasiswa = listMahasiswa.get(position);
        String nama = mahasiswa.getName();

        holder.tvNama.setText(nama);
        holder.btnSettings.setOnClickListener(v -> {
            showMenu(v, mahasiswa);
        });
    }

    @Override
    public int getItemCount() {
        return listMahasiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama;
        ImageView btnSettings;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            btnSettings = itemView.findViewById(R.id.btn_settings);
        }
    }

    public interface ClickListener{
        void updateClicked(Mahasiswa mahasiswa);
        void deleteClicked(Mahasiswa mahasiswa);
    }

    public void showMenu(View view, Mahasiswa mahasiswa) {
        PopupMenu menu = new PopupMenu(context, view);
        menu.inflate(R.menu.menu);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.itemUpdate) listener.updateClicked(mahasiswa);
                else listener.deleteClicked(mahasiswa);
                return false;
            }
        });

        menu.show();
    }
}
