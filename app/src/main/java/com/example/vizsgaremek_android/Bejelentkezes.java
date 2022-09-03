package com.example.vizsgaremek_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Bejelentkezes extends Fragment {

    private Button buttonBejelentkezesBejelentkezes;
    private EditText editFelhasznalonevBejelentkezes, editJelszoBejelentkezes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bejelentkezes, container, false);

        buttonBejelentkezesBejelentkezes = view.findViewById(R.id.buttonBejelentkezesBejelentkezes);
        editFelhasznalonevBejelentkezes = view.findViewById(R.id.editFelhasznalonevBejelentkezes);
        editJelszoBejelentkezes = view.findViewById(R.id.editJelszoBejelentkezes);

        buttonBejelentkezesBejelentkezes.setOnClickListener(view1 -> {
            String felhnev = editFelhasznalonevBejelentkezes.getText().toString();
            String jelszo = editJelszoBejelentkezes.getText().toString();

            new Adatletolto().bejelentkezes(getContext(),felhnev, jelszo, Successful -> {
                if (Successful){
                    Toast.makeText(getActivity(), "Sikeres bejelentkezés!", Toast.LENGTH_SHORT).show();
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new HirdetesekKeresese());
                    fr.commit();
                }
                else {
                    Toast.makeText(getActivity(), "Sikertelen bejelentkezés!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return view;
    }
}
