package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.AddAddressModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddAddressController {
    ShopsAPIS.addAddressCallBack addAddressCallBack;

    public AddAddressController(ShopsAPIS.addAddressCallBack addAddressCallBack) {
        this.addAddressCallBack = addAddressCallBack;
    }

    public void start(String authorization , AddAddressModel addAddressModel)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ( ShopsAPIS.Base_URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create ( ShopsAPIS.class );
        Call <AddAddressModel> call = shopsAPIS.addAdress ( authorization,addAddressModel );
        call.enqueue ( new Callback <AddAddressModel> () {
            @Override
            public void onResponse(Call <AddAddressModel> call, Response<AddAddressModel> response) {
                addAddressCallBack.onResponse ( response.body () );
            }

            @Override
            public void onFailure(Call <AddAddressModel> call, Throwable t) {
                addAddressCallBack.onFailure ( t.getCause ().getMessage () );

            }
        } );
    }
}
