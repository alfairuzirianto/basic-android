package com.example.room2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room2.databinding.ItemLayoutBinding;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    List<Mahasiswa> listMahasiswa;
    OnClickItemInterface onClickItemInterface;
    MahasiswaViewModel mahasiswaViewModel;
    private Context context;

    public MahasiswaAdapter(Context context, OnClickItemInterface onClickItemInterface, MahasiswaViewModel mahasiswaViewModel) {
        this.context = context;
        this.onClickItemInterface = onClickItemInterface;
        this.mahasiswaViewModel = mahasiswaViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_layout, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (listMahasiswa != null) {
            Mahasiswa mahasiswa = listMahasiswa.get(position);
            holder.binding.setMahasiswa(mahasiswa);
//            holder.binding.setListener(onClickItemInterface);

            holder.binding.imgDelete.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Hapus data?")
                        .setItems(new CharSequence[]{"Ya", "Tidak"}, (dialog, pilihan) -> {
                            if(pilihan == 0) {
                                mahasiswaViewModel.deleteMahasiswa(mahasiswa);
                                Toast.makeText(v.getContext(), "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();
            });

            holder.binding.imgEdit.setOnClickListener(v -> {
                Dialog dialogEdit = new Dialog(v.getContext());
                dialogEdit.setContentView(R.layout.dialog_edit);
                EditText etNama = dialogEdit.findViewById(R.id.et_nama);
                etNama.setText(mahasiswa.namaMahasiswa);


                dialogEdit.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialogEdit.show();

                dialogEdit.findViewById(R.id.btn_simpan).setOnClickListener(v1 -> {
                    String nama = etNama.getText().toString();
                    if(nama.isBlank()) {
                        Toast.makeText(v1.getContext(), "Data harus diisi", Toast.LENGTH_SHORT).show();
                    } else {
                        mahasiswa.namaMahasiswa = nama;
                        mahasiswaViewModel.updateMahasiswa(mahasiswa);
                        dialogEdit.dismiss();
                        ((MainActivity) context).syncData();
                        Toast.makeText(v1.getContext(), "Berhasil diedit", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        }
    }

    @Override
    public int getItemCount() {
        if (listMahasiswa != null){
            return listMahasiswa.size();
        }
        else return 0;
    }

    public void setListMahasiswa(List<Mahasiswa> listMahasiswa) {
        this.listMahasiswa = listMahasiswa;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemLayoutBinding binding;

        public ViewHolder(@NonNull ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
