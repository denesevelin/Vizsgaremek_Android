package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ProfilModositas extends AppCompatActivity {

    private Spinner spinnerTelepulesProfilModositas;
    private Button buttonVisszaProfilModositas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_modositas);

        init();
        telepulesekBetoltese();

        //Vissza a profilra
        buttonVisszaProfilModositas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilModositas.this, Profil.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        spinnerTelepulesProfilModositas = findViewById(R.id.spinnerTelepulesProfilModositas);
        buttonVisszaProfilModositas = findViewById(R.id.buttonVisszaProfilModositas);
    }

    //Spinner tesztelése
    public void telepulesekBetoltese(){
        String[] items = new String[]{"Első település", "Második település", "Harmadik település"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerTelepulesProfilModositas.setAdapter(adapter);
    }
}