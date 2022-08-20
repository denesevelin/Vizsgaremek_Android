package com.example.vizsgaremek_android;

public class Telepules {
    private int telepules_id;
    private String telepules;

    public Telepules(int telepules_id, String telepules) {
        this.telepules_id = telepules_id;
        this.telepules = telepules;
    }

    public int getTelepules_id() {
        return telepules_id;
    }

    public void setTelepules_id(int telepules_id) {
        this.telepules_id = telepules_id;
    }

    public String getTelepules() {
        return telepules;
    }

    public void setTelepules(String telepules) {
        this.telepules = telepules;
    }
}
