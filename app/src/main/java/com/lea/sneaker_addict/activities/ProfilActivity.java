package com.lea.sneaker_addict.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lea.sneaker_addict.R;
import com.lea.sneaker_addict.bdd.SharedPrefManager;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pseudoTextView;
    private Button buttonDeco, buttonParams, buttonAddCrea;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilpage);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginUserActivity.class));
        }
        pseudoTextView = (TextView) findViewById(R.id.profil_username);
        pseudoTextView.setText(SharedPrefManager.getInstance(this).getPseudo());

        buttonDeco = (Button) findViewById(R.id.btn_deco);
        buttonParams = (Button) findViewById(R.id.btn_param);
        //buttonAddCrea = (Button) findViewById(R.id.btn_add_crea);

        buttonParams.setOnClickListener(this);
        //buttonAddCrea.setOnClickListener(this);
        buttonDeco.setOnClickListener(this);

        //****BOTTOM NAV*****//
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_profil);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.menu_homepage :
                        startActivity(new Intent(getApplicationContext(),HomePageActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.menu_produit:
                        startActivity(new Intent(getApplicationContext(),AllProductsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.menu_profil:
                        return true;

                }
                return false;
            }
        });


    }


    @Override
    public void onClick(View view) {
        if (view == buttonParams) {
            Log.i("test", "parameters");
            startActivity(new Intent(this, ParametersActivity.class));
        }
        /*if (view == buttonAddCrea) {
            Log.i("test", "addcrea");
            startActivity(new Intent(this, AddProductActivity.class));
        }*/
        if (view == buttonDeco) {
            SharedPrefManager.getInstance(this).logout();


        }
    }

}



