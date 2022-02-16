package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Profil extends AppCompatActivity {

    private ImageButton imageButtonJelentkeztem1;
    private TextView textViewJelentkeztem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        init();

        imageButtonJelentkeztem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profil.this, KonkretHirdetes.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        imageButtonJelentkeztem1 = findViewById(R.id.imageButtonJelentkeztem1);

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
            Intent i = new Intent(Profil.this, MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuBejelentkezes){
            Intent i = new Intent(Profil.this, BejelentkezesiFelulet.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuRegisztracio){
            Intent i = new Intent(Profil.this, RegisztraciosFelulet.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuHirdetesekKeres){
            Intent i = new Intent(Profil.this, HirdetesekKeresese.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuHirdetesFelad){
            Intent i = new Intent(Profil.this, HirdetesFeladasa.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuProfil){
            Intent i = new Intent(Profil.this, Profil.class);
            startActivity(i);
            finish();
            return true;
        }
        /*if(id == R.id.menuRanglista){
            Intent i = new Intent(Profil.this, Ranglista.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id == R.id.menuKijelentkezes){
            Intent i = new Intent(Profil.this, Kijelentkezes.class);
            startActivity(i);
            finish();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}