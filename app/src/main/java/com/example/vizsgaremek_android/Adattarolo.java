package com.example.vizsgaremek_android;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class Adattarolo {

    public static void userIdMentes(Context context, String user_id){
        SharedPreferences sharedPreferences = context.getSharedPreferences("tarolo", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("id", user_id).apply();
    }

    public static String userIdOlvasas(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("tarolo", Context.MODE_PRIVATE);
        return sharedPreferences.getString("id", null);
    }
}
