package com.example.room1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    private ArrayList<Integer> listNim;
    private ArrayList<String> listNama;
    private Context context;
    private Dialog dialogEdit;

    AppDatabase db;

    public MahasiswaAdapter(Context context, ArrayList listNim, ArrayList listNama) {
        this.context = context;
        this.listNim = listNim;
        this.listNama = listNama;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNim, tvNama;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tv_nim);
            tvNama = itemView.findViewById(R.id.tv_nama);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_layout, parent, false);
        return new ViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nim = listNim.get(position).toString();
        String nama = listNama.get(position).toString();

        holder.tvNim.setText(nim);
        holder.tvNama.setText(nama);

        holder.itemView.setOnLongClickListener(v -> {
            dialogEdit = new Dialog(holder.itemView.getContext());
            dialogEdit.setContentView(R.layout.dialog_edit);
            dialogEdit.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialogEdit.show();

            Button btnSimpan = dialogEdit.findViewById(R.id.btn_simpan);
            Button btnHapus = dialogEdit.findViewById(R.id.btn_hapus);
            EditText inputNama = dialogEdit.findViewById(R.id.et_nama);
            inputNama.setText(nama);

            btnSimpan.setOnClickListener(v1 -> {
//                String newNama = inputNama.getText().toString();
//                if(newNama.isBlank()) Toast.makeText(context, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
//                else {
//                    db.execSQL("UPDATE tb_mahasiswa SET nama = '" + newNama + "' WHERE nim = '" + nim + "'");
//                    dialogEdit.dismiss();
//                    Toast.makeText(context, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
//                    ((MainActivity) context).showData();
//                }
            });

            btnHapus.setOnClickListener(v1 -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Konfirmasi hapus data?")
                        .setItems(new CharSequence[]{"Ya", "Tidak"}, (dialog, pilihan) -> {
                            if(pilihan == 0) {
                                dialogEdit.dismiss();
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Mahasiswa mahasiswa = AppDatabase.getInstance(context.getApplicationContext())
                                                .dao().findMahasiswa(Integer.parseInt(nim));
                                        AppDatabase.getInstance(context.getApplicationContext())
                                                .dao().deleteMahasiswa(mahasiswa);
                                    }
                                });
                                thread.start();
                                Toast.makeText(context, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();
            });

            return false;
        });
    }

    @Override
    public int getItemCount() {
        return listNim.size();
    }
}
