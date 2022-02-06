package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisztraciosFelulet extends AppCompatActivity {

    private Spinner SpinnerTelepules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisztracios_felulet);

        init();

        //Spinner tesztelése
        String[] items = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerTelepules.setAdapter(adapter);
    }

    public void init(){
        SpinnerTelepules = findViewById(R.id.SpinnerTelepules);
    }
}