package com.example.latianfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemListFragment extends Fragment {
    private RecyclerView vRecycler;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;
    private ImageButton ibMenu, ibCart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        vRecycler = view.findViewById(R.id.v_recycler);
        vRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Initialize the item list and adapter
        itemList = new ArrayList<>();
        itemList.add(new Item("Nokha Sepatu Sneakers Triglav Alpha Pria", "Rp389.100", R.drawable.product1));
        itemList.add(new Item("Nokha Sepatu Sneakers Ava Black Women", "Rp370.000", R.drawable.product2));
        itemList.add(new Item("Nokha Sepatu Boots Kody Ivory Wanita", "Rp362.000", R.drawable.product3));
        itemList.add(new Item("Nokha Sepatu Sneakers Ava Navy Women", "Rp371.000", R.drawable.product4));
        itemList.add(new Item("Nokha Sneakers Sivas Pearl Pria", "Rp380.000", R.drawable.product5));
        itemList.add(new Item("Nokha Sepatu Boots Geneva Mocca Pria", "Rp384.490", R.drawable.product6));
        itemList.add(new Item("Nokha Sepatu Boots Kody Pink Wanita", "Rp362.100", R.drawable.product7));
        itemList.add(new Item("Nokha Sneakers Wave Full White Wanita", "Rp444.000", R.drawable.product8));
        itemList.add(new Item("Nokha Kody Black Boots Wanita", "Rp362.100", R.drawable.product9));

        itemAdapter = new ItemAdapter(getContext(), itemList);
        vRecycler.setAdapter(itemAdapter);

        ibCart = view.findViewById(R.id.ib_cart);
        ibMenu = view.findViewById(R.id.ib_menu);

        // Toast onClick
        ibCart.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Tombol ini belum tersedia!", Toast.LENGTH_SHORT).show();
        });
        ibMenu.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Tombol ini belum tersedia!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}