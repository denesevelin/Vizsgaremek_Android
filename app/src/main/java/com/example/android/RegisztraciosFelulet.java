package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegisztraciosFelulet extends AppCompatActivity {

    private Spinner SpinnerTelepulesRegisztracio;
    private Button buttonRegisztraciosFeluletRegisztracio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisztracios_felulet);

        init();
        telepulesekBetoltese();


        //Vissza a főoldalra
        buttonRegisztraciosFeluletRegisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisztraciosFelulet.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        SpinnerTelepulesRegisztracio = findViewById(R.id.SpinnerTelepulesRegisztracio);
        buttonRegisztraciosFeluletRegisztracio = findViewById(R.id.buttonRegisztraciosFeluletRegisztracio);
    }

    //Spinner tesztelése
    public void telepulesekBetoltese(){
        String[] items = new String[]{"Első település", "Második település", "Harmadik település"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerTelepulesRegisztracio.setAdapter(adapter);
    }

    //Menürendszer
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuFooldal){
            Intent i = new Intent(RegisztraciosFelulet.this, MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuBejelentkezes){
            Intent i = new Intent(RegisztraciosFelulet.this, BejelentkezesiFelulet.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuRegisztracio){
            Intent i = new Intent(RegisztraciosFelulet.this, RegisztraciosFelulet.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuHirdetesekKeres){
            Intent i = new Intent(RegisztraciosFelulet.this, HirdetesekKeresese.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuHirdetesFelad){
            Intent i = new Intent(RegisztraciosFelulet.this, HirdetesFeladasa.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuProfil){
            Intent i = new Intent(RegisztraciosFelulet.this, Profil.class);
            startActivity(i);
            finish();
            return true;
        }
        /*if(id == R.id.menuRanglista){
            Intent i = new Intent(RegisztraciosFelulet.this, Ranglista.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuKijelentkezes){
            Intent i = new Intent(RegisztraciosFelulet.this, Kijelentkezes.class);
            startActivity(i);
            finish();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}