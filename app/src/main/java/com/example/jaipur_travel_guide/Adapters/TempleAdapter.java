package com.example.jaipur_travel_guide.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jaipur_travel_guide.Domains.TempleDomain;
import com.example.jaipur_travel_guide.R;

import java.util.ArrayList;

public class TempleAdapter extends BaseAdapter {
        private ArrayList<TempleDomain> items;
        private Context context;

    public TempleAdapter(ArrayList<TempleDomain> items, Context context){
            this.items = items;
            this.context = context;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.viewholder_temple, viewGroup, false);
            holder = new ViewHolder();
            holder.tempNameTextView = convertView.findViewById(R.id.tempName);
            holder.tempLocTextView = convertView.findViewById(R.id.tempLoc);
            holder.tempImageView = convertView.findViewById(R.id.tempIim);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TempleDomain currentItem = items.get(i);
        holder.tempNameTextView.setText(currentItem.getTempName());
        holder.tempLocTextView.setText(currentItem.getTempLoc());

        int drawableResId = context.getResources().getIdentifier(currentItem.getTempImg(),
                "drawable", context.getPackageName());
        Glide.with(context)
                .load(drawableResId)
                .into(holder.tempImageView);

        return convertView;
    }



        static class ViewHolder {
            TextView tempNameTextView;
            TextView tempLocTextView;
            ImageView tempImageView;
        }
}
