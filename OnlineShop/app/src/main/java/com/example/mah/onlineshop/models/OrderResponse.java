package com.example.mah.onlineshop.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderResponse {

    @SerializedName("results")
    private List<RegisterOrder> registerOrderList;

    public OrderResponse() {
    }

    public List <RegisterOrder> getRegisterOrderList() {
        return registerOrderList;
    }

    public void setRegisterOrderList(List <RegisterOrder> registerOrderList) {
        this.registerOrderList = registerOrderList;
    }
}
