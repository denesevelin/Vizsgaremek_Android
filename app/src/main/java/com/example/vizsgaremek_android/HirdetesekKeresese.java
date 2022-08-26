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

public class HirdetesekKeresese extends Fragment {

    private Spinner SpinnerKategoriaKeresHirdetesekKeresese, SpinnerTelepulesKeresHirdetesekKeresese;
    private Button buttonSzuresHirdetesekKeresese;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hirdetesek_keresese, container, false);

        SpinnerKategoriaKeresHirdetesekKeresese = view.findViewById(R.id.SpinnerKategoriaKeresHirdetesekKeresese);
        SpinnerTelepulesKeresHirdetesekKeresese = view.findViewById(R.id.SpinnerTelepulesKeresHirdetesekKeresese);
        buttonSzuresHirdetesekKeresese = view.findViewById(R.id.buttonSzuresHirdetesekKeresese);

        kategoriakBetoltese();
        telepulesekBetoltese();

        //Hirdetések kilistázása
        buttonSzuresHirdetesekKeresese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new HirdetesekListazasa());
                fr.commit();
            }
        });

        return view;
    }

    public void kategoriakBetoltese(){
        String[] items = new String[]{"Kutyasétáltatás", "Vásárlás", "Takarítás", "Személyszállítás", "Idősgondozás", "Gyermekfelügyelet", "Szerelés", "Társalgás", "Korrepetálás", "Főzés", "Kertészkedés", "Egyéb"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerKategoriaKeresHirdetesekKeresese.setAdapter(adapter);
    }

    public void telepulesekBetoltese(){
        String[] items = new String[]{"Első település", "Második település", "Harmadik település"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerTelepulesKeresHirdetesekKeresese.setAdapter(adapter);
    }


}
