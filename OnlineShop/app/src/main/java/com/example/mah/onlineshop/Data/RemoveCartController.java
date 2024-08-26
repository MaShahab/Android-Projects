package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.RemoveModel;
import com.example.mah.onlineshop.models.UserNameModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoveCartController {
    ShopsAPIS.getRemoveCartCallback getRemoveCartCallback;

    public RemoveCartController(ShopsAPIS.getRemoveCartCallback getRemoveCartCallback) {
        this.getRemoveCartCallback = getRemoveCartCallback;
    }

    public void start(String authorization , RemoveModel removeModel)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ( ShopsAPIS.Base_URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create ( ShopsAPIS.class );
        Call<RemoveModel> call = shopsAPIS.removeCart(authorization,removeModel);
        call.enqueue ( new Callback <RemoveModel> () {
            @Override
            public void onResponse(Call <RemoveModel> call, Response <RemoveModel> response) {
                getRemoveCartCallback.onResponse (response.body ());
            }

            @Override
            public void onFailure(Call <RemoveModel> call, Throwable t) {

            }
        } );

    }
}
