package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.InquiryResponse;
import com.example.mah.onlineshop.models.UserNameModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InquiryController {
    ShopsAPIS.getInquiryCallback getInquiryCallback;

    public InquiryController(ShopsAPIS.getInquiryCallback getInquiryCallback) {
        this.getInquiryCallback = getInquiryCallback;
    }

    public void start(String authorization , UserNameModel userNameModel)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ( ShopsAPIS.Base_URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create(ShopsAPIS.class);
        Call<InquiryResponse> call = shopsAPIS.getInquiry(authorization,userNameModel);
        call.enqueue ( new Callback <InquiryResponse> () {
            @Override
            public void onResponse(Call <InquiryResponse> call, Response<InquiryResponse> response) {
                getInquiryCallback.onResponse ( response.body ().getCartInquiries () );
            }

            @Override
            public void onFailure(Call <InquiryResponse> call, Throwable t) {
                getInquiryCallback.onFailure ( t.getCause ().getMessage () );
            }
        } );
    }
}
