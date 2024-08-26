package com.example.mah.onlineshop.models;

import com.google.gson.annotations.SerializedName;

public class InquiryInCartModel {

    @SerializedName("_id")
    private String id;

    private String username;
    private String productId;
    private String count;
    private String createdAt;

    public InquiryInCartModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
