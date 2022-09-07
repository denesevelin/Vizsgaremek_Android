package com.example.vizsgaremek_android;

public class Kategoria {
    private int kategoria_id;
    private String kategoria_nev;
    private String kategoria_kep;

    public Kategoria(int kategoria_id, String kategoria_nev, String kategoria_kep) {
        this.kategoria_id = kategoria_id;
        this.kategoria_nev = kategoria_nev;
        this.kategoria_kep = kategoria_kep;
    }

    public int getKategoria_id() {
        return kategoria_id;
    }

    public String getKategoria_nev() {
        return kategoria_nev;
    }

    public String getKategoria_kep() {
        return kategoria_kep;
    }
}
