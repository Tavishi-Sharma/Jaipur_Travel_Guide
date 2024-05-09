package com.example.jaipur_travel_guide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jaipur_travel_guide.Domains.CategoryDomain;
import com.example.jaipur_travel_guide.R;

import java.util.ArrayList;
import com.example.jaipur_travel_guide.Activities.MainPage;
import com.example.jaipur_travel_guide.Activities.TempleActivity;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<CategoryDomain> items;
    private Context context;

    public CategoryAdapter(ArrayList<CategoryDomain> items, Context context){
        this.items=items;
        this.context=context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
         holder.titleTxt.setText(items.get(position).getTitle());
         int drawableResourceId=holder.itemView.getResources().getIdentifier(items.get(position).getPicPath(),
                 "drawable",holder.itemView.getContext().getPackageName());
         Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.picImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (items.get(position).getTitle()) { // Assuming getType() returns the type of item
                    //case "cafe":
                      //  intent = new Intent(context, TempleActivity.class);
                       // break;
                    case "Temples":
                           intent = new Intent(context, TempleActivity.class);
                        break;
                    // Add more cases for other types of items
                    default:
                        intent = new Intent(context, MainPage.class);
                        break;
                }
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView picImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            picImg=itemView.findViewById(R.id.catImg);
        }
    }
}
