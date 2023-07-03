package com.example.jmp_fancesatria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.jmp_fancesatria.RecyclerView.DataHeroes;
import com.example.jmp_fancesatria.RecyclerView.HeroAdapter;
import com.example.jmp_fancesatria.RecyclerView.ModelHero;
import com.example.jmp_fancesatria.databinding.ActivityListviewBinding;

import java.util.ArrayList;

public class ListviewActivity extends AppCompatActivity {
    ActivityListviewBinding bind;
    private ArrayList<ModelHero> data = new ArrayList<>();
    HeroAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityListviewBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.rv.setHasFixedSize(true);
        data.addAll(DataHeroes.getListData());
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
        adapter =new HeroAdapter(this, data);
        bind.rv.setAdapter(adapter);

    }
}