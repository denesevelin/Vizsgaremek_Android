package com.example.vizsgaremek_android;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Profil extends Fragment {

    private ListView userAdatok;
    private ImageView imageViewJelentkeztemProfil;
    private Button buttonProfilModositasProfil;
    private TextView hibaText;
    private List<User> userLista;
    private String bejelentkezett_id ;
    private final String EgyUser_URL = "http://192.168.0.18/Vizsgaremek_Web/api/user/";

    private TextView textViewNevProfil, textViewFelhasznalonevProfil, textViewTelefonszamProfil, textViewEmailProfil, textViewTelepulesProfil, textViewCimProfil, textViewKonkretPontszamProfil;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profil, container, false);

        imageViewJelentkeztemProfil = view.findViewById(R.id.imageViewJelentkeztemProfil);
        buttonProfilModositasProfil = view.findViewById(R.id.buttonProfilModositasProfil);

        textViewNevProfil = view.findViewById(R.id.textViewNevProfil);
        textViewFelhasznalonevProfil = view.findViewById(R.id.textViewFelhasznalonevProfil);
        textViewTelefonszamProfil = view.findViewById(R.id.textViewTelefonszamProfil);
        textViewEmailProfil = view.findViewById(R.id.textViewEmailProfil);
        textViewTelepulesProfil = view.findViewById(R.id.textViewTelepulesProfil);
        textViewCimProfil = view.findViewById(R.id.textViewCimProfil);
        textViewKonkretPontszamProfil = view.findViewById(R.id.textViewKonkretPontszamProfil);
        userAdatok = view.findViewById(R.id.userAdatok);
        userLista = new ArrayList<>();

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

        userekListazasa();

        return view;
    }

    private void userekListazasa() {
        bejelentkezett_id = Adattarolo.userIdOlvasas(getContext());
        RequestTask task = new RequestTask(EgyUser_URL+bejelentkezett_id,"GET","");
        task.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    private void refreshUserList(List<User> adatok, String requestType, String parameterek) {
        switch (requestType){
            case "GET":
                this.userLista.clear();
                userLista.addAll(adatok);
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
                    case "GET":
                        response = RequestHandler.getRequest(url);
                        break;
                }
            } catch (IOException e) {
                new HibaRunnable(e);
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //hibaText.setText("");
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
                    refreshUserList(valasz.getAdatok(), requestType, parameterek);

                }
                userAdatok.setAdapter(new UserAdapter());

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

    private class UserAdapter extends ArrayAdapter<User> {


        public UserAdapter() {
            super(Profil.this.getActivity(), R.layout.profil_user_list_item, userLista);
        }

        @Override
        public View getView(int position,  View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.profil_user_list_item, null);


            TextView textViewNevProfil = listViewItem.findViewById(R.id.textViewNevProfil);
            TextView textViewFelhasznalonevProfil = listViewItem.findViewById(R.id.textViewFelhasznalonevProfil);
            TextView textViewTelefonszamProfil = listViewItem.findViewById(R.id.textViewTelefonszamProfil);
            TextView textViewEmailProfil = listViewItem.findViewById(R.id.textViewEmailProfil);
            TextView textViewTelepulesProfil = listViewItem.findViewById(R.id.textViewTelepulesProfil);
            TextView textViewCimProfil = listViewItem.findViewById(R.id.textViewCimProfil);
            TextView textViewKonkretPontszamProfil = listViewItem.findViewById(R.id.textViewKonkretPontszamProfil);


            User user = userLista.get(position);
            textViewNevProfil.setText(String.format("%s ", user.getNev()));
            textViewFelhasznalonevProfil.setText(String.format("%s ", user.getFelhnev()));
            textViewTelefonszamProfil.setText(String.format("%s ", user.getTelszam()));
            textViewEmailProfil.setText(String.format("%s ", user.getEmail()));
            textViewTelepulesProfil.setText(String.format("%s ", user.getTelepules_id()));
            textViewCimProfil.setText(String.format("%s ", user.getCim()));
            textViewKonkretPontszamProfil.setText(String.format("%s ", user.getPontszam()));

            return listViewItem;
        }


    }
}
