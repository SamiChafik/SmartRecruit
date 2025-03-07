package com.example.smartrecruit.model;

public class Candidature {
    private int offer_id;
    private int user_id;
    private int c_id;

    public Candidature() {
    }

    public Candidature(int offer_id, int user_id, int c_id) {
        this.offer_id = offer_id;
        this.user_id = user_id;
        this.c_id = c_id;
    }
}
