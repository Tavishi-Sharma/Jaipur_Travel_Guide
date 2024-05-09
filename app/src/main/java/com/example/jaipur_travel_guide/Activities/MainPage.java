package com.example.jaipur_travel_guide.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jaipur_travel_guide.Adapters.CategoryAdapter;
import com.example.jaipur_travel_guide.Adapters.PopularAdapter;
import com.example.jaipur_travel_guide.Domains.CategoryDomain;
import com.example.jaipur_travel_guide.Domains.PopularDomain;
import com.example.jaipur_travel_guide.FavoriteFragment;
import com.example.jaipur_travel_guide.HomeFragment;
import com.example.jaipur_travel_guide.ProfileFragment;
import com.example.jaipur_travel_guide.R;
import com.example.jaipur_travel_guide.SettingsFragment;
import com.example.jaipur_travel_guide.databinding.ActivityMainPageBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular, adapterCat,adapterNear;
    private RecyclerView recyclerViewPopular, recyclerViewCategory,recyclerViewNear;

    ActivityMainPageBinding binding;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView userName;
    TextView seeBtn;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = binding.sideMenu;
        navigationView = binding.navigationview;
        bottomNavigationView = binding.bottomNavigationView;
        userName = findViewById(R.id.userTxt);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        toolbar = findViewById(R.id.toolbar);
        seeBtn=findViewById(R.id.seeallBtn);
        seeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this,TempleActivity.class);
                startActivity(intent);
            }
        });
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            fetchUsernameFromDatabase();
        }

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        initRecyclerView();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.event) {
                    startActivity(new Intent(MainPage.this,EventActivity.class));
                    //Toast.makeText(MainPage.this, "event selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.shopping) {
                    startActivity(new Intent(MainPage.this, MainActivity.class));
                    return true;
                }

                drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer after handling the click
                return true;
            }
        });

        String userId = getIntent().getStringExtra("userId");
        if (userId != null) {
            ProfileFragment profileFragment = ProfileFragment.newInstance(userId);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, profileFragment)
                    .commit();
        }


        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemReselectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.profile) {
                replaceFragment(new ProfileFragment());
            } else if (item.getItemId() == R.id.shop) {
                startActivity(new Intent(MainPage.this,ShoppingActivity.class));
            } else if (item.getItemId() == R.id.seevent) {
                startActivity(new Intent(MainPage.this,EventActivity.class));
            }
        });
        fetchUsernameFromDatabase();


    }

    private void fetchUsernameFromDatabase() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // Get the user's document reference from Firestore
            DocumentReference userDocRef = FirebaseFirestore.getInstance()
                    .collection("User")
                    .document(currentUser.getUid());

            // Fetch the username from Firestore
            userDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        // Get the username from the document snapshot
                        String username = documentSnapshot.getString("Username");
                        // Update the TextView with the fetched username
                        userName.setText(username);
                    }
                }
            });
        }
    }


    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("City Palace","Gangori Bazaar,JDA Market,Pink City","The City Palace in Jaipur, India, stands as a majestic testament to the city's rich history and vibrant culture. Built in the 18th century by Maharaja Sawai Jai Singh II, it blends Mughal and Rajput architectural styles seamlessly. This sprawling complex comprises stunning courtyards, intricately designed gates, and ornate palaces like Chandra Mahal and Mubarak Mahal. Lavish gardens, museums showcasing royal artifacts, and mesmerizing views of Jaipur's bustling streets add to its allure. Each corner whispers tales of royal grandeur, making it a captivating destination for history enthusiasts and architectural aficionados, preserving the legacy of Rajasthan's royal heritage.","city_palace","300(for Indians)"));
        items.add(new PopularDomain("Jal Mahal","near Amer Fort","Jal Mahal, translating to \"Water Palace,\" is a mesmerizing architectural gem nestled in the midst of Man Sagar Lake in Jaipur, India. Built in the 18th century by Maharaja Madho Singh I, this picturesque palace boasts a unique blend of Rajput and Mughal architectural styles. Its lower floors are submerged in the lake's waters, giving the illusion of floating serenely on its surface. The palace's five stories showcase intricate details and ornate designs, making it a captivating sight for visitors. Surrounded by the Aravalli hills, Jal Mahal offers a tranquil retreat amidst the bustling city, reflecting Jaipur's rich cultural heritage.","jal_mahal","Free"));
        items.add(new PopularDomain("Nargarh Fort", "Jaipur", "Perched atop the Aravalli hills, Nahargarh Fort in Jaipur, India, offers a panoramic view of the Pink City. Built in the 18th century by Maharaja Sawai Jai Singh II, it was primarily a defense fort protecting Jaipur. Nahargarh, with its intricate architecture and rich history, now serves as a popular tourist destination and a venue for cultural events.",  "nahargarh_fort", "50(for Indians) \n 200(for Foreigners)" ));
        items.add(new PopularDomain("Jaigarh Fort", "jaipur", "Known as the Fort of Victory, Jaigarh Fort overlooks Amer Fort and the Maota Lake. Built to protect Amer and its palaces, it houses the world's largest cannon on wheels, Jaivana. With its formidable walls, extensive network of underground passages, and stunning views, Jaigarh Fort captivates visitors with its military prowess and historical significance.",  "jai_garh",  "100(for Indians) \n 200(for Foreigners)"));
        items.add(new PopularDomain("Amer Fort", "Jaipur", "A majestic blend of Hindu and Mughal architecture, Amer Fort stands as a testament to Jaipur's regal past. Constructed of red sandstone and marble, it features ornate palaces, intricate carvings, and sprawling courtyards. Located on a hilltop, visitors can ascend on elephant back or by foot to explore its grandeur.",  "amer_city",  "100(for Indians) \n 200(for Foreigners)"));
        items.add(new PopularDomain("Hawa Mahal", "Jaipur", "Translating to \"Palace of Winds,\" Hawa Mahal is an architectural marvel in the heart of Jaipur. Built in 1799 by Maharaja Sawai Pratap Singh, it features a unique façade with 953 intricately carved windows, allowing royal women to observe street festivities without being seen. This five-story palace, constructed of pink sandstone, is a symbol of Jaipur's rich heritage and artistic finesse. Today, it stands as an iconic landmark, drawing tourists from around the world to admire its beauty and historical significance.", "hawa_mahal", "50(for Indians) \n 200(for Foreigners)"));
        recyclerViewPopular = findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);


        ArrayList<CategoryDomain> cats = new ArrayList<>();
        cats.add(new CategoryDomain("Parks", "parkicon"));
        cats.add(new CategoryDomain("Market","marketicon"));
        cats.add(new CategoryDomain("Heritage","heritageicon"));
        cats.add(new CategoryDomain("Temples","templeicon"));
        cats.add(new CategoryDomain("Cafes","cafe"));


        recyclerViewCategory = findViewById(R.id.view_cat);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterCat = new CategoryAdapter(cats,MainPage.this);
        recyclerViewCategory.setAdapter(adapterCat);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}