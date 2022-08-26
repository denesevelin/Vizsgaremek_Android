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

public class ProfilModositas extends Fragment {

    private Spinner SpinnerTelepulesProfilModositas;
    private Button buttonVisszaProfilModositas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profil_modositasa, container, false);

        SpinnerTelepulesProfilModositas = view.findViewById(R.id.SpinnerTelepulesProfilModositas);
        buttonVisszaProfilModositas = view.findViewById(R.id.buttonVisszaProfilModositas);

        telepulesekBetoltese();

        //Vissza a profilra
        buttonVisszaProfilModositas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Profil());
                fr.commit();
            }
        });

        return view;
    }

    public void telepulesekBetoltese(){
        String[] items = new String[]{"Első település", "Második település", "Harmadik település"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerTelepulesProfilModositas.setAdapter(adapter);
    }
}
