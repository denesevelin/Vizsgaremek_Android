package com.example.vizsgaremek_android;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ranglista extends Fragment {

    private ListView statisztikaLista;
    private List<Kategoria> kategoriaLista;
    private final String Kategoria_URL = "http://192.168.0.18/Vizsgaremek_Web/api/kategoria";
    private TextView hibaText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranglista, container, false);


        statisztikaLista = view.findViewById(R.id.statisztikaLista);
        kategoriaLista = new ArrayList<>();

        kategoriakListazasa();

        return view;
    }

    private void kategoriakListazasa() {
        RequestTask task = new RequestTask(Kategoria_URL,"GET","");
        task.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    private void refreshKategoriaList(List<Kategoria> adatok, String requestType, String parameterek) {
        switch (requestType){
            case "GET":
                this.kategoriaLista.clear();
                kategoriaLista.addAll(adatok);
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
                KategoriaApiValasz valasz = jsonConvert.fromJson(response.getContent(), KategoriaApiValasz.class);
                Log.d("responseCode", String.valueOf(response.getResponseCode()));
                Log.d("responseContent", response.getContent());
                if(valasz.isError()){
                    hibaText.setText(String.format("%d-as hiba: %s", response.getResponseCode(), valasz.getMessage()));
                }else {
                    refreshKategoriaList(valasz.getAdatok(), requestType, parameterek);

                }
                statisztikaLista.setAdapter(new KategoriaAdapter());
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

    private class KategoriaAdapter extends ArrayAdapter<Kategoria> {


        public KategoriaAdapter() {
            super(Ranglista.this.getActivity(), R.layout.statisztika_list_item, kategoriaLista);
        }

        @Override
        public View getView(int position,  View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.statisztika_list_item, null);
            TextView textViewKategoriaNevRanglista = listViewItem.findViewById(R.id.textViewKategoriaNevRanglista);
            ImageView ImageViewKategoriakepRanglista = listViewItem.findViewById(R.id.ImageViewKategoriakepRanglista);


            Kategoria kategoria = kategoriaLista.get(position);
            textViewKategoriaNevRanglista.setText(String.format("%s ", kategoria.getKategoria_nev()));

            int kategoriaId = kategoria.getKategoria_id();
            switch (kategoriaId) {
                case 1:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.kutyasetaltatas);
                    break;
                case 2:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.vasarlas);
                    break;
                case 3:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.takaritas);
                    break;
                case 4:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.szemelyszallitas);
                    break;
                case 5:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.idosgondozas);
                    break;
                case 6:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.gyermekfelugyelet);
                    break;
                case 7:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.szereles);
                    break;
                case 8:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.tarsalgas);
                    break;
                case 9:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.korrepetalas);
                    break;
                case 10:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.fozes);
                    break;
                case 11:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.kerteszkedes);
                    break;
                case 12:
                    ImageViewKategoriakepRanglista.setImageResource(R.drawable.egyeb);
                    break;
            }


            return listViewItem;
        }


    }
}
