package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BejelentkezesiFelulet extends AppCompatActivity {

    private Button buttonBejelentkezesiFeluletBejelentkezes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bejelentkezesi_felulet);

        init();

        buttonBejelentkezesiFeluletBejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHirdetesekKereseseFelulet();
            }
        });
    }

    public void init(){
        buttonBejelentkezesiFeluletBejelentkezes = findViewById(R.id.buttonBejelentkezesiFeluletBejelentkezes);
    }
    public void openHirdetesekKereseseFelulet(){
        Intent intent_hirdetesekKereseseFeluletre = new Intent(BejelentkezesiFelulet.this, HirdetesekKeresese.class);
        startActivity(intent_hirdetesekKereseseFeluletre);
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
            Intent i = new Intent(BejelentkezesiFelulet.this, MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuBejelentkezes){
            Intent i = new Intent(BejelentkezesiFelulet.this, BejelentkezesiFelulet.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuRegisztracio){
            Intent i = new Intent(BejelentkezesiFelulet.this, RegisztraciosFelulet.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuHirdetesekKeres){
            Intent i = new Intent(BejelentkezesiFelulet.this, HirdetesekKeresese.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuHirdetesFelad){
            Intent i = new Intent(BejelentkezesiFelulet.this, HirdetesFeladasa.class);
            startActivity(i);
            finish();
            return true;
        }
        /*if(id == R.id.menuProfil){
            Intent i = new Intent(BejelentkezesiFelulet.this, Profil.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuRanglista){
            Intent i = new Intent(BejelentkezesiFelulet.this, Ranglista.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuKijelentkezes){
            Intent i = new Intent(BejelentkezesiFelulet.this, Kijelentkezes.class);
            startActivity(i);
            finish();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}