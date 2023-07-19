package com.example.jmp_fancesatria.TugasAPI.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmp_fancesatria.TugasAPI.APIActivityActivity;
import com.example.jmp_fancesatria.TugasAPI.ModelAndResponse.ModelItem;
import com.example.jmp_fancesatria.TugasSQLite.SQLiteActivity;
import com.example.jmp_fancesatria.databinding.ItemBarang2Binding;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    List<ModelItem> dataItem = new ArrayList<>();

    public ItemAdapter(Context context, List<ModelItem> dataItem) {
        this.context = context;
        this.dataItem = dataItem;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBarang2Binding binding = ItemBarang2Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        ModelItem modelItem = dataItem.get(position);
        holder.bind.nama.setText(modelItem.getName());
        holder.bind.brand.setText(modelItem.getBrand());
        holder.bind.price.setText("IDR "+modelItem.getPrice());

        holder.bind.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context).setTitle("Pilihan")
                        .setMessage("Pilih aksi yang akan anda lakukan")
                        .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((APIActivityActivity) context).editItemAlert(modelItem.getId(), modelItem.getPrice(), modelItem.getBrand(), modelItem.getName());
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((APIActivityActivity) context).deleteItemAlert(modelItem.getId());
                            }
                        }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemBarang2Binding bind;
        public ViewHolder(@NonNull ItemBarang2Binding i) {
            super(i.getRoot());
            bind = i;
        }
    }
}
