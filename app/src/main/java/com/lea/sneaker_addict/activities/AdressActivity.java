package com.lea.sneaker_addict.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

public class AdressActivity extends AppCompatActivity {

    public EditText mEdit;
    public RadioButton rButton;
    public RadioGroup rAdressGroup;
    public Button button;
    public String str = "";
    TextView bddAdresse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adressforshipping);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginUserActivity.class));

        }

        rAdressGroup = (RadioGroup) findViewById(R.id.radiogroup);
        button = (Button) findViewById(R.id.button_changer_adresse);
        bddAdresse = (TextView) findViewById(R.id.bdd_adress);

        getUserAdress();
        bddAdresse.setText(SharedPrefManager.getInstance(this).getAdress());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rAdressGroup.getCheckedRadioButtonId();
                rButton = (RadioButton) findViewById(selectedId);
                if (rButton.getText().toString().equals("Adresse actuelle")) {
                    str = "2 Petre Melikishvili St.0162, Tsibili";
                    Intent intent = new Intent(AdressActivity.this, ConfirmActivity.class);
                    intent.putExtra("address_change", str);
                    startActivity(intent);
                }
                if (rButton.getText().toString().equals("Nouvelle adresse")) {
                    Intent intent = new Intent(AdressActivity.this, ConfirmActivity.class);
                    mEdit = (EditText) findViewById(R.id.edit_adress);
                    str = mEdit.getText().toString();
                    intent.putExtra("address_change", str);
                    startActivity(intent);
                }
            }
        });

        //*****BOTTOM NAVIGATION BAR*****//
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_produit);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menu_homepage:
                        startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.menu_produit:
                        return true;

                    case R.id.menu_profil:
                        startActivity(new Intent(getApplicationContext(), LoginUserActivity.class));
                        return true;
                }
                return false;
            }
        });
    }

    public void back(View view) {
        startActivity(new Intent(AdressActivity.this, ConfirmActivity.class));
    }

    public void getUserAdress() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_ADRESSE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonLoginObj = new JSONObject(response);

                    if (!jsonLoginObj.getBoolean("error")) {

                        SharedPrefManager.getInstance(getApplicationContext()).isAdress(jsonLoginObj.getString("adresse"));

                    } else {
                        Toast.makeText(getApplicationContext(), jsonLoginObj.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
        );
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}

