package com.example.vizsgaremek_android;

public class User {
    private int user_id;
    private String nev;
    private String felhnev;
    private String szuldatum;
    private int telepules_id;
    private String telszam;
    private String email;
    private String jelszo;
    private String profilkep;
    private String okmanykep;
    private String okmanyszam;
    private String cim;
    private int pontszam;

    public User(int pontszam) {
        this.pontszam = pontszam;
    }

    public int getPontszam() {
        return pontszam;
    }


    public User(int user_id, int telepules_id, String telszam, String email, String cim) {
        this.user_id = user_id;
        this.telepules_id = telepules_id;
        this.telszam = telszam;
        this.email = email;
        this.cim = cim;
    }

    public User(int user_id, String nev, String felhnev, String szuldatum, int telepules_id, String telszam, String email, String jelszo, String profilkep, String okmanykep, String okmanyszam, String cim) {
        this.user_id = user_id;
        this.nev = nev;
        this.felhnev = felhnev;
        this.szuldatum = szuldatum;
        this.telepules_id = telepules_id;
        this.telszam = telszam;
        this.email = email;
        this.jelszo = jelszo;
        this.profilkep = profilkep;
        this.okmanykep = okmanykep;
        this.okmanyszam = okmanyszam;
        this.cim = cim;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getNev() {
        return nev;
    }

    public String getFelhnev() {
        return felhnev;
    }

    public String getSzuldatum() {
        return szuldatum;
    }

    public int getTelepules_id() {
        return telepules_id;
    }

    public void setTelepules_id(int telepules_id) {
        this.telepules_id = telepules_id;
    }

    public String getTelszam() {
        return telszam;
    }

    public void setTelszam(String telszam) {
        this.telszam = telszam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJelszo() {
        return jelszo;
    }

    public String getProfilkep() {
        return profilkep;
    }

    public String getOkmanykep() {
        return okmanykep;
    }

    public String getOkmanyszam() {
        return okmanyszam;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }
}
