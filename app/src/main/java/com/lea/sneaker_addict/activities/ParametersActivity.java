package com.lea.sneaker_addict.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lea.sneaker_addict.R;
import com.lea.sneaker_addict.bdd.Constants;
import com.lea.sneaker_addict.bdd.RequestHandler;
import com.lea.sneaker_addict.bdd.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

public class ParametersActivity extends AppCompatActivity  {



    private EditText editNom, editPrenom, editPseudo, editMail,editAdresse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginUserActivity.class));
        }

        editNom = (EditText) findViewById(R.id.edit_nom);
        editPrenom = (EditText) findViewById(R.id.edit_prenom);
        editPseudo = (EditText) findViewById(R.id.edit_pseudo);
        editMail = (EditText) findViewById(R.id.edit_mail);
        editAdresse = (EditText) findViewById(R.id.edit_adresse);

        editPseudo.setText(SharedPrefManager.getInstance(this).getPseudo());
        editMail.setText(SharedPrefManager.getInstance(this).getMail());
        editPrenom.setText(SharedPrefManager.getInstance(this).getSurnameUser());
        editNom.setText(SharedPrefManager.getInstance(this).getNameUser());
        editAdresse.setText(SharedPrefManager.getInstance(this).getAdresse());

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

    public void back(View view){
        startActivity(new Intent(ParametersActivity.this, ProfilActivity.class));
    }

}
