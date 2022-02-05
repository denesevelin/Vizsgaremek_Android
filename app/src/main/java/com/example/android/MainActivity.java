package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonBejelentkezes, buttonRegisztracio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        buttonBejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBejelentkezesiFelulet();
            }
        });

        buttonRegisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisztraciosFelulet();
            }
        });
    }

    public void init(){
        buttonBejelentkezes = findViewById(R.id.buttonBejelentkezés);
        buttonRegisztracio = findViewById(R.id.buttonRegisztracio);
    }

    public void openBejelentkezesiFelulet(){
        Intent intent_bejelentkezes = new Intent(MainActivity.this, BejelentkezesiFelulet.class);
        startActivity(intent_bejelentkezes);
    }

    public void openRegisztraciosFelulet(){
        Intent intent_regisztracio = new Intent(MainActivity.this,RegisztraciosFelulet.class);
        startActivity(intent_regisztracio);
    }
}