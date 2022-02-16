package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HirdetesFeladasa extends AppCompatActivity {

    EditText editTextKezdoIdopt, editTextZaroIdopt;
    private Spinner spinnerKategoriaFelad, spinnerTelepulesFelad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirdetes_feladasa);

        init();
        kategoriakBetoltese();
        telepulesekBetoltese();

        editTextKezdoIdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kezdoidopont(editTextKezdoIdopt);
            }
        });

        editTextZaroIdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zaroidopont(editTextZaroIdopt);
            }
        });
    }



    public void init(){
        editTextKezdoIdopt = findViewById(R.id.editTextKezdoIdopt);
        editTextZaroIdopt = findViewById(R.id.editTextZaroIdopt);
        spinnerKategoriaFelad = findViewById(R.id.spinnerKategoriaFelad);
        spinnerTelepulesFelad = findViewById(R.id.spinnerTelepulesFelad);
    }

    private void kezdoidopont(EditText editTextKezdoIdopt) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                        editTextKezdoIdopt.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(HirdetesFeladasa.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(HirdetesFeladasa.this,dateSetListener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void zaroidopont(EditText editTextZaroIdopt) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                        editTextZaroIdopt.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(HirdetesFeladasa.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(HirdetesFeladasa.this,dateSetListener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void kategoriakBetoltese(){
        String[] items = new String[]{"Kutyasétáltatás", "Vásárlás", "Takarítás", "Személyszállítás", "Idősgondozás", "Gyermekfelügyelet", "Szerelés", "Társalgás", "Korrepetálás", "Főzés", "Kertészkedés", "Egyéb"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerKategoriaFelad.setAdapter(adapter);
    }

    public void telepulesekBetoltese(){
        String[] items = new String[]{"Első", "Második", "..."};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerTelepulesFelad.setAdapter(adapter);
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