package com.example.vizsgaremek_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
        buttonRegisztraciosFeluletRegisztracio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fooldal());
                fr.commit();
            }
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
