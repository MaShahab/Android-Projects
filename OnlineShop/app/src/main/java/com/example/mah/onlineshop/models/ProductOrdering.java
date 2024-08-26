package com.example.mah.onlineshop.models;

import java.io.Serializable;

public class ProductOrdering implements Serializable {
    private String username;
    private String productId;
    private String count;

    public ProductOrdering() {
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
}
