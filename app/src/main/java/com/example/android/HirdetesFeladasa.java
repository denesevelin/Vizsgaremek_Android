package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

public class HirdetesFeladasa extends AppCompatActivity {

    private TimePicker TimePickerKezdoIdopt;
    private Spinner SpinnerKategoriaFelad, SpinnerTelepulesFelad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirdetes_feladasa);

        init();
        TimePickerKezdoIdopt.setIs24HourView(true);
        kategoriakBetoltese();
        telepulesekBetoltese();
    }

    public void init(){
        TimePickerKezdoIdopt = findViewById(R.id.TimePickerKezdoIdopt);
        SpinnerKategoriaFelad = findViewById(R.id.SpinnerKategoriaFelad);
        SpinnerTelepulesFelad = findViewById(R.id.SpinnerTelepulesFelad);
    }

    public void kategoriakBetoltese(){
        String[] items = new String[]{"Kutyasétáltatás", "Vásárlás", "Takarítás", "Személyszállítás", "Idősgondozás", "Gyermekfelügyelet", "Szerelés", "Társalgás", "Korrepetálás", "Főzés", "Kertészkedés", "Egyéb"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerKategoriaFelad.setAdapter(adapter);
    }

    public void telepulesekBetoltese(){
        String[] items = new String[]{"Első", "Második", "..."};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerTelepulesFelad.setAdapter(adapter);
    }
}