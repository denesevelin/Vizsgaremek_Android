package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegisztraciosFelulet extends AppCompatActivity {

    private Spinner SpinnerTelepules;
    private Button buttonRegisztraciosFeluletRegisztracio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisztracios_felulet);

        init();

        //Spinner tesztelése
        String[] items = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerTelepules.setAdapter(adapter);

        buttonRegisztraciosFeluletRegisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_regisztracio = new Intent(RegisztraciosFelulet.this, MainActivity.class);
                startActivity(intent_regisztracio);
            }
        });
    }

    public void init(){
        SpinnerTelepules = findViewById(R.id.SpinnerTelepules);
        buttonRegisztraciosFeluletRegisztracio = findViewById(R.id.buttonRegisztraciosFeluletRegisztracio);
    }
}