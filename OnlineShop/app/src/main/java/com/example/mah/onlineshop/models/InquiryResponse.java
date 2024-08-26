package com.example.mah.onlineshop.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InquiryResponse {

    @SerializedName("results")
    private List<InquiryInCartModel> cartInquiries;

    public InquiryResponse() {
    }

    public List <InquiryInCartModel> getCartInquiries() {
        return cartInquiries;
    }

    public void setCartInquiries(List <InquiryInCartModel> cartInquiries) {
        this.cartInquiries = cartInquiries;
    }
}
