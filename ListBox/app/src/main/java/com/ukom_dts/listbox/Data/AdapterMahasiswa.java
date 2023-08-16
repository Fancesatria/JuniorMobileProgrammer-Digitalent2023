package com.ukom_dts.listbox.Data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.ukom_dts.listbox.ListActivity;
import com.ukom_dts.listbox.R;
import com.ukom_dts.listbox.databinding.DialogOptionBinding;

import java.util.List;

public class AdapterMahasiswa extends BaseAdapter {
    Context context;
    List<ModelMahasiswa> data;

    public AdapterMahasiswa(Context context, List<ModelMahasiswa> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.item_mahasiswa, null);
        }

        TextView barang = convertView.findViewById(R.id.nama);
        TextView stok = convertView.findViewById(R.id.kelamin);

        barang.setText(data.get(position).getNama());
        stok.setText(data.get(position).getKelamin());

        LinearLayout ll = convertView.findViewById(R.id.ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListActivity) context).optionDialog(data.get(position).getId(), data.get(position).getNama(), data.get(position).getKelamin(), data.get(position).getAsal(), data.get(position).getAlamat(), data.get(position).getPend_terakhir());
            }
        });

        return convertView;
    }
}
