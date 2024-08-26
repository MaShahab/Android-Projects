package com.example.mah.chatroom;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mah.chatroom.Data.ChatRoomAPI;
import com.example.mah.chatroom.Data.LogUserController;
import com.example.mah.chatroom.Data.RegisterUSerController;
import com.example.mah.chatroom.models.TokenResponse;
import com.example.mah.chatroom.models.User;


public class RegisterFragment extends Fragment {

    private EditText UserName;
    private EditText Password;
    private EditText Email;
    private Button Register;
    private Button Login;
    private TokenResponse tokenResponse;
    private TextView developerShow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register , container , false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        introduction(view);
        ObjectAnimator show = ObjectAnimator.ofFloat(developerShow,"Alpha" , 1,0);
        show.setDuration(4000);
        show.setStartDelay(7000);
        show.start();
        loginuser();
//***************************************************************************************************************************************
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUSerController registerUSerController = new RegisterUSerController(registerUserCallback);
                User user = new User();
                user.setUsername(UserName.getText().toString());
                user.setEmail(Email.getText().toString());
                user.setPassword(Password.getText().toString());
                registerUSerController.start(user);

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment loginFragment = new LoginFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.frame_container , loginFragment)
                        .commit();
            }
        });
    }

        ChatRoomAPI.RegisterUserCallback registerUserCallback = new ChatRoomAPI.RegisterUserCallback() {
            @Override
            public void onResponse(boolean successful , String errorMessage , User user) {
                if(successful) {
                    Toast.makeText(getActivity(), "Done " +user.getUsername() + " created !", Toast.LENGTH_LONG).show();
                    loginuser();
                }
                else {
                    Toast.makeText(getActivity(),errorMessage, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onfailure(String cause) {
                Toast.makeText(getActivity(), cause , Toast.LENGTH_SHORT).show();
            }
        };
//******************************************************************************************************************************************

//******************************************************************************************************************************************
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
    //****************************************************************************************************************************************
    private void introduction (View view)
    {
        UserName = (EditText) view.findViewById(R.id.user_name);
        Password = (EditText) view.findViewById(R.id.password);
        Email = (EditText) view.findViewById(R.id.email);
        Register = (Button) view.findViewById(R.id.register);
        Login = (Button) view.findViewById(R.id.log_in);
        developerShow = (TextView) view.findViewById(R.id.producer);
    }
}
