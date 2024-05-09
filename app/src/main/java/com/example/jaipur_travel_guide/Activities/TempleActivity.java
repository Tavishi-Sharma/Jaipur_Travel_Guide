package com.example.jaipur_travel_guide.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jaipur_travel_guide.Adapters.TempleAdapter;
import com.example.jaipur_travel_guide.Domains.TempleDomain;
import com.example.jaipur_travel_guide.R;

import java.util.ArrayList;

public class TempleActivity extends AppCompatActivity {

    private ListView templeListView;
    private TempleAdapter templeAdapter;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temple);

        templeListView = findViewById(R.id.templeListView);
        backBtn = findViewById(R.id.tempback);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TempleActivity.this, MainPage.class));
            }
        });

        initListView();
    }

    private void initListView() {
        ArrayList<TempleDomain> temples = new ArrayList<>();
        temples.add(new TempleDomain("akshay_patra", "Akshay Patra","Mahal Road,Jagatpura,Jaipur"));
        temples.add(new TempleDomain("akshrdham_temple","Akshardham","Vaishali Nagar Jaipur"));
        temples.add(new TempleDomain("birla_mandir","Birla Mandir","JLN Marg,Jaipur"));
        temples.add(new TempleDomain("galtaji_temple","Galta Ji Temple(Monkey temple)","Transport Nagar"));
        temples.add(new TempleDomain("govind_devji_temple","Govind Dev Ji","Gangori Bazaar,Jaipur"));
        temples.add(new TempleDomain("isckon_temple","Isckon Temple","Patrkar Colony,Mansrover,Jaipur"));
        temples.add(new TempleDomain("moti_dungri","Moti Dungri ganesh Ji","JLN Marg,Jaipur"));
        temples.add(new TempleDomain("tarkeshwar_mahadev_temple","Tarkeshawar Mahadev","Chaura Rasta,Jaipur"));

        templeAdapter = new TempleAdapter(temples, this);
        templeListView.setAdapter(templeAdapter);

        templeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Handle item click here if needed
            }
        });
    }
}
