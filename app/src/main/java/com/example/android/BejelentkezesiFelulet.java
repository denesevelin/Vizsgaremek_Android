package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

}