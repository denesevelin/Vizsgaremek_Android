package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class BejelentkezesiFelulet extends AppCompatActivity {

    private Button buttonBejelentkezesBejelentkezes;
    private TextInputLayout textInputFelhasznalonevBejelentkezes, textInputJelszoBejelentkezes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bejelentkezesi_felulet);

        init();

        //Hirdetések keresése oldal megnyitása
        buttonBejelentkezesBejelentkezes.setOnClickListener(v -> {
            //Üres mezők kiszűrése
            if(textInputFelhasznalonevBejelentkezes.getEditText().getText().toString().isEmpty() || textInputJelszoBejelentkezes.getEditText().getText().toString().isEmpty()){
                Toast.makeText(this, "Nem lehet üres mező", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(BejelentkezesiFelulet.this, HirdetesekKeresese.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        buttonBejelentkezesBejelentkezes = findViewById(R.id.buttonBejelentkezesBejelentkezes);
        textInputFelhasznalonevBejelentkezes = findViewById(R.id.textInputFelhasznalonevBejelentkezes);
        textInputJelszoBejelentkezes = findViewById(R.id.textInputJelszoBejelentkezes);
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
        if(id == R.id.menuProfil){
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
        /*if(id == R.id.menuKijelentkezes){
            Intent i = new Intent(BejelentkezesiFelulet.this, Kijelentkezes.class);
            startActivity(i);
            finish();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}