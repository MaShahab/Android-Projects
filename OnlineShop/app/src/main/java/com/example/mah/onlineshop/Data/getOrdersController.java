package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.OrderResponse;
import com.example.mah.onlineshop.models.UserNameModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class getOrdersController {
    private ShopsAPIS.getOrdersCallback getOrdersCallback;

    public getOrdersController(ShopsAPIS.getOrdersCallback getOrdersCallback) {
        this.getOrdersCallback = getOrdersCallback;
    }


    public void start(String authorization , UserNameModel userNameModel)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ( ShopsAPIS.Base_URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create ( ShopsAPIS.class );
        Call<OrderResponse> call = shopsAPIS.getMyOrders ( authorization,userNameModel );
        call.enqueue ( new Callback <OrderResponse> () {
            @Override
            public void onResponse(Call <OrderResponse> call, Response<OrderResponse> response) {
                getOrdersCallback.onResponse ( response.body ().getRegisterOrderList () );
            }

            @Override
            public void onFailure(Call <OrderResponse> call, Throwable t) {
                getOrdersCallback.onFailure ( t.getCause ().getMessage () );
            }
        } );
    }

}
