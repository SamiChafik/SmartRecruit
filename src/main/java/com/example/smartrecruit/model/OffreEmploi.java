package com.example.smartrecruit.model;

public class OffreEmploi {
    private int offer_id;
    private String Title;
    private String Description;
    private int pubDate;
public OffreEmploi(){}
    public OffreEmploi(String title, String description, int pubDate ) {
        Title = title;
        Description = description;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public int getPubDate() {
        return pubDate;
    }

    public void setPubDate(int pubDate) {
        this.pubDate = pubDate;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }
}
