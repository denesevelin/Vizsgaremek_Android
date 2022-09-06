package com.example.vizsgaremek_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Fooldal extends Fragment {

    private Button buttonBejelentkezésFooldal, buttonRegisztracioFooldal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fooldal, container, false);

        buttonBejelentkezésFooldal = view.findViewById(R.id.buttonBejelentkezésFooldal);
        buttonRegisztracioFooldal = view.findViewById(R.id.buttonRegisztracioFooldal);

        buttonBejelentkezésFooldal.setOnClickListener(v -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Bejelentkezes());
            fr.commit();
        });

        buttonRegisztracioFooldal.setOnClickListener(v -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Regisztracio());
            fr.commit();
        });

        return view;
    }
}
