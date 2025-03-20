package com.example.androidsqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList ids, titles;
    private Context context;
    private DBConfig config;
    SQLiteDatabase db;
    private Intent intent;

    public DataAdapter(Context context, ArrayList ids, ArrayList titles) {
        this.context = context;
        this.ids = ids;
        this.titles = titles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        config = new DBConfig(listItemView.getContext());
        db = config.getReadableDatabase();
        return new ViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id = ids.get(position).toString();
        String title = titles.get(position).toString();

        holder.tvId.setText(id);
        holder.tvTitle.setText(title);

        // click listener
        holder.cvMain.setOnClickListener(v -> {
            intent = new Intent(context, DetailActivity.class);
            intent.putExtra("id", id);
            context.startActivity(intent);
        });

        // edit icon
        holder.ivEdit.setOnClickListener(v -> {
            intent = new Intent(context, EditActivity.class);
            intent.putExtra("id", id);
            context.startActivity(intent);
        });

        // delete icon
        holder.ivDelete.setOnClickListener(v -> {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
            alertBuilder.setTitle("Konfirmasi hapus data?")
                    .setItems(new CharSequence[]{"Ya", "Tidak"}, ((dialog, which) -> {
                        if(which == 0) {
                            db.execSQL("DELETE FROM tb_note WHERE id='" + id + "'");
                            ((MainActivity) context).showData();
                            Toast.makeText(context, "Berhasil dihapus!", Toast.LENGTH_SHORT).show();
                        }
                    }));
            alertBuilder.show();
        });

        // hold option
        holder.cvMain.setOnLongClickListener(v -> {
            BottomSheetDialog bsdOption = new BottomSheetDialog(context);
            bsdOption.setContentView(LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.bottom_sheet_dialog, null));
            bsdOption.show();

            bsdOption.findViewById(R.id.optUpdate).setOnClickListener(v1 -> {
                intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
                bsdOption.dismiss();
            });

            bsdOption.findViewById(R.id.optDelete).setOnClickListener(v1 -> {
                deleteData(id);
                bsdOption.dismiss();
            });


            return false;
        });
    }

    private void deleteData(String id) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("Konfirmasi hapus data?")
                        .setItems(new CharSequence[]{"Ya", "Tidak"}, ((dialog, which) -> {
                            if(which == 0) {
                                db.execSQL("DELETE FROM tb_note WHERE id='" + id + "'");
                                ((MainActivity) context).showData();
                                Toast.makeText(context, "Berhasil dihapus!", Toast.LENGTH_SHORT).show();
                            }
                        }));
        alertBuilder.show();
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId, tvTitle;
        private CardView cvMain;
        private ImageView ivEdit, ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
            cvMain = itemView.findViewById(R.id.cv_main);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }
}
