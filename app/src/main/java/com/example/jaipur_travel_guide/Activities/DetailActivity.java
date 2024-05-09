package com.example.jaipur_travel_guide.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jaipur_travel_guide.R;
import com.example.jaipur_travel_guide.Domains.PopularDomain;
import com.example.jaipur_travel_guide.Domains.TempleDomain;

public class DetailActivity extends AppCompatActivity {
    private TextView directionBtn,placename,locationTxt,price,description;
    private ImageView backBtn,Pimage;
    private PopularDomain item;
    private TempleDomain itemT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initview();
        setVariable();

    }

    private void setVariable() {
        item=(PopularDomain) getIntent().getSerializableExtra("object");
        placename.setText(item.getTitle());
        locationTxt.setText(item.getLocation());
        description.setText(item.getDescription());
        price.setText(item.getPrice());
        int drawableResourceId=getResources().getIdentifier(item.getPic(),"drawable",getPackageName());
        Glide.with(this).load(drawableResourceId).into(Pimage);

        directionBtn.setOnClickListener(v -> {
            String destination = placename.getText().toString();
            openMaps(destination);
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this,MainPage.class));
            }
        });
    }

    private void initview() {
        placename=findViewById(R.id.placename);
        locationTxt=findViewById(R.id.locationTxt);
        price=findViewById(R.id.price);
        Pimage=findViewById(R.id.picImage);
        description=findViewById(R.id.des);
        directionBtn=findViewById(R.id.directionBtn);
        backBtn=findViewById(R.id.dback);

    }

    private void openMaps(String destination) {
        Toast.makeText(this, "map opened", Toast.LENGTH_SHORT).show();
        destination=destination+",Jaipur";
        destination = destination.trim();

        // Replace spaces with plus signs
        destination = destination.replace(" ", "+");

        // Encode the destination string
        String encodedDestination = Uri.encode(destination);


        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+encodedDestination);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}