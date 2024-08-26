package com.example.mah.onlineshop.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PeoductsModels2 implements Serializable {
    @SerializedName("_id")
    private String id;

    @SerializedName("createdAt")
    private String publishDate;

    @SerializedName("updatedAt")
    private String updateDate;

    private String name;
    private String price;
    private String description;
    private String photoUrl;
    private String rating;

    public PeoductsModels2() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
