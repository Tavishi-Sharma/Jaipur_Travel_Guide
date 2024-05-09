package com.example.jaipur_travel_guide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jaipur_travel_guide.Activities.LoginActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.ListenerRegistration;

public class ProfileFragment extends Fragment {

    private EditText usernameEditText;
    private TextView emailTxt;

    private static final String TAG = "ProfileFragment";

    private FirebaseFirestore db;
    private DocumentReference userDocRef;
    private ListenerRegistration userListener;
    private ImageView logoutBtn;
    private Button saveButton;


    private static final String ARG_USER_ID = "userId";

    private String userId;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String userId) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(ARG_USER_ID);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        usernameEditText = rootView.findViewById(R.id.usernameTxt);
        emailTxt = rootView.findViewById(R.id.emailTxt);
        saveButton = rootView.findViewById(R.id.savebutton);
        logoutBtn=rootView.findViewById(R.id.logoutbtn);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            if (email != null) {
                emailTxt.setText(email);
            }
        }
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call logout method
                logoutUser();
            }
        });


        db = FirebaseFirestore.getInstance();
        String userId = getCurrentUserId();
        if (userId != null) {
            userDocRef = db.collection("User").document(userId);

            userListener = userDocRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.w(TAG, "Listen failed", e);
                        return;
                    }

                    if (snapshot != null && snapshot.exists()) {
                        String username = snapshot.getString("Username");
                        String password = snapshot.getString("password");
                        GeoPoint location = snapshot.getGeoPoint("location");

                        usernameEditText.setText(username);

                    } else {
                        Log.d(TAG, "Current data: null");
                    }
                }
            });
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the username in Firestore
                updateUserProfile();
            }
        });

        return rootView;
    }
    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        // Redirect to login activity or any other appropriate action
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish(); // Close the current activity
    }

    private void updateUserProfile() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            String newUsername = usernameEditText.getText().toString().trim();
            String email = emailTxt.getText().toString().trim();

            if (!email.isEmpty()) {
                DocumentReference ur = db.collection("User").document(uid);
                ur.update("email", email);
            }


            if (!newUsername.isEmpty()) {
                DocumentReference userRef = db.collection("User").document(uid);

                userRef.update("Username", newUsername)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Username updated successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "Error updating username", e);
                                Toast.makeText(getActivity(), "Failed to update username", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(getActivity(), "Username cannot be empty", Toast.LENGTH_SHORT).show();
            }
        } else {
            // User is not signed in, handle this case accordingly
            Toast.makeText(getActivity(), "User is not signed in", Toast.LENGTH_SHORT).show();
        }

    }

    private String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        } else {
            return null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (userListener != null) {
            userListener.remove();
        }

    }
}


