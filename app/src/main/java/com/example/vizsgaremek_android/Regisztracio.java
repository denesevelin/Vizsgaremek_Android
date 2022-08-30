package com.example.vizsgaremek_android;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Regisztracio extends Fragment {

    private Button buttonRegisztraciosFeluletRegisztracio;

    private EditText editNevRegisztracio, editFelhasznalonevRegisztracio, editSzuletesiDatumRegisztracio, editTelefonszamRegisztracio,
            editEmailRegisztracio, editTelepulesRegisztracio, editCimRegisztracio, editOkmanyszamRegisztracio, editOkmanykepRegisztracio,
            editProfilkepRegisztracio, editJelszoRegisztracio, editJelszoIsmetRegisztracio;

    private CheckBox checkBoxNyilatkozatRegisztracio;
    private TextView hibaText;
    private final String BASE_URL = "http://192.168.0.18/Vizsgaremek_Web/api/user";
    private List<User> userLista;

    String regexEmail = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$";
    String regexJelszo = "(?=.*\\d)(?=.*[A-Za-z]).{6,100}";
    String regexNev = "^((Mr\\.|Dr\\.|dr\\.|Ms\\.|Mrs\\.)\\s)?([A-ZÁÉÍÓÖŐÚÜŰ][a-záéíóöőúüű]+([-][A-ZÁÉÍÓÖŐÚÜŰ][a-záéíóöőúüű]+){0,1})(\\s[A-ZÉÍÓÖŐÚÜŰ][a-záéíóöőúüű]+(\\s[A-ZÉÍÓÖŐÚÜŰ][a-záéíóöőúüű]+){0,1})";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.regisztracio, container, false);

        buttonRegisztraciosFeluletRegisztracio = view.findViewById(R.id.buttonRegisztraciosFeluletRegisztracio);
        editNevRegisztracio = view.findViewById(R.id.editNevRegisztracio);
        editFelhasznalonevRegisztracio = view.findViewById(R.id.editFelhasznalonevRegisztracio);
        editSzuletesiDatumRegisztracio = view.findViewById(R.id.editSzuletesiDatumRegisztracio);
        editTelefonszamRegisztracio = view.findViewById(R.id.editTelefonszamRegisztracio);
        editEmailRegisztracio = view.findViewById(R.id.editEmailRegisztracio);
        editTelepulesRegisztracio = view.findViewById(R.id.editTelepulesRegisztracio);
        editCimRegisztracio = view.findViewById(R.id.editCimRegisztracio);
        editOkmanyszamRegisztracio = view.findViewById(R.id.editOkmanyszamRegisztracio);
        editOkmanykepRegisztracio = view.findViewById(R.id.editOkmanykepRegisztracio);
        editProfilkepRegisztracio = view.findViewById(R.id.editProfilkepRegisztracio);
        editJelszoRegisztracio = view.findViewById(R.id.editJelszoRegisztracio);
        editJelszoIsmetRegisztracio = view.findViewById(R.id.editJelszoIsmetRegisztracio);
        checkBoxNyilatkozatRegisztracio = view.findViewById(R.id.checkBoxNyilatkozatRegisztracio);
        hibaText = view.findViewById(R.id.hibaText);
        userLista = new ArrayList<>();

        felvesz();

        return view;
    }

    private void felvesz() {
        buttonRegisztraciosFeluletRegisztracio.setOnClickListener(v ->{
            try {
                User u = validalEsUsertLetrehoz();
                Gson jsonConvert = new Gson();
                RequestTask task = new RequestTask(BASE_URL,"POST", jsonConvert.toJson(u));
                task.execute();
                Toast.makeText(getActivity(), "Sikeres regisztráció, szíves türelmét kérjük a hitelesítési folyamat végéig!", Toast.LENGTH_SHORT).show();

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fooldal());
                fr.commit();
            } catch (Exception e) {
                hibaText.setText(e.getMessage());
            }
        });
    }

    private User validalEsUsertLetrehoz() throws Exception {
        String nev = editNevRegisztracio.getText().toString().trim();
        String felhnev = editFelhasznalonevRegisztracio.getText().toString().trim();
        String szuldatum = editSzuletesiDatumRegisztracio.getText().toString().trim();
        String telepules_id = editTelepulesRegisztracio.getText().toString().trim();
        String telszam = editTelefonszamRegisztracio.getText().toString().trim();
        String email = editEmailRegisztracio.getText().toString().trim();
        String jelszo = editJelszoRegisztracio.getText().toString().trim();
        String jelszoIsmet = editJelszoIsmetRegisztracio.getText().toString().trim();
        String profilkep = editProfilkepRegisztracio.getText().toString().trim();
        String okmanykep = editOkmanykepRegisztracio.getText().toString().trim();
        String okmanyszam = editOkmanyszamRegisztracio.getText().toString().trim();
        String cim = editCimRegisztracio.getText().toString().trim();

        if (nev.isEmpty() || felhnev.isEmpty() || szuldatum.isEmpty() || telepules_id.isEmpty() || telszam.isEmpty() || email.isEmpty() || jelszo.isEmpty() || profilkep.isEmpty() || okmanykep.isEmpty() || okmanyszam.isEmpty() || cim.isEmpty()){
            throw  new Exception("Minden mező kitöltése kötelező.");
        }else if (!nev.matches(regexNev)){
            throw  new Exception("Nem jól adta meg a nevét.");
        }else if (felhnev.length() < 6 || felhnev.length() > 100){
            throw  new Exception("A felhasználónév 6 és 100 karakter közötti lehet.");
        }else if (telszam.length() < 7 || telszam.length() > 30) {
            throw  new Exception("A telefonszám 7 és 30 karakter közötti lehet.");
        }else if (!email.matches(regexEmail)) {
            throw  new Exception("Nem megfelelő az e-mail cím formátuma.");
        }else if (!jelszo.equals(jelszoIsmet)){
            throw  new Exception("Nem ugyanaz a két jelszó.");
        }else if (!jelszo.matches(regexJelszo)){
            throw  new Exception("A jelszó 6 és 100 karakter közötti lehet, tartalmaznia kell minimum egy számot is.");
        }else if (cim.length() < 8 || cim.length() > 100){
            throw  new Exception("A cím 8 és 100 karakter közötti lehet");
        }else if (!checkBoxNyilatkozatRegisztracio.isChecked()){
            throw  new Exception("Nem fogadta el a feltételeket.");
        }else {
        return new User(0,nev,felhnev,szuldatum,Integer.parseInt(telepules_id), telszam, email, jelszo, profilkep, okmanykep, okmanyszam, cim);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refreshUserList(List<User> adatok, String requestType, String parameterek) {
        switch (requestType){
            case "POST":
                userLista.add(0, (User) adatok);
                break;
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
                switch (requestType){
                    case "POST":
                        response = RequestHandler.postRequest(url, parameterek);
                        break;
                }
            } catch (IOException e) {
                getActivity().runOnUiThread(new HibaRunnable(e));
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            hibaText.setText("");
        }

        @RequiresApi(api = Build.VERSION_CODES.N)

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            if(response != null){
                Gson jsonConvert = new Gson();
                UserApiValasz valasz = jsonConvert.fromJson(response.getContent(), UserApiValasz.class);
                if(valasz.isError()){
                    hibaText.setText(String.format("%d-as hiba: %s", response.getResponseCode(), valasz.getMessage()));
                }else {
                    refreshUserList(valasz.getAdatok(), requestType, parameterek);
                }
            }
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
}
