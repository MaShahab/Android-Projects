package com.example.mah.onlineshop.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpeciphicationsResponse {

    @SerializedName("results")
    private List<GetSpeciphications> mySpeciphications;

    public SpeciphicationsResponse() {
    }

    public List <GetSpeciphications> getMySpeciphications() {
        return mySpeciphications;
    }

    public void setMySpeciphications(List <GetSpeciphications> mySpeciphications) {
        this.mySpeciphications = mySpeciphications;
    }
}
