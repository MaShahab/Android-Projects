package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.ProductOrdering;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderProductController {
    ShopsAPIS.OrderProductsCallback productsCallback;

    public OrderProductController(ShopsAPIS.OrderProductsCallback productsCallback) {
        this.productsCallback = productsCallback;
    }

    public void start(String authorization, ProductOrdering productOrdering)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ( ShopsAPIS.Base_URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create ( ShopsAPIS.class );
        Call<ProductOrdering> call = shopsAPIS.orderProduct (authorization,productOrdering);
        call.enqueue ( new Callback <ProductOrdering> () {
            @Override
            public void onResponse(Call <ProductOrdering> call, Response<ProductOrdering> response) {
                productsCallback.onResponse (response.body ());
            }

            @Override
            public void onFailure(Call <ProductOrdering> call, Throwable t) {

                productsCallback.onfailure ( t.getCause ().getMessage () );
            }
        } );
    }
}
