package com.example.vizsgaremek_android;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class KonkretHirdetes extends Fragment {

    private Button buttonJelentkezesKonkretHirdetes;
    private static final String PARAM_ID = "id";
    private List<Hirdetes> hirdetesLista;
    private TextView textViewNevKonkretHirdetes, textViewTelepulesKonkretHirdetes, textViewCimKonkretHirdetes, textViewTelefonszamKonkretHirdetes,
            textViewKategoriaKonkretHirdetes, textViewKonkretKezdoIdopontKonkretHirdetes, textViewKonkretVegIdopontKonkretHirdetes,
            textViewLeirasKonkretHirdetes;
    private final String Hirdetes_URL = "http://192.168.0.18/Vizsgaremek_Web/api/hirdetes";
    private TextView hibaText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.konkret_hirdetes, container, false);

        buttonJelentkezesKonkretHirdetes = view.findViewById(R.id.buttonJelentkezesKonkretHirdetes);
        textViewNevKonkretHirdetes = view.findViewById(R.id.textViewNevKonkretHirdetes);
        textViewTelepulesKonkretHirdetes = view.findViewById(R.id.textViewTelepulesKonkretHirdetes);
        textViewCimKonkretHirdetes = view.findViewById(R.id.textViewCimKonkretHirdetes);
        textViewTelefonszamKonkretHirdetes = view.findViewById(R.id.textViewTelefonszamKonkretHirdetes);
        textViewKategoriaKonkretHirdetes = view.findViewById(R.id.textViewKategoriaKonkretHirdetes);
        textViewKonkretKezdoIdopontKonkretHirdetes = view.findViewById(R.id.textViewKonkretKezdoIdopontKonkretHirdetes);
        textViewKonkretVegIdopontKonkretHirdetes = view.findViewById(R.id.textViewKonkretVegIdopontKonkretHirdetes);
        textViewLeirasKonkretHirdetes = view.findViewById(R.id.textViewLeirasKonkretHirdetes);
        hirdetesLista = new ArrayList<>();

        //Profilra
        buttonJelentkezesKonkretHirdetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Profil());
                fr.commit();
            }
        });



        hirdetesListazasa();

        return view;



    }

    public static KonkretHirdetes newInstance(int id) {
        KonkretHirdetes f = new KonkretHirdetes();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt(PARAM_ID, id);
        f.setArguments(args);
        return f;
    }

    private void hirdetesListazasa() {
        Bundle args = getArguments();
        if (args != null) {
            int id = args.getInt(PARAM_ID, 0);
            Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
            RequestTask task = new RequestTask(Hirdetes_URL+"/"+id,"GET","");
            task.execute();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    private void refreshHirdetesList(List<Hirdetes> adatok, String requestType, String parameterek) {
        switch (requestType){
            case "GET":
                this.hirdetesLista.clear();
                hirdetesLista.addAll(adatok);
                hirdetesBetoltese(hirdetesLista.get(0));
                break;
        }
    }

    private void hirdetesBetoltese(Hirdetes hirdetes) {
        textViewNevKonkretHirdetes.setText(String.format("%s ", hirdetes.getHirdeto_id()));
        textViewTelepulesKonkretHirdetes.setText(String.format("%s ", hirdetes.getTelepules_id()));
        textViewCimKonkretHirdetes.setText(String.format("%s ", hirdetes.getHirdetes_cim()));
        textViewTelefonszamKonkretHirdetes.setText(String.format("%s ",hirdetes.getHirdetes_telszam() ));
        textViewKategoriaKonkretHirdetes.setText(String.format("%s ", hirdetes.getKategoria_id()));
        textViewKonkretKezdoIdopontKonkretHirdetes.setText(String.format("%s ", hirdetes.getKezdo_idopont()));
        textViewKonkretVegIdopontKonkretHirdetes.setText(String.format("%s ", hirdetes.getZaro_idopont()));
        textViewLeirasKonkretHirdetes.setText(String.format("%s ", hirdetes.getLeiras()));
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
                HirdetesApiValasz valasz = jsonConvert.fromJson(response.getContent(), HirdetesApiValasz.class);
                Log.d("responseCode", String.valueOf(response.getResponseCode()));
                Log.d("responseContent", response.getContent());
                if(valasz.isError()){
                    hibaText.setText(String.format("%d-as hiba: %s", response.getResponseCode(), valasz.getMessage()));
                }else {
                        refreshHirdetesList(valasz.getAdatok(), requestType, parameterek);
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
