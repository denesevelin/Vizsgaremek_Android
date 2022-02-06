package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HirdetesekKeresese extends AppCompatActivity {

    private Spinner SpinnerKategoriaKeres, SpinnerTelepulesKeres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirdetesek_keresese);

        init();
        kategoriakBetoltese();
        telepulesekBetoltese();
    }

    public void init(){
        SpinnerKategoriaKeres = findViewById(R.id.SpinnerKategoriaKeres);
        SpinnerTelepulesKeres = findViewById(R.id.SpinnerTelepulesKeres);
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