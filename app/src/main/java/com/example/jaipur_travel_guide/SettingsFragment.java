package com.example.jaipur_travel_guide;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.example.jaipur_travel_guide.Activities.RegistrationActivity;
import com.example.jaipur_travel_guide.Activities.LoginActivity;

public class SettingsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        // Your existing code ...
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);


        return rootView;
    }

}