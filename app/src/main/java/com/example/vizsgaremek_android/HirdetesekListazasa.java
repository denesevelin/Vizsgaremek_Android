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

public class HirdetesekListazasa extends Fragment {



    private ListView hirdetesAdatok;
    private TextView hibaText;
    private List<Hirdetes> hirdetesLista;
    private final String Hirdetes_URL = "http://192.168.0.18/Vizsgaremek_Web/api/hirdetes";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hirdetesek_listazasa, container, false);



        hirdetesAdatok = view.findViewById(R.id.hirdetesAdatok);
        hirdetesLista = new ArrayList<>();




        //Konkrét hirdetésre


        hirdetesekListazasa();

        return view;
    }

    private void hirdetesekListazasa() {
        RequestTask task = new RequestTask(Hirdetes_URL,"GET","");
        task.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    private void refreshHirdetesList(List<Hirdetes> adatok, String requestType, String parameterek) {
        switch (requestType){
            case "GET":
                this.hirdetesLista.clear();
                hirdetesLista.addAll(adatok);
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
                HirdetesApiValasz valasz = jsonConvert.fromJson(response.getContent(), HirdetesApiValasz.class);
                Log.d("responseCode", String.valueOf(response.getResponseCode()));
                Log.d("responseContent", response.getContent());
                if(valasz.isError()){
                    hibaText.setText(String.format("%d-as hiba: %s", response.getResponseCode(), valasz.getMessage()));
                }else {
                    refreshHirdetesList(valasz.getAdatok(), requestType, parameterek);

                }
                hirdetesAdatok.setAdapter(new HirdetesAdapter());
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

    private class HirdetesAdapter extends ArrayAdapter<Hirdetes> {


        public HirdetesAdapter() {
            super(HirdetesekListazasa.this.getActivity(), R.layout.hirdetes_list_item, hirdetesLista);
        }

        @Override
        public View getView(int position,  View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.hirdetes_list_item, null);
            TextView textViewHirdetesSzovegHirdetesekListazasa = listViewItem.findViewById(R.id.textViewHirdetesSzovegHirdetesekListazasa);
            ImageView ImageViewKategoriakepHirdetesekListazasa = listViewItem.findViewById(R.id.ImageViewKategoriakepHirdetesekListazasa);
            Button buttonTovabbHirdetesekListazasa = listViewItem.findViewById(R.id.buttonTovabbHirdetesekListazasa);


            Hirdetes hirdetes = hirdetesLista.get(position);
            textViewHirdetesSzovegHirdetesekListazasa.setText(String.format("%s ", hirdetes.getLeiras()));

            int kategoriaId = hirdetes.getKategoria_id();
            switch (kategoriaId) {
                case 1:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.kutyasetaltatas);
                    break;
                case 2:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.vasarlas);
                    break;
                case 3:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.takaritas);
                    break;
                case 4:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.szemelyszallitas);
                    break;
                case 5:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.idosgondozas);
                    break;
                case 6:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.gyermekfelugyelet);
                    break;
                case 7:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.szereles);
                    break;
                case 8:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.tarsalgas);
                    break;
                case 9:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.korrepetalas);
                    break;
                case 10:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.fozes);
                    break;
                case 11:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.kerteszkedes);
                    break;
                case 12:
                    ImageViewKategoriakepHirdetesekListazasa.setImageResource(R.drawable.egyeb);
                    break;
            }

            buttonTovabbHirdetesekListazasa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container,  KonkretHirdetes.newInstance(hirdetes.getHirdetes_id()));
                    fr.commit();
                }
            });


            return listViewItem;
        }


    }
}
