package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class HirdetesekKeresese extends AppCompatActivity {

    private Spinner SpinnerKategoriaKeres, SpinnerTelepulesKeres;
    private Button buttonSzures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirdetesek_keresese);

        init();
        kategoriakBetoltese();
        telepulesekBetoltese();

        buttonSzures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_szures = new Intent(HirdetesekKeresese.this, HirdetesekListazasa.class);
                startActivity(intent_szures);
            }
        });
    }

    public void init(){
        SpinnerKategoriaKeres = findViewById(R.id.SpinnerKategoriaKeres);
        SpinnerTelepulesKeres = findViewById(R.id.SpinnerTelepulesKeres);
        buttonSzures = findViewById(R.id.buttonSzures);
    }

    public void kategoriakBetoltese(){
        String[] items = new String[]{"Kutyasétáltatás", "Vásárlás", "Takarítás", "Személyszállítás", "Idősgondozás", "Gyermekfelügyelet", "Szerelés", "Társalgás", "Korrepetálás", "Főzés", "Kertészkedés", "Egyéb"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerKategoriaKeres.setAdapter(adapter);
    }

    public void telepulesekBetoltese(){
        String[] items = new String[]{"Első", "Második", "..."};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerTelepulesKeres.setAdapter(adapter);
    }
}