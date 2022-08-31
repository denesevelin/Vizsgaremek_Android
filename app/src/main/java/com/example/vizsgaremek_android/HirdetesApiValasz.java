package com.example.vizsgaremek_android;

import java.util.List;

public class HirdetesApiValasz extends ApiValasz<Hirdetes>{
    public HirdetesApiValasz(boolean error, String message, List<Hirdetes> adatok) {
        super(error, message, adatok);
    }
}
