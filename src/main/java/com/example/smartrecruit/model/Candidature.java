package com.example.smartrecruit.model;

public class Candidature {
    private int offer_id;
    private int user_id;
    private int c_id;
    private User user;
    private OffreEmploi offer;

    public Candidature() {
    }

    public Candidature(int offer_id, int user_id, int c_id) {
        this.offer_id = offer_id;
        this.user_id = user_id;
        this.c_id = c_id;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OffreEmploi getOffer() {
        return offer;
    }

    public void setOffer(OffreEmploi offer) {
        this.offer = offer;
    }
}
