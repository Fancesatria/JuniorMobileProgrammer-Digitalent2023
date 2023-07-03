package com.example.jmp_fancesatria.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.jmp_fancesatria.databinding.ItemHeroBinding;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {

    Context context;
    private List<ModelHero> data;

    public HeroAdapter(Context context, List<ModelHero> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModelHero hero = data.get(position);
        Glide.with(context)
                .load(hero.getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.bind.imgItemPhoto);
        holder.bind.tvItemName.setText(hero.getName());
        holder.bind.tvItemFrom.setText(hero.getFrom());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,""+hero.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHeroBinding bind;
        bind = ItemHeroBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(bind);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemHeroBinding bind;

        public ViewHolder(@NonNull ItemHeroBinding itemView) {
            super(itemView.getRoot());

            bind = itemView;
        }
    }

}
