package com.example.mah.onlineshop.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailsResponse {
    @SerializedName("results")
    private List<PeoductsModels2> details;

    public DetailsResponse() {
    }

    public List <PeoductsModels2> getDetails() {
        return details;
    }

    public void setDetails(List <PeoductsModels2> details) {
        this.details = details;
    }
}
