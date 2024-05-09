package com.example.jaipur_travel_guide.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.jaipur_travel_guide.Adapters.ShopAdapter;
import com.example.jaipur_travel_guide.Domains.ShopDomain;
import com.example.jaipur_travel_guide.R;

import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity {
    private RecyclerView recyclerViewShop;
    private RecyclerView.Adapter adapterShop;
    ImageView backBt,cartBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        backBt=findViewById(R.id.b1back);
        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingActivity.this, MainPage.class));
            }
        });
        cartBt=findViewById(R.id.cartIn);
        cartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingActivity.this,MainPage.class));
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<ShopDomain> items=new ArrayList<>();
        items.add(new ShopDomain("Peacock - Menakari work","300","peacockmenakari"));
        items.add(new ShopDomain("Blue Pottery","500","bluepottery"));
        items.add(new ShopDomain("Puppets","100","puppets"));
        items.add(new ShopDomain("Handicraft","250","handicrafts"));
        recyclerViewShop=findViewById(R.id.shopview);
        int numberOfColumns = 2; // Number of columns in the grid
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);
        recyclerViewShop.setLayoutManager(layoutManager);
        adapterShop=new ShopAdapter(items,ShoppingActivity.this);
        recyclerViewShop.setAdapter(adapterShop);
    }
}