package com.example.djmag;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListDjAdapter extends RecyclerView.Adapter<ListDjAdapter.ListViewHolder> {
    private ArrayList<Dj> listDj;

    public ListDjAdapter(ArrayList<Dj> list) {
        this.listDj = list;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvRank, tvName, tvDesc;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvRank = itemView.findViewById(R.id.tv_rank);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Dj dj = listDj.get(position);
        holder.tvRank.setText(dj.getRank());
        holder.imgPhoto.setImageResource(dj.getPhoto());
        holder.tvName.setText(dj.getName());
        holder.tvDesc.setText(dj.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Intent intentDetail = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intentDetail.putExtra("key_dj", listDj.get(holder.getAdapterPosition()));
            holder.itemView.getContext().startActivity(intentDetail);
        });
    }

    @Override
    public int getItemCount() {
        return listDj.size();
    }

}
