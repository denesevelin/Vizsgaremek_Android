package com.example.vizsgaremek_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Profil extends Fragment {

    private ImageView imageViewJelentkeztemProfil;
    private Button buttonProfilModositasProfil;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profil, container, false);

        imageViewJelentkeztemProfil = view.findViewById(R.id.imageViewJelentkeztemProfil);
        buttonProfilModositasProfil = view.findViewById(R.id.buttonProfilModositasProfil);

        //Konkrét hirdetés megtekintése
        imageViewJelentkeztemProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new KonkretHirdetes());
                fr.commit();
            }
        });

        //Profil módosítás megnyitása
        buttonProfilModositasProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ProfilModositas());
                fr.commit();
            }
        });

        return view;
    }
}
