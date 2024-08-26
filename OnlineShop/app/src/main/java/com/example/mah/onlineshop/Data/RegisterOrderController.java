package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.RegisterOrder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterOrderController {
    ShopsAPIS.getRegisterOrderCallback getRegisterOrderCallback;

    public RegisterOrderController(ShopsAPIS.getRegisterOrderCallback getRegisterOrderCallback) {
        this.getRegisterOrderCallback = getRegisterOrderCallback;
    }

    public void start(String authorization , RegisterOrder registerOrder)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ( ShopsAPIS.Base_URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create ( ShopsAPIS.class );
        Call<RegisterOrder> call = shopsAPIS.registerOrder(authorization,registerOrder);
        call.enqueue ( new Callback <RegisterOrder> () {
            @Override
            public void onResponse(Call <RegisterOrder> call, Response<RegisterOrder> response) {
                getRegisterOrderCallback.onResponse (response.body ());
            }

            @Override
            public void onFailure(Call <RegisterOrder> call, Throwable t) {
                getRegisterOrderCallback.onFailure ( t.getCause ().getMessage () );

            }
        } );
    }
}
