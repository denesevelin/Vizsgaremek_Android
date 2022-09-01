package com.example.vizsgaremek_android;

public class Hirdetes {
    private int hirdetes_id;
    private int kategoria_id;
    private int hirdeto_id;
    private String kezdo_idopont;
    private String zaro_idopont;
    private String leiras;
    private String hirdetes_telszam;
    private  String hirdetes_cim;
    private int telepules_id;

    public Hirdetes(int hirdetes_id, int kategoria_id, int hirdeto_id, String kezdo_idopont, String zaro_idopont, String leiras, String hirdetes_telszam, String hirdetes_cim, int telepules_id) {
        this.hirdetes_id = hirdetes_id;
        this.kategoria_id = kategoria_id;
        this.hirdeto_id = hirdeto_id;
        this.kezdo_idopont = kezdo_idopont;
        this.zaro_idopont = zaro_idopont;
        this.leiras = leiras;
        this.hirdetes_telszam = hirdetes_telszam;
        this.hirdetes_cim = hirdetes_cim;
        this.telepules_id = telepules_id;
    }

    public int getHirdetes_id() {
        return hirdetes_id;
    }

    public void setHirdetes_id(int hirdetes_id) {
        this.hirdetes_id = hirdetes_id;
    }

    public int getKategoria_id() {
        return kategoria_id;
    }

    public void setKategoria_id(int kategora_id) {
        this.kategoria_id = kategora_id;
    }

    public int getHirdeto_id() {
        return hirdeto_id;
    }

    public void setHirdeto_id(int hirdeto_id) {
        this.hirdeto_id = hirdeto_id;
    }

    public String getKezdo_idopont() {
        return kezdo_idopont;
    }

    public void setKezdo_idopont(String kezdo_idopont) {
        this.kezdo_idopont = kezdo_idopont;
    }

    public String getZaro_idopont() {
        return zaro_idopont;
    }

    public void setZaro_idopont(String zaro_idopont) {
        this.zaro_idopont = zaro_idopont;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getHirdetes_telszam() {
        return hirdetes_telszam;
    }

    public void setHirdetes_telszam(String hirdetes_telszam) {
        this.hirdetes_telszam = hirdetes_telszam;
    }

    public String getHirdetes_cim() {
        return hirdetes_cim;
    }

    public void setHirdetes_cim(String hirdetes_cim) {
        this.hirdetes_cim = hirdetes_cim;
    }

    public int getTelepules_id() {
        return telepules_id;
    }

    public void setTelepules_id(int telepules_id) {
        this.telepules_id = telepules_id;
    }
}
