package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.ProductsModels;
import com.example.mah.onlineshop.models.ProductsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsController {
    ShopsAPIS.ProductsCallback productsCallback;

    public ProductsController(ShopsAPIS.ProductsCallback productsCallback) {
        this.productsCallback = productsCallback;
    }

    public void start(String authorization)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl(ShopsAPIS.Base_URL)
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create(ShopsAPIS.class);
        Call<ProductsResponse> call = shopsAPIS.showProducts(authorization);
        call.enqueue ( new Callback <ProductsResponse> () {
            @Override
            public void onResponse(Call <ProductsResponse> call, Response <ProductsResponse> response) {
                productsCallback.onResponse (response.body ().getProducts());
            }

            @Override
            public void onFailure(Call <ProductsResponse> call, Throwable t) {
                productsCallback.onFailure ( t.getCause ().getMessage () );
            }
        } );

    }
}
