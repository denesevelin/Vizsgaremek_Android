package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HirdetesekListazasa extends AppCompatActivity {

    //teszt a hirdetések feladása miatt
    private Button buttonTovabbPelda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirdetesek_listazasa);

        //teszt
        buttonTovabbPelda = findViewById(R.id.buttonTovabbPelda);
        buttonTovabbPelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_szures = new Intent(HirdetesekListazasa.this, HirdetesFeladasa.class);
                startActivity(intent_szures);
            }
        });
    }
}