package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.CommentsResponse;
import com.example.mah.onlineshop.models.MyID;
import com.example.mah.onlineshop.models.ProductsId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentsController {
    private ShopsAPIS.ProductsCommentsCallback commentsCallback;

    public CommentsController(ShopsAPIS.ProductsCommentsCallback commentsCallback) {
        this.commentsCallback = commentsCallback;
    }

    public void start(String authorization , MyID myID)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ( ShopsAPIS.Base_URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create ( ShopsAPIS.class );
        Call<CommentsResponse> call = shopsAPIS.getComments(authorization,myID);
        call.enqueue ( new Callback <CommentsResponse> () {
            @Override
            public void onResponse(Call <CommentsResponse> call, Response<CommentsResponse> response) {
                commentsCallback.onResponse ( response.body ().getComments () );
            }

            @Override
            public void onFailure(Call <CommentsResponse> call, Throwable t) {
                commentsCallback.onFailure ( t.getCause ().getMessage () );
            }
        } );
    }
}
