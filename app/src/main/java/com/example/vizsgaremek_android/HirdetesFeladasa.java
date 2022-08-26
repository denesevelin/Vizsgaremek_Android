package com.example.vizsgaremek_android;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HirdetesFeladasa extends Fragment {

    private EditText editTextKezdoIdoptHirdetesFeladasa, editTextZaroIdoptHirdetesFeladasa;
    private Spinner spinnerKategoriaHirdetesFeladasa, spinnerTelepulesHirdetesFeladasa;
    private Button buttonMentesHirdetesFeladasa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hirdetes_feladasa, container, false);

        editTextKezdoIdoptHirdetesFeladasa = view.findViewById(R.id.editTextKezdoIdoptHirdetesFeladasa);
        editTextZaroIdoptHirdetesFeladasa = view.findViewById(R.id.editTextZaroIdoptHirdetesFeladasa);
        spinnerKategoriaHirdetesFeladasa = view.findViewById(R.id.spinnerKategoriaHirdetesFeladasa);
        spinnerTelepulesHirdetesFeladasa = view.findViewById(R.id.spinnerTelepulesHirdetesFeladasa);
        buttonMentesHirdetesFeladasa = view.findViewById(R.id.buttonMentesHirdetesFeladasa);

        kategoriakBetoltese();
        telepulesekBetoltese();

        editTextKezdoIdoptHirdetesFeladasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kezdoidopont(editTextKezdoIdoptHirdetesFeladasa);
            }
        });

        editTextZaroIdoptHirdetesFeladasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zaroidopont(editTextZaroIdoptHirdetesFeladasa);
            }
        });

        //Vissza a főoldalra
        buttonMentesHirdetesFeladasa.setOnClickListener(v -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Fooldal());
            fr.commit();
        });

        return view;
    }

    public void kategoriakBetoltese(){
        String[] items = new String[]{"Kutyasétáltatás", "Vásárlás", "Takarítás", "Személyszállítás", "Idősgondozás", "Gyermekfelügyelet", "Szerelés", "Társalgás", "Korrepetálás", "Főzés", "Kertészkedés", "Egyéb"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        spinnerKategoriaHirdetesFeladasa.setAdapter(adapter);
    }

    public void telepulesekBetoltese(){
        String[] items = new String[]{"Első település", "Második település", "Harmadik település"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        spinnerTelepulesHirdetesFeladasa.setAdapter(adapter);
    }


    //Kezdő dátum + időpont kiválasztása
    private void kezdoidopont(EditText editTextKezdoIdopt) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                        editTextKezdoIdopt.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(getActivity(),timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(getActivity(),dateSetListener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    //Záró dátum + időpont kiválasztása
    private void zaroidopont(EditText editTextZaroIdopt) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                        editTextZaroIdopt.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(getActivity(),timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(getActivity(),dateSetListener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
