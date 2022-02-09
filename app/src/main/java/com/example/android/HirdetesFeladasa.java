package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuFooldal){
            Intent i = new Intent(HirdetesFeladasa.this, MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuBejelentkezes){
            Intent i = new Intent(HirdetesFeladasa.this, BejelentkezesiFelulet.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuRegisztracio){
            Intent i = new Intent(HirdetesFeladasa.this, RegisztraciosFelulet.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuHirdetesekKeres){
            Intent i = new Intent(HirdetesFeladasa.this, HirdetesekKeresese.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuHirdetesFelad){
            Intent i = new Intent(HirdetesFeladasa.this, HirdetesFeladasa.class);
            startActivity(i);
            finish();
            return true;
        }
        /*if(id == R.id.menuProfil){
            Intent i = new Intent(HirdetesFeladasa.this, Profil.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuRanglista){
            Intent i = new Intent(HirdetesFeladasa.this, Ranglista.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuKijelentkezes){
            Intent i = new Intent(HirdetesFeladasa.this, Kijelentkezes.class);
            startActivity(i);
            finish();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}