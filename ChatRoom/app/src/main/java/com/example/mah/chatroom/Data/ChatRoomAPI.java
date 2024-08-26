package com.example.mah.chatroom.Data;

import com.example.mah.chatroom.models.CreateNewRoom;
import com.example.mah.chatroom.models.Messages;
import com.example.mah.chatroom.models.MessagesResponse;
import com.example.mah.chatroom.models.Room;
import com.example.mah.chatroom.models.RoomId;
import com.example.mah.chatroom.models.RoomResponse;
import com.example.mah.chatroom.models.Sending;
import com.example.mah.chatroom.models.TokenResponse;
import com.example.mah.chatroom.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChatRoomAPI {

    String Base_URL = "https://api.backtory.com/";
    @Headers("X-Backtory-Authentication-Id:5a1d4b3de4b0afa16474fabd")
    @POST("auth/users")
    Call<User> registerUser(@Body User user);

    @Headers({"X-Backtory-Authentication-Id:5a1d4b3de4b0afa16474fabd" , "X-Backtory-Authentication-Key:5a1d4b3de4b0ce09cd4655c8"})
    @FormUrlEncoded
    @POST("auth/login")
    Call<TokenResponse> loginUser(@Field("username")String username , @Field("password") String password);

    @Headers("X-Backtory-Object-Storage-Id:5a1d4b3de4b03ffa047badf5")
    @POST("object-storage/classes/query/Room")
    Call<RoomResponse> getRooms (@Header("Authorization")String authorization);

    @Headers("X-Backtory-Object-Storage-Id:5a1d4b3de4b03ffa047badf5")
    @POST("object-storage/classes/query/Message")
    Call <MessagesResponse> getMessage(@Header("Authorization") String authorization , @Body RoomId roomId);

    @Headers("X-Backtory-Object-Storage-Id:5a1d4b3de4b03ffa047badf5")
    @POST("object-storage/classes/Message")
    Call<Sending> sendMessage (@Header("Authorization")String authorization , @Body Sending sending);

    @Headers("X-Backtory-Object-Storage-Id:5a1d4b3de4b03ffa047badf5")
    @POST("object-storage/classes/Room")
    Call<CreateNewRoom> createRoom (@Header("Authorization") String authorization , @Body CreateNewRoom createNewRoom);


    interface RegisterUserCallback
    {
        void onResponse(boolean successful , String errorMessage , User user);
        void onfailure(String cause);
    }

    interface LoginUserCallback
    {
        void onResponse(boolean successful ,String errorDescription , TokenResponse tokenResponse);
        void onFailure(String cause);
    }
    interface GetRoomsCallback
    {
        void onResponse(List<Room> roomList);
        void onFailure(String cause);
    }
    interface GetMessagesCallback
    {
        void onResponse(List<Messages> messagesList);
        void onFailure(String cause);
    }
    interface sendMessageCallback
    {
        void onResponse(Sending sending);
        void onFailure(String cause);
    }
    interface CreateRoomCallback
    {
        void onResponse(CreateNewRoom createNewRoom);
        void onFailure(String cause);
    }

}