package com.example.vizsgaremek_android;

import com.example.vizsgaremek_android.Response;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public final class RequestHandler {
    private RequestHandler(){}

    public static Response getRequest(String url) throws IOException {
        HttpURLConnection conn = kapcsolatBeallitas(url);

        return  valaszFeldolgozas(conn);
    }

    private static Response valaszFeldolgozas(HttpURLConnection conn) throws IOException {
        int responseCode = conn.getResponseCode();

        InputStream inputStream = null;
        if(responseCode < 400){
            inputStream = conn.getInputStream();
        } else {
            inputStream = conn.getErrorStream();
        }
        Scanner sc = new Scanner(inputStream) ;
        StringBuilder stringBuilder = new StringBuilder();
        while (sc.hasNext()){
            stringBuilder.append(sc.nextLine());
        }
        String s = stringBuilder.toString().trim();
        return new Response(responseCode, s);
    }

    public static Response postRequest(String url, String params) throws IOException {
        HttpURLConnection conn = kapcsolatBeallitas(url);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(params);
        writer.flush();
        writer.close();
        os.close();

        return valaszFeldolgozas(conn);
    }

    public static Response putRequest(String url, String params) throws IOException {
        HttpURLConnection conn = kapcsolatBeallitas(url);
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(params);
        writer.flush();
        writer.close();
        os.close();

        return valaszFeldolgozas(conn);
    }

    public static Response deleteRequest(String url) throws IOException {
        HttpURLConnection conn = kapcsolatBeallitas(url);
        conn.setRequestMethod("DELETE");

        return valaszFeldolgozas(conn);
    }

    private static HttpURLConnection kapcsolatBeallitas(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection conn =(HttpURLConnection) urlObj.openConnection();
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(15000);
        return conn;
    }
}
