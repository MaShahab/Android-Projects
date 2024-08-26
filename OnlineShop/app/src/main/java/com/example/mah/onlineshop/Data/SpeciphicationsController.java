package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.SpeciphicationsResponse;
import com.example.mah.onlineshop.models.UserNameModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpeciphicationsController {
    ShopsAPIS.getSpeciphicationsCallback getSpeciphicationsCallback;

    public SpeciphicationsController(ShopsAPIS.getSpeciphicationsCallback getSpeciphicationsCallback) {
        this.getSpeciphicationsCallback = getSpeciphicationsCallback;
    }

    public void start(String authorization, UserNameModel userNameModel)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ( ShopsAPIS.Base_URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create ( ShopsAPIS.class );
        Call<SpeciphicationsResponse> call = shopsAPIS.getMySpeciphications(authorization,userNameModel);
        call.enqueue ( new Callback <SpeciphicationsResponse> () {
            @Override
            public void onResponse(Call <SpeciphicationsResponse> call, Response<SpeciphicationsResponse> response) {
                getSpeciphicationsCallback.onResponse ( response.body ().getMySpeciphications () );
            }

            @Override
            public void onFailure(Call <SpeciphicationsResponse> call, Throwable t) {
                getSpeciphicationsCallback.onFailure ( t.getCause ().getMessage () );
            }
        } );
    }
}
