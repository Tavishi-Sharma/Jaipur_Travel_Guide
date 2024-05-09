package com.example.jaipur_travel_guide.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.jaipur_travel_guide.Domains.EventDomain;
import com.example.jaipur_travel_guide.Adapters.EventAdapter;
import com.example.jaipur_travel_guide.R;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
private RecyclerView recyclerViewEvent;
private RecyclerView.Adapter adapterEvent;
TextView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initRecyclerView();
        backBtn=findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EventActivity.this, MainPage.class));
            }
        });
    }

    private void initRecyclerView() {
        ArrayList<EventDomain> items=new ArrayList<>();
        items.add(new EventDomain("LUCKY ALI CONCERT","lucky_ali_event","500","5 Apr"));
        items.add(new EventDomain("Vishal Mishra:Music Tour","vishal_mishra","200", "12 Apr"));
        items.add(new EventDomain("An enemy of the people","enemy","100", "22 Apr"));
        recyclerViewEvent=findViewById(R.id.eventView);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapterEvent=new EventAdapter(items);
        recyclerViewEvent.setAdapter(adapterEvent);
    }
}