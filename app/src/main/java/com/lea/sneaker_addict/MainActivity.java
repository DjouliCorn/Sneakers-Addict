package com.lea.sneaker_addict;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView artistesRecycler;
    RecyclerView.Adapter adapter;

    RecyclerView basketRecycler;
    RecyclerView.Adapter adapterBasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_homepage);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.menu_homepage :
                        return true;

                    case R.id.menu_produit:
                        startActivity(new Intent(getApplicationContext(),RecyclerViewProduits.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.menu_profil:
                        startActivity(new Intent(getApplicationContext(),ActivityProfilMain.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });

        artistesRecycler = findViewById(R.id.recycler_slider_artiste);
        mArtistesRecycler();

        basketRecycler = findViewById(R.id.recycler_produits_baskets);
        mBasketRecycler();


    }

    //method to hook features in the recycler
    private void mArtistesRecycler(){
        artistesRecycler.setHasFixedSize(true);
        artistesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<HelperSliderArtiste> helperSliderArtistesLocation = new ArrayList<>();
        helperSliderArtistesLocation.add(new HelperSliderArtiste(R.drawable.artiste1, "SneakersAddict"));
        helperSliderArtistesLocation.add(new HelperSliderArtiste(R.drawable.artiste2, "GirlWithShoes"));
        helperSliderArtistesLocation.add(new HelperSliderArtiste(R.drawable.artiste3, "Tulip"));

        adapter = new AdapterSliderArtiste(helperSliderArtistesLocation);
        artistesRecycler.setAdapter(adapter);
    }

    private void mBasketRecycler(){
        basketRecycler.setHasFixedSize(true);
        basketRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<HelperSliderBasket> helperSliderBaskets = new ArrayList<>();
        helperSliderBaskets.add(new HelperSliderBasket(R.drawable.accueil_basket1, "Shoes in color", "120€"));
        helperSliderBaskets.add(new HelperSliderBasket(R.drawable.accueil_basket2, "Shoes in color", "120€"));
        helperSliderBaskets.add(new HelperSliderBasket(R.drawable.accueil_basket3, "Shoes in color", "120€"));
        helperSliderBaskets.add(new HelperSliderBasket(R.drawable.accueil_basket4, "Shoes in color", "120€"));

        adapterBasket = new AdapterSliderBasket(helperSliderBaskets);
        basketRecycler.setAdapter(adapterBasket);
    }
}