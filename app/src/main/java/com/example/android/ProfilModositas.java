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

        //Spinner tesztelése
        String[] items = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerTelepulesProfilModositas.setAdapter(adapter);

        buttonVisszaProfilModositas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_regisztracio = new Intent(ProfilModositas.this, Profil.class);
                startActivity(intent_regisztracio);
            }
        });
    }

    public void init(){
        spinnerTelepulesProfilModositas = findViewById(R.id.spinnerTelepulesProfilModositas);
        buttonVisszaProfilModositas = findViewById(R.id.buttonVisszaProfilModositas);
    }
}