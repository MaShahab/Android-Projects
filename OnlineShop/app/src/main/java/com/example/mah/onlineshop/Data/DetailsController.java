package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.DetailsResponse;
import com.example.mah.onlineshop.models.PeoductsModels2;
import com.example.mah.onlineshop.models.ProductsId;
import com.example.mah.onlineshop.models.ProductsModels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsController {

    private ShopsAPIS.ProductsDetailsCallback productsDetailsCallback;

    public DetailsController(ShopsAPIS.ProductsDetailsCallback productsDetailsCallback) {
        this.productsDetailsCallback = productsDetailsCallback;
    }

    public void start(String authorization , ProductsId productsId)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl(ShopsAPIS.Base_URL)
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create(ShopsAPIS.class);
        Call<DetailsResponse> call = shopsAPIS.getDetails(authorization,productsId);
        call.enqueue ( new Callback <DetailsResponse> () {
            @Override
            public void onResponse(Call <DetailsResponse> call, Response <DetailsResponse> response) {
                productsDetailsCallback.onResponse(response.body().getDetails());
            }

            @Override
            public void onFailure(Call <DetailsResponse> call, Throwable t) {
                productsDetailsCallback.onFailure ( t.getCause ().getMessage () );
            }
        } );
    }
}
