package com.example.jmp_fancesatria.TugasAPI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jmp_fancesatria.R;
import com.example.jmp_fancesatria.TugasAPI.API.addItem;
import com.example.jmp_fancesatria.TugasAPI.API.updateItem;
import com.example.jmp_fancesatria.databinding.ActivityInsertAndUpdateApiactivityBinding;

import hivatec.ir.easywebservice.Callback;
import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;

public class InsertAndUpdateAPIActivity extends AppCompatActivity {
    ActivityInsertAndUpdateApiactivityBinding bind;
    int idbarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityInsertAndUpdateApiactivityBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        cekIntent();
        bind.btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertOrUpdate();
            }
        });
    }

    public boolean cekIntent(){
        if(getIntent().getIntExtra("editFlag", 0) == 1){
            bind.textView.setText("Ubah Data Item");
            bind.btnsimpan.setText("Simpan Perubahan");

            idbarang = getIntent().getIntExtra("id", 0);

            bind.etBrand.setText(getIntent().getStringExtra("brand"));
            bind.etNama.setText(getIntent().getStringExtra("name"));
            bind.etPrice.setText(getIntent().getIntExtra("price", 0)+"");
            return false;
        } else {
            bind.textView.setText("Tambah Data Item");
            bind.btnsimpan.setText("Simpan");
            return true;
        }
    }

    public void insertOrUpdate(){
        String name = bind.etNama.getText().toString();
        String brand = bind.etBrand.getText().toString();
        int price = Integer.parseInt(bind.etPrice.getText().toString());
        String price2 = bind.etPrice.getText().toString();

        if(name.isEmpty() || brand.isEmpty() || String.valueOf(price).isEmpty()){
            Toast.makeText(this, "Pastikan data terisi semua", Toast.LENGTH_SHORT).show();
        } else {
            if(cekIntent()){
//                addItem addItem = new addItem(InsertAndUpdateAPIActivity.this, bind);
//                addItem.execute(name, brand, String.valueOf(price));

                new EasyWebservice(" http://vsga.zulhaydarakbar.com/fanceToken/item/create")
                        .method(Method.POST) //default
                        .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                        .addParam("name", name) //adding params to body
                        .addParam("brand", brand) //adding params to body
                        .addParam("price", price) //adding params to body
                        .call(new Callback.AB<Boolean, String>("res", "msg") { //should map response params
                            @Override
                            public void onSuccess(Boolean res, String msg) {

                                backToMain();
                            }

                            @Override
                            public void onError(String error) {

                                //if any error encountered
                            }
                        });
            } else {
//                updateItem updateItem = new updateItem(bind, InsertAndUpdateAPIActivity.this);
//                updateItem.execute(String.valueOf(idbarang), name, brand, price2);

                new EasyWebservice(" http://vsga.zulhaydarakbar.com/fanceToken/item/"+idbarang+"/update")
                        .method(Method.POST) //default
                        .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                        .addParam("name", name) //adding params to body
                        .addParam("brand", brand) //adding params to body
                        .addParam("price", price) //adding params to body
                        .call(new Callback.AB<Boolean, String>("res", "msg") { //should map response params
                            @Override
                            public void onSuccess(Boolean res, String msg) {

                                backToMain();
                            }

                            @Override
                            public void onError(String error) {

                                //if any error encountered
                            }
                        });
            }
        }
    }


    public void backToMain(){
        startActivity(new Intent(InsertAndUpdateAPIActivity.this, APIActivityActivity.class));
        finish();
    }

}