package com.example.vizsgaremek_android;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Regisztracio extends Fragment {

    private Spinner SpinnerTelepulesRegisztracio;
    private Button buttonRegisztraciosFeluletRegisztracio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.regisztracio, container, false);

        SpinnerTelepulesRegisztracio = view.findViewById(R.id.SpinnerTelepulesRegisztracio);
        buttonRegisztraciosFeluletRegisztracio = view.findViewById(R.id.buttonRegisztraciosFeluletRegisztracio);

        telepulesekBetoltese();

        //Vissza a főoldalra
        buttonRegisztraciosFeluletRegisztracio.setOnClickListener(v -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Fooldal());
            fr.commit();
        });

        return view;
    }

        //Spinner tesztelése
        public void telepulesekBetoltese(){
        String[] items = new String[]{"Első település", "Második település", "Harmadik település"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerTelepulesRegisztracio.setAdapter(adapter);
        }
}
