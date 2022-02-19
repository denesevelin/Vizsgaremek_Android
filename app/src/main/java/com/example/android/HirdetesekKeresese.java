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

public class HirdetesekKeresese extends AppCompatActivity {

    private Spinner SpinnerKategoriaKeresHirdetesekKeresese, SpinnerTelepulesKeresHirdetesekKeresese;
    private Button buttonSzuresHirdetesekKeresese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirdetesek_keresese);

        init();
        kategoriakBetoltese();
        telepulesekBetoltese();

        //Hirdetések kilistázása
        buttonSzuresHirdetesekKeresese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_szures = new Intent(HirdetesekKeresese.this, HirdetesekListazasa.class);
                startActivity(intent_szures);
            }
        });
    }

    public void init(){
        SpinnerKategoriaKeresHirdetesekKeresese = findViewById(R.id.SpinnerKategoriaKeresHirdetesekKeresese);
        SpinnerTelepulesKeresHirdetesekKeresese = findViewById(R.id.SpinnerTelepulesKeresHirdetesekKeresese);
        buttonSzuresHirdetesekKeresese = findViewById(R.id.buttonSzuresHirdetesekKeresese);
    }

    public void kategoriakBetoltese(){
        String[] items = new String[]{"Kutyasétáltatás", "Vásárlás", "Takarítás", "Személyszállítás", "Idősgondozás", "Gyermekfelügyelet", "Szerelés", "Társalgás", "Korrepetálás", "Főzés", "Kertészkedés", "Egyéb"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerKategoriaKeresHirdetesekKeresese.setAdapter(adapter);
    }

    public void telepulesekBetoltese(){
        String[] items = new String[]{"Első település", "Második település", "Harmadik település"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerTelepulesKeresHirdetesekKeresese.setAdapter(adapter);
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
            Intent i = new Intent(HirdetesekKeresese.this, MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuBejelentkezes){
            Intent i = new Intent(HirdetesekKeresese.this, BejelentkezesiFelulet.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuRegisztracio){
            Intent i = new Intent(HirdetesekKeresese.this, RegisztraciosFelulet.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuHirdetesekKeres){
            Intent i = new Intent(HirdetesekKeresese.this, HirdetesekKeresese.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuHirdetesFelad){
            Intent i = new Intent(HirdetesekKeresese.this, HirdetesFeladasa.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuProfil){
            Intent i = new Intent(HirdetesekKeresese.this, Profil.class);
            startActivity(i);
            finish();
            return true;
        }
        /*if(id == R.id.menuRanglista){
            Intent i = new Intent(HirdetesekKeresese.this, Ranglista.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuKijelentkezes){
            Intent i = new Intent(HirdetesekKeresese.this, Kijelentkezes.class);
            startActivity(i);
            finish();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

}