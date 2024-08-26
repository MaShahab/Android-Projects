package com.example.mah.onlineshop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mah.onlineshop.Data.LoginUserController;
import com.example.mah.onlineshop.Data.RegisterUserController;
import com.example.mah.onlineshop.Data.ShopsAPIS;
import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.TokenResponse;
import com.example.mah.onlineshop.models.User;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;
import com.google.gson.Gson;

public class RegisterFragment extends Fragment {

    private EditText usernameedit;
    private EditText passedit;
    private EditText emailedit;
    private Button submit;
    private Button here;

    private CheckBox checkBox;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_register,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );
        introduction (view);


        checkBox.setChecked(true);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener () {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    passedit.setTransformationMethod( PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    passedit.setTransformationMethod( HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        here.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                LoginFragment loginFragment = new LoginFragment ();
                getFragmentManager ().beginTransaction ()
                        .add ( R.id.frame_container,loginFragment )
                        .addToBackStack ( null )
                        .commit ();
            }
        } );

        submit.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                RegisterUserController registerUserController = new RegisterUserController(registerUserCallback);
                LoginUserController loginUserController = new LoginUserController(loginusercallback);
                User user = new User ();
                user.setUsername(usernameedit.getText ().toString ());
                user.setPassword(passedit.getText ().toString ());
                user.setEmail(emailedit.getText ().toString ());
                registerUserController.start(user);
                loginUserController.start ( usernameedit.getText ().toString (),passedit.getText ().toString () );
                MypreferenceManager.getInstance ( getActivity () ).putUsername (usernameedit.getText ().toString ());
//                LocalBroadcastManager.getInstance ( getActivity () ).sendBroadcast ( new Intent ( "open_products" ) );


            }
        } );


    }

    ShopsAPIS.RegisterUserCallback registerUserCallback = new ShopsAPIS.RegisterUserCallback () {
        @Override
        public void onResponse(boolean successful, String errormessage, User user) {
            if(successful)
            {
                Toast.makeText (getActivity (),"Done" + user.getUsername () + "created", Toast.LENGTH_LONG ).show ();

//                MypreferenceManager.getInstance ( getActivity () ).putUsernameSignOut ( user.getUsername () );

//                TokenResponse tokenResponse = new TokenResponse ();
//                Toast.makeText ( getActivity (),""+ tokenResponse.getAccessToken (), Toast.LENGTH_SHORT ).show ();
            }
            else {
                Toast.makeText ( getActivity (), errormessage, Toast.LENGTH_LONG ).show ();
            }
        }

        @Override
        public void onFailure(String cause) {

        }
    };

    ShopsAPIS.Loginusercallback loginusercallback = new ShopsAPIS.Loginusercallback () {
        @Override
        public void onResponse(boolean successful, String errorDescription, TokenResponse tokenResponse) {
//            Gson gson = new Gson ();
//            String tokenStringed = gson.toJson(tokenResponse,TokenResponse.class);
////            Toast.makeText ( getActivity (), "access_token :" + tokenStringed, Toast.LENGTH_SHORT ).show ();
////            MypreferenceManager.getInstance ( getActivity () ).putAccesstoken (tokenResponse.getAccessToken ());
////            Toast.makeText ( getActivity (),""+ tokenResponse.getAccessToken (), Toast.LENGTH_SHORT ).show ();

            Toast.makeText ( getActivity (), "انجام شد", Toast.LENGTH_LONG ).show ();
//            Toast.makeText ( getActivity (),""+ tokenResponse.getAccessToken (), Toast.LENGTH_SHORT ).show ();
            MypreferenceManager.getInstance ( getActivity () ).putAccesstoken (tokenResponse.getAccessToken ());
            LocalBroadcastManager.getInstance ( getActivity () ).sendBroadcast ( new Intent ( "open_products" ) );

            MypreferenceManager.getInstance ( getActivity () ).putUsernameSignOut(usernameedit.getText().toString());
            MypreferenceManager.getInstance ( getActivity () ).putPassSignOut ( passedit.getText ().toString () );
            MypreferenceManager.getInstance ( getActivity () ).putEmailsignout ( emailedit.getText ().toString () );
        }

        @Override
        public void onFailure(String cause) {

        }
    };

    private void introduction(View view)
    {
        usernameedit=(EditText) view.findViewById ( R.id.user_id );
        passedit=(EditText) view.findViewById ( R.id.pass_id );
        emailedit=(EditText) view.findViewById ( R.id.email_id );
        submit=(Button)view.findViewById ( R.id.register_btn );
        here=(Button)view.findViewById ( R.id.here_btn );
        checkBox=(CheckBox) view.findViewById ( R.id.checkbox_register );
    }
    private void startProductsFrag()
    {
        ProductsFragment productsFragment = new ProductsFragment ();

    }
}
