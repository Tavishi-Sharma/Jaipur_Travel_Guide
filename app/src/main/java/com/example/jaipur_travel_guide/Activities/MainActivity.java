package com.example.jaipur_travel_guide.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jaipur_travel_guide.R;


public class MainActivity extends AppCompatActivity {

    // Define a constant for the location permission request
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout introBtn=findViewById(R.id.introbtn);
        introBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the location permission is granted
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // Permission is already granted, start the DetailActivity
                    startActivity(new Intent(MainActivity.this,DetailActivity.class));
                } else {
                    // Permission is not granted, request the permission
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            LOCATION_PERMISSION_REQUEST_CODE);
                }
            }
        });
    }

    // Override onRequestPermissionsResult to handle the permission request response
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted, start the DetailActivity
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            } else {
                // Location permission denied, show a message to the user
                Toast.makeText(this, "Location permission denied. Cannot proceed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
