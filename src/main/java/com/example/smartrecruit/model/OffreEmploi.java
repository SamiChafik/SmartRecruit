package com.example.smartrecruit.model;

public class OffreEmploi {
    private int offer_id;
    private String Title;
    private String Description;
    private String pub_date;
public OffreEmploi(){}

    public OffreEmploi(String title, String description, String pub_date) {
        this.Title = title;
        this.Description = description;
        this.pub_date = pub_date;


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

    public String getPubDate() {
        return pub_date;
    }

    public void setPubDate(String pubDate) {
        this.pub_date= pubDate;
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
