package com.example.vizsgaremek_android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adatletolto {

    private Handler MainHandler = new Handler(Looper.getMainLooper());
    private Gson jsonConvert = new Gson();

    class UserRequest {
        private String felhnev;
        private String jelszo;

        public UserRequest(String felhnev, String jelszo) {
            this.felhnev = felhnev;
            this.jelszo = jelszo;
        }
    }

    protected void bejelentkezes(Context context, String felhnev, String jelszo, UserListener listener){
        new Thread(() -> {
            try {
                String parameter = jsonConvert.toJson(new UserRequest(felhnev, jelszo));
                Response response = RequestHandler.postRequest("http://192.168.0.18/Vizsgaremek_Web/api/user/bejelentkezes", parameter);
                MainHandler.post(() ->{
                    listener.OnUserReady(response.getResponseCode() == 200);
                    Adattarolo.userIdMentes(context, response.getContent());
                        });

            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }

    interface UserListener {
        void OnUserReady(boolean Successful);
    }



}



