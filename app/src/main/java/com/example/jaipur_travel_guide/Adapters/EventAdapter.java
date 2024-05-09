package com.example.jaipur_travel_guide.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import com.example.jaipur_travel_guide.Domains.EventDomain;
import com.example.jaipur_travel_guide.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    ArrayList<EventDomain> items;

    public EventAdapter(ArrayList<EventDomain> items) {
        this.items = items;
    }
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_event,parent,false);
        return new EventAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.pass.setText(items.get(position).getPrice());
        holder.date.setText(items.get(position).getDate());
        int drawableResourceId=holder.itemView.getResources().getIdentifier(items.get(position).getPicPath(),
                "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.picImg);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView picImg;
        TextView pass,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.eve_title);
            picImg=itemView.findViewById(R.id.evepic);
            pass=itemView.findViewById(R.id.price_txt);
            date=itemView.findViewById(R.id.date);
        }
    }
}
