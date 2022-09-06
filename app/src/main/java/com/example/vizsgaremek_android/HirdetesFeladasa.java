package com.example.vizsgaremek_android;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HirdetesFeladasa extends Fragment {

    private EditText editTextKezdoIdoptHirdetesFeladasa, editTextZaroIdoptHirdetesFeladasa, editTextCimHirdetesFeladasa, editTelepulesHirdetesFeladasa, editTextLeirasHirdetesFeladasa, editTextTelszamHirdetesFeladasa;
    private Spinner spinnerKategoriaHirdetesFeladasa;
    private Button buttonMentesHirdetesFeladasa;
    private TextView hibaText;
    private final String HirdetesFeladasa_URL = "http://192.168.0.18/Vizsgaremek_Web/api/hirdetes";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hirdetes_feladasa, container, false);

        editTextKezdoIdoptHirdetesFeladasa = view.findViewById(R.id.editTextKezdoIdoptHirdetesFeladasa);
        editTextZaroIdoptHirdetesFeladasa = view.findViewById(R.id.editTextZaroIdoptHirdetesFeladasa);
        spinnerKategoriaHirdetesFeladasa = view.findViewById(R.id.spinnerKategoriaHirdetesFeladasa);
        editTelepulesHirdetesFeladasa = view.findViewById(R.id.editTelepulesHirdetesFeladasa);
        editTextLeirasHirdetesFeladasa = view.findViewById(R.id.editTextLeirasHirdetesFeladasa);
        editTextTelszamHirdetesFeladasa = view.findViewById(R.id.editTextTelszamHirdetesFeladasa);
        editTextCimHirdetesFeladasa = view.findViewById(R.id.editTextCimHirdetesFeladasa);
        buttonMentesHirdetesFeladasa = view.findViewById(R.id.buttonMentesHirdetesFeladasa);
        hibaText = view.findViewById(R.id.hibaText);

        kategoriakBetoltese();

        editTextKezdoIdoptHirdetesFeladasa.setOnClickListener(view1 -> kezdoidopont(editTextKezdoIdoptHirdetesFeladasa));

        editTextZaroIdoptHirdetesFeladasa.setOnClickListener(view12 -> zaroidopont(editTextZaroIdoptHirdetesFeladasa));

        felvesz();

        return view;
    }

    private void felvesz() {
        buttonMentesHirdetesFeladasa.setOnClickListener(v ->{
            try {
                Hirdetes h = validalEsHirdetestLetrehoz();
                Gson jsonConvert = new Gson();
                RequestTask task = new RequestTask(HirdetesFeladasa_URL,"POST", jsonConvert.toJson(h));
                task.execute();
                Toast.makeText(getActivity(), "Sikeresen feladta hirdetését!", Toast.LENGTH_SHORT).show();

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fooldal());
                fr.commit();
            } catch (Exception e) {
                hibaText.setText(e.getMessage());
            }
        });
    }

    private Hirdetes validalEsHirdetestLetrehoz() throws Exception {
        String kategoria = spinnerKategoriaHirdetesFeladasa.getSelectedItem().toString();
        String hirdeto_id = Adattarolo.userIdOlvasas(getContext());
        String kezdo_idopont = editTextKezdoIdoptHirdetesFeladasa.getText().toString();
        String zaro_idopont = editTextZaroIdoptHirdetesFeladasa.getText().toString();
        String leiras = editTextLeirasHirdetesFeladasa.getText().toString();
        String hirdetes_telszam = editTextTelszamHirdetesFeladasa.getText().toString();
        String telepules_id = editTelepulesHirdetesFeladasa.getText().toString();
        String hirdetes_cim = editTextCimHirdetesFeladasa.getText().toString();

        String kategoria_id = "0";
        switch (kategoria){
            case "Kutyasétáltatás":
                kategoria_id = "1";
                break;
            case "Vásárlás":
                kategoria_id = "2";
                break;
            case "Takarítás":
                kategoria_id = "3";
                break;
            case "Személyszállítás":
                kategoria_id = "4";
                break;
            case "Idősgondozás":
                kategoria_id = "5";
                break;
            case "Gyermekfelügyelet":
                kategoria_id = "6";
                break;
            case "Szerelés":
                kategoria_id = "7";
                break;
            case "Társalgás":
                kategoria_id = "8";
                break;
            case "Korrepetálás":
                kategoria_id = "9";
                break;
            case "Főzés":
                kategoria_id = "10";
                break;
            case "Kertészkedés":
                kategoria_id = "11";
                break;
            case "Egyéb":
                kategoria_id = "12";
                break;
        }

        if (kategoria.isEmpty() || kezdo_idopont.isEmpty() || zaro_idopont.isEmpty() || leiras.isEmpty() || hirdetes_telszam.isEmpty() || hirdetes_cim.isEmpty()){
            throw  new Exception("Minden mező kitöltése kötelező.");
        }else if (hirdetes_telszam.length() < 7 || hirdetes_telszam.length() > 30) {
            throw  new Exception("A telefonszám 7 és 30 karakter közötti lehet.");
        }else if (hirdetes_cim.length() < 8 || hirdetes_cim.length() > 100){
            throw  new Exception("A cím 8 és 100 karakter közötti lehet");
        }else {
            return new Hirdetes(0, Integer.parseInt(kategoria_id), Integer.parseInt(hirdeto_id), kezdo_idopont, zaro_idopont, leiras, hirdetes_telszam, hirdetes_cim, Integer.parseInt(telepules_id));
        }
    }

    private class RequestTask extends AsyncTask<Void, Void, Response> {

        private String url;
        private String requestType;
        private String parameterek;

        public RequestTask(String url, String requestType, String parameterek) {
            this.url = url;
            this.requestType = requestType;
            this.parameterek = parameterek;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                if (requestType.equals("POST")) {
                    response = RequestHandler.postRequest(url, parameterek);
                }
            } catch (IOException e) {
                new HibaRunnable(e);
            }
            return response;
        }
    }

    private class HibaRunnable implements Runnable {

        private Exception ex;

        public HibaRunnable(Exception ex) {
            this.ex = ex;
        }

        @Override
        public void run() {
            hibaText.setText(ex.toString());
        }
    }

    public void kategoriakBetoltese(){
        String[] items = new String[]{"Kutyasétáltatás", "Vásárlás", "Takarítás", "Személyszállítás", "Idősgondozás", "Gyermekfelügyelet", "Szerelés", "Társalgás", "Korrepetálás", "Főzés", "Kertészkedés", "Egyéb"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        spinnerKategoriaHirdetesFeladasa.setAdapter(adapter);
    }

    //Kezdő dátum + időpont kiválasztása
    private void kezdoidopont(EditText editTextKezdoIdopt) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

            TimePickerDialog.OnTimeSetListener timeSetListener = (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                editTextKezdoIdopt.setText(simpleDateFormat.format(calendar.getTime()));
            };
            new TimePickerDialog(getActivity(),timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
        };
        new DatePickerDialog(getActivity(),dateSetListener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    //Záró dátum + időpont kiválasztása
    private void zaroidopont(EditText editTextZaroIdopt) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

            TimePickerDialog.OnTimeSetListener timeSetListener = (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                editTextZaroIdopt.setText(simpleDateFormat.format(calendar.getTime()));
            };
            new TimePickerDialog(getActivity(),timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
        };
        new DatePickerDialog(getActivity(),dateSetListener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
