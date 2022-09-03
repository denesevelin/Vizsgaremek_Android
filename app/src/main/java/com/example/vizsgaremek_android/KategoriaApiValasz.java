package com.example.vizsgaremek_android;

import java.util.List;

public class KategoriaApiValasz extends ApiValasz<Kategoria>{
    public KategoriaApiValasz(boolean error, String message, List<Kategoria> adatok) {
        super(error, message, adatok);
    }
}
