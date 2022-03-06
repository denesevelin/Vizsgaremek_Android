package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisztraciosFelulet extends AppCompatActivity {

    private Spinner SpinnerTelepulesRegisztracio;
    private Button buttonRegisztraciosFeluletRegisztracio;
    private TextInputLayout textInputNevRegisztracio, textInputFelhasznalonevRegisztracio, textInputSzulDatumRegisztracio, textInputTelszamRegisztracio,
            textInputEmailRegisztracio, textInputCimRegisztracio, textInputOkmanyszamRegisztracio, textInputJelszoRegisztracio, textInputJelszoIsmetRegisztracio;
    private MaterialButton buttonOkmanykepRegisztracio, buttonProfilkepRegisztracio;
    private TextView textViewOkmanykepFileNeveRegisztracio, textViewProfilkepFileNeveRegisztracio;
    private CheckBox checkBoxNyilatkozatRegisztracio;


    String regex_nev = "^([A-ZÁÉÚŐÓÜÖÍ]([a-záéúőóüöí.-]+\\s?)){2,}$";
    String regex_felhnev = "^[a-zA-Z0-9\\S]{1,100}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisztracios_felulet);

        init();
        telepulesekBetoltese();



        //Vissza a főoldalra
        buttonRegisztraciosFeluletRegisztracio.setOnClickListener(v -> {
            //Frontend validálás
            if(textInputNevRegisztracio.getEditText().getText().toString().isEmpty() || textInputFelhasznalonevRegisztracio.getEditText().getText().toString().isEmpty() || textInputSzulDatumRegisztracio.getEditText().getText().toString().isEmpty() || textInputTelszamRegisztracio.getEditText().getText().toString().isEmpty() || textInputTelszamRegisztracio.getEditText().getText().toString().isEmpty() || textInputEmailRegisztracio.getEditText().getText().toString().isEmpty() || textInputCimRegisztracio.getEditText().getText().toString().isEmpty() || textInputOkmanyszamRegisztracio.getEditText().getText().toString().isEmpty() || textInputJelszoRegisztracio.getEditText().getText().toString().isEmpty() || textInputJelszoIsmetRegisztracio.getEditText().getText().toString().isEmpty()){
                Toast.makeText(this, "Nem lehet üres mező", Toast.LENGTH_SHORT).show();
            } else if (checkBoxNyilatkozatRegisztracio.isChecked() == false){
                Toast.makeText(this, "Nem fogadta el a szerződési feltételeket", Toast.LENGTH_SHORT).show();

            } else if(!textInputNevRegisztracio.getEditText().getText().toString().trim().matches(regex_nev)){
                Toast.makeText(this, "Nem megfelelő a név formátuma. Próbálja így: Kis Béla", Toast.LENGTH_SHORT).show();
            } else  if(!textInputFelhasznalonevRegisztracio.getEditText().getText().toString().matches(regex_felhnev)){
                Toast.makeText(this, "A felhasználónév nem tartalmazhat space-t. Próbálja így: nagy.anna222", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(RegisztraciosFelulet.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }

    public void init(){
        SpinnerTelepulesRegisztracio = findViewById(R.id.SpinnerTelepulesRegisztracio);
        buttonRegisztraciosFeluletRegisztracio = findViewById(R.id.buttonRegisztraciosFeluletRegisztracio);
        textInputNevRegisztracio = findViewById(R.id.textInputNevRegisztracio);
        textInputFelhasznalonevRegisztracio = findViewById(R.id.textInputFelhasznalonevRegisztracio);
        textInputSzulDatumRegisztracio = findViewById(R.id.textInputSzulDatumRegisztracio);
        textInputTelszamRegisztracio = findViewById(R.id.textInputTelszamRegisztracio);
        textInputEmailRegisztracio = findViewById(R.id.textInputEmailRegisztracio);
        textInputCimRegisztracio = findViewById(R.id.textInputCimRegisztracio);
        textInputOkmanyszamRegisztracio = findViewById(R.id.textInputOkmanyszamRegisztracio);
        textInputJelszoRegisztracio = findViewById(R.id.textInputJelszoRegisztracio);
        textInputJelszoIsmetRegisztracio = findViewById(R.id.textInputJelszoIsmetRegisztracio);
        checkBoxNyilatkozatRegisztracio = findViewById(R.id.checkBoxNyilatkozatRegisztracio);
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
        if(id == R.id.menuRanglista){
            Intent i = new Intent(RegisztraciosFelulet.this, Ranglista.class);
            startActivity(i);
            finish();
            return true;
        }
        /*if(id == R.id.menuKijelentkezes){
            Intent i = new Intent(RegisztraciosFelulet.this, Kijelentkezes.class);
            startActivity(i);
            finish();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}