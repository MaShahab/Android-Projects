package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.AddAddressModel;
import com.example.mah.onlineshop.models.CommentModels;
import com.example.mah.onlineshop.models.CommentsResponse;
import com.example.mah.onlineshop.models.DetailsResponse;
import com.example.mah.onlineshop.models.GetSpeciphications;
import com.example.mah.onlineshop.models.InquiryInCartModel;
import com.example.mah.onlineshop.models.InquiryResponse;
import com.example.mah.onlineshop.models.MyID;
import com.example.mah.onlineshop.models.OrderResponse;
import com.example.mah.onlineshop.models.PeoductsModels2;
import com.example.mah.onlineshop.models.ProductOrdering;
import com.example.mah.onlineshop.models.ProductsId;
import com.example.mah.onlineshop.models.ProductsModels;
import com.example.mah.onlineshop.models.ProductsResponse;
import com.example.mah.onlineshop.models.RegisterOrder;
import com.example.mah.onlineshop.models.RemoveModel;
import com.example.mah.onlineshop.models.SpeciphicationsResponse;
import com.example.mah.onlineshop.models.TokenResponse;
import com.example.mah.onlineshop.models.User;
import com.example.mah.onlineshop.models.UserNameModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ShopsAPIS {

    String Base_URL = "https://api.backtory.com/";

    @Headers("X-Backtory-Authentication-Id:5a154d2fe4b03ffa0436a534")
    @POST("auth/users")
    Call<User> registerUser (@Body User user);

    @Headers({"X-Backtory-Authentication-Id:5a154d2fe4b03ffa0436a534","X-Backtory-Authentication-Key:57bdf2629df04f46a31de972"})
    @FormUrlEncoded
    @POST("auth/login")
    Call<TokenResponse> loginUser (@Field ("username") String username , @Field ( "password" ) String password);

    @Headers("X-Backtory-Object-Storage-Id:5a154d2fe4b03ffa0436a535")
    @POST("object-storage/classes/query/Product")
    Call<ProductsResponse> showProducts (@Header ("authorization") String authorization);

    @Headers("X-Backtory-Object-Storage-Id:5a154d2fe4b03ffa0436a535")
    @POST("object-storage/classes/query/Product")
    Call <DetailsResponse> getDetails (@Header ("authorization") String authorization , @Body ProductsId productsId);

    @Headers("X-Backtory-Object-Storage-Id:5a154d2fe4b03ffa0436a535")
    @POST("object-storage/classes/query/Comment")
    Call<CommentsResponse> getComments (@Header ("authorization") String authorization , @Body MyID myID);

    @Headers ("X-Backtory-Object-Storage-Id:5a154d2fe4b03ffa0436a535")
    @POST("object-storage/classes/Basket")
    Call<ProductOrdering> orderProduct (@Header ( "authorization" ) String authorization , @Body ProductOrdering productOrdering);

    @Headers("X-Backtory-Object-Storage-Id:5a154d2fe4b03ffa0436a535")
    @POST("object-storage/classes/query/Basket")
    Call<InquiryResponse> getInquiry (@Header ( "authorization" ) String authorization , @Body UserNameModel userNameModel);

    @Headers("X-Backtory-Object-Storage-Id:5a154d2fe4b03ffa0436a535")
    @POST("object-storage/classes/Address")
    Call<AddAddressModel> addAdress (@Header ( "authorization" ) String authorization , @Body AddAddressModel addAddressModel);

    @Headers("X-Backtory-Object-Storage-Id:5a154d2fe4b03ffa0436a535")
    @POST("object-storage/classes/query/Address")
    Call<SpeciphicationsResponse> getMySpeciphications (@Header ( "authorization" ) String authorization ,@Body UserNameModel userNameModel);

    @Headers("X-Backtory-Object-Storage-Id:5a154d2fe4b03ffa0436a535")
    @HTTP(method = "DELETE" ,path="object-storage/classes/Basket",hasBody = true)
    Call <RemoveModel> removeCart (@Header ( "authorization" ) String authorization , @Body RemoveModel removeModel);

    @Headers("X-Backtory-Object-Storage-Id:5a154d2fe4b03ffa0436a535")
    @POST("object-storage/classes/Order")
    Call<RegisterOrder> registerOrder (@Header ( "authorization" ) String authorization , @Body RegisterOrder registerOrder);

    @Headers("X-Backtory-Object-Storage-Id:5a154d2fe4b03ffa0436a535")
    @POST("object-storage/classes/query/Order")
    Call<OrderResponse> getMyOrders (@Header ( "authorization" ) String authorization , @Body UserNameModel userNameModel);

    //**********************************************************************************************
    interface RegisterUserCallback
    {
        void onResponse(boolean successful , String errormessage , User user);
        void onFailure(String cause);
    }

    interface Loginusercallback
    {
        void onResponse(boolean successful , String errorDescription , TokenResponse tokenResponse);
        void onFailure(String cause);
    }

    interface ProductsCallback
    {
        void onResponse(List<ProductsModels> products);
        void onFailure(String cause);
    }

    interface ProductsDetailsCallback
    {
        void onResponse(List<PeoductsModels2> peoductsModels2s);
        void onFailure(String cause);
    }

    interface ProductsCommentsCallback
    {
        void onResponse(List<CommentModels> commentModels);
        void onFailure(String cause);
    }

    interface OrderProductsCallback
    {
        void onResponse(ProductOrdering productOrdering);
        void onfailure(String cause);
    }

    interface getInquiryCallback
    {
        void onResponse(List<InquiryInCartModel> inquiryInCartModels);
        void onFailure(String cause);
    }

    interface addAddressCallBack
    {
        void onResponse(AddAddressModel addAddressModel);
        void onFailure(String cause);
    }

    interface getSpeciphicationsCallback
    {
        void onResponse(List<GetSpeciphications> getSpeciphications);
        void onFailure(String cause);
    }

    interface getRemoveCartCallback
    {
        void onResponse(RemoveModel removeModel);
        void onFailure(String cause);
    }

    interface getRegisterOrderCallback
    {
        void onResponse(RegisterOrder registerOrder);
        void onFailure(String cause);
    }

    interface getOrdersCallback
    {
        void onResponse(List<RegisterOrder> registerOrders);
        void onFailure(String cause);
    }
}
