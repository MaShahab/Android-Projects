package com.example.mah.onlineshop.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {
    @SerializedName("results")
    private List<ProductsModels> products;

    public ProductsResponse() {
    }

    public List <ProductsModels> getProducts() {
        return products;
    }

    public void setProducts(List <ProductsModels> products) {
        this.products = products;
    }
}
