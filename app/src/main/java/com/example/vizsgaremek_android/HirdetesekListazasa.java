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

public class HirdetesekListazasa extends Fragment {

    private Button buttonTovabbHirdetesekListazasa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hirdetesek_listazasa, container, false);

        buttonTovabbHirdetesekListazasa = view.findViewById(R.id.buttonTovabbHirdetesekListazasa);

        //Konkrét hirdetésre
        buttonTovabbHirdetesekListazasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new KonkretHirdetes());
                fr.commit();
            }
        });

        return view;
    }
}
