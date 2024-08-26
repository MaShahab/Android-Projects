package com.example.mah.onlineshop.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterOrder {
    private String username;
    private String addressId;
    private Integer totalPrice;
    private String createdAt;
    private String _id;

    @SerializedName("products")
    private List<ComponentsModel> componentsModels;

    public RegisterOrder() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List <ComponentsModel> getComponentsModels() {
        return componentsModels;
    }

    public void setComponentsModels(List <ComponentsModel> componentsModels) {
        this.componentsModels = componentsModels;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
