package com.example.jaipur_travel_guide.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jaipur_travel_guide.Domains.ShopDomain;
import com.example.jaipur_travel_guide.R;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{
    ArrayList<ShopDomain> items;
    Context context;

    public ShopAdapter(ArrayList<ShopDomain> items,Context context){
        this.context=context;
        this.items=items;
    }

    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_shop,parent,false);
        return new ShopAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShopDomain currentItem = items.get(position);
        holder.iname.setText(currentItem.getIname());
        holder.iprice.setText(currentItem.getIprice());
        int drawableResourceId = context.getResources().getIdentifier(currentItem.getIpic(), "drawable", context.getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.ipic);

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView iname,iprice;
        ImageView ipic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iname=itemView.findViewById(R.id.iname);
            iprice=itemView.findViewById(R.id.iprice);
            ipic=itemView.findViewById(R.id.ipic);
        }
    }
}
