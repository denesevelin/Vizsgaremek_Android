package com.example.vizsgaremek_android;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfilModositas extends Fragment {

    private Button buttonVisszaProfilModositas, buttonProfilModositasProfilModositas;
    private TextView hibaText;
    private List<User> userLista;
    private String bejelentkezett_id;
    private final String EgyUser_URL = "http://192.168.0.18/Vizsgaremek_Web/api/user";
    String regexEmail = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$";
    private EditText editTelepulesProfilModositas, editCimProfilModositas, editEmailProfilModositas, editTelszamProfilModositas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profil_modositasa, container, false);


        buttonVisszaProfilModositas = view.findViewById(R.id.buttonVisszaProfilModositas);
        buttonProfilModositasProfilModositas = view.findViewById(R.id.buttonProfilModositasProfilModositas);
        editTelepulesProfilModositas = view.findViewById(R.id.editTelepulesProfilModositas);
        editCimProfilModositas = view.findViewById(R.id.editCimProfilModositas);
        editEmailProfilModositas = view.findViewById(R.id.editEmailProfilModositas);
        editTelszamProfilModositas = view.findViewById(R.id.editTelszamProfilModositas);
        hibaText = view.findViewById(R.id.hibaText);
        userLista = new ArrayList<>();

        buttonVisszaProfilModositas.setOnClickListener(v -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Profil());
            fr.commit();
        });

        userekListazasa();
        modosit();

        return view;
    }

    private void userekListazasa() {
        bejelentkezett_id = Adattarolo.userIdOlvasas(getContext());
        RequestTask task = new RequestTask(EgyUser_URL+"/"+bejelentkezett_id,"GET","");
        task.execute();
    }

    private void modosit() {
        buttonProfilModositasProfilModositas.setOnClickListener(v ->{
            try {
                User u = validalEsUsertLetrehoz();
                Gson jsonConvert = new Gson();
                String user_id = Adattarolo.userIdOlvasas(getContext());
                RequestTask task = new RequestTask(EgyUser_URL+"/"+user_id,"PUT", jsonConvert.toJson(u));
                task.execute();

            } catch (Exception e) {
                hibaText.setText(e.getMessage());
            }
        });
    }

    private User validalEsUsertLetrehoz() throws Exception {
        String telepules_id = editTelepulesProfilModositas.getText().toString().trim();
        String telszam = editTelszamProfilModositas.getText().toString().trim();
        String email = editEmailProfilModositas.getText().toString().trim();
        String cim = editCimProfilModositas.getText().toString().trim();

        if (telepules_id.isEmpty() || telszam.isEmpty() || email.isEmpty() || cim.isEmpty()){
            throw  new Exception("Minden mező kitöltése kötelező.");
        }else if (telszam.length() < 7 || telszam.length() > 30) {
            throw  new Exception("A telefonszám 7 és 30 karakter közötti lehet.");
        }else if (!email.matches(regexEmail)) {
            throw  new Exception("Nem megfelelő az e-mail cím formátuma.");
        }else if (cim.length() < 8 || cim.length() > 100){
            throw  new Exception("A cím 8 és 100 karakter közötti lehet");
        }else {
            return new User(0,Integer.parseInt(telepules_id),telszam,email,cim);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    private void refreshUserList(List<User> adatok, String requestType) {
        switch (requestType){
            case "GET":
                this.userLista.clear();
                userLista.addAll(adatok);
                userBetoltese(userLista.get(0));
                break;
            case "POST":
                userLista.addAll(0,adatok);
                break;
            case "PUT":
                User modositott = adatok.get(0);
                User regi = userLista.stream().filter(user -> user.getUser_id() == modositott.getUser_id()).findFirst().get();
                regi.setTelepules_id(modositott.getTelepules_id());
                regi.setTelszam(modositott.getTelszam());
                regi.setEmail(modositott.getEmail());
                regi.setCim(modositott.getCim());
                userLista.addAll(adatok);
                break;
        }
    }

    private void userBetoltese(User user) {
        editTelepulesProfilModositas.setText(String.format("%s ", user.getTelepules_id()));
        editCimProfilModositas.setText(String.format("%s ", user.getCim()));
        editEmailProfilModositas.setText(String.format("%s ", user.getEmail()));
        editTelszamProfilModositas.setText(String.format("%s ", user.getTelszam()));
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
                    case "GET":
                        response = RequestHandler.getRequest(url);
                        break;
                    case "POST":
                        response = RequestHandler.postRequest(url, parameterek);
                        break;
                    case "PUT":
                        response = RequestHandler.putRequest(url, parameterek);
                        break;
                }
            } catch (IOException e) {
                new HibaRunnable(e);
            }
            return response;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            if(response != null){
                Gson jsonConvert = new Gson();
                UserApiValasz valasz = jsonConvert.fromJson(response.getContent(), UserApiValasz.class);
                Log.d("responseCode", String.valueOf(response.getResponseCode()));
                Log.d("responseContent", response.getContent());
                if(valasz.isError()){
                    hibaText.setText(String.format("%d-as hiba: %s", response.getResponseCode(), valasz.getMessage()));
                }else {
                    if (requestType != "GET"){
                        Toast.makeText(getActivity(), "Sikeres módosítás!", Toast.LENGTH_SHORT).show();

                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new Profil());
                        fr.commit();
                    }else {
                        refreshUserList(valasz.getAdatok(), requestType);
                    }
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
