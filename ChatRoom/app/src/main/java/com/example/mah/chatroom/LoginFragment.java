package com.example.mah.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mah.chatroom.Data.ChatRoomAPI;
import com.example.mah.chatroom.Data.LogUserController;
import com.example.mah.chatroom.models.TokenResponse;
import com.example.mah.chatroom.models.User;

public class LoginFragment extends Fragment {

    private Button Login;
    private EditText UserName;
    private EditText Password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login , container ,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        introduction(view);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
                RoomsFragment roomsFragment = new RoomsFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.frame_container , roomsFragment)
                        .commit();
            }
        });


    }
    private void introduction(View view)
    {
        Login = view.findViewById(R.id.log_in_button);
        UserName = view.findViewById(R.id.login_user_name);
        Password = view.findViewById(R.id.login_password);
    }
    ChatRoomAPI.LoginUserCallback loginUserCallback = new ChatRoomAPI.LoginUserCallback() {
        @Override
        public void onResponse(boolean successful, String errorDescription, TokenResponse tokenResponse) {
            if(successful)
            {
                Toast.makeText(getActivity(), "successful" , Toast.LENGTH_LONG).show();
                MyprefrenceManager.getInstance(getActivity()).putUsername(UserName.getText().toString());
                MyprefrenceManager.getInstance(getActivity()).putAccessToken(tokenResponse.getAccessToken());
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent("login_ok"));
            }
        }

        @Override
        public void onFailure(String cause) {
            Toast.makeText(getActivity(), cause , Toast.LENGTH_SHORT).show();
        }
    };

    private void loginuser()
    {
        LogUserController logUserController = new LogUserController(loginUserCallback);
        TokenResponse tokenResponse = new TokenResponse();
        logUserController.start(UserName.getText().toString(),Password.getText().toString());
    }

}
