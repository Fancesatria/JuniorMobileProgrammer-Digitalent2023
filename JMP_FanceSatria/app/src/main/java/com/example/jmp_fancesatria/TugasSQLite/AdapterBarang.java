package com.example.jmp_fancesatria.TugasSQLite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jmp_fancesatria.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterBarang extends BaseAdapter {

    Context context;
    List<ModelBarang> dataBarang;

    public AdapterBarang(Context context, List<ModelBarang> dataBarang) {
        this.context = context;
        this.dataBarang = dataBarang;
    }

    @Override
    public int getCount() {
        return dataBarang.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBarang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.item_barang, null);
        }

        TextView barang = convertView.findViewById(R.id.barang);
        TextView stok = convertView.findViewById(R.id.stock);

        barang.setText(dataBarang.get(position).getNama());
        stok.setText("Stok : "+dataBarang.get(position).getStok());

        LinearLayout ll = convertView.findViewById(R.id.ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context).setTitle("Pilihan")
                        .setMessage("Pilih aksi yang akan anda lakukan")
                        .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((SQLiteActivity) context).editItemAlert(dataBarang.get(position).getId(), dataBarang.get(position).getStok(), dataBarang.get(position).getNama());
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((SQLiteActivity) context).deleteItemAlert(dataBarang.get(position).getId());
                            }
                        }).show();
            }
        });

        return convertView;
    }
}
