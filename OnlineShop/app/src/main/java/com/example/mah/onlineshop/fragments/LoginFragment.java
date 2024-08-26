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
import com.example.mah.onlineshop.Data.ShopsAPIS;
import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.TokenResponse;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;

public class LoginFragment extends Fragment {

    public EditText userenamedit;
    private EditText passedit;
    private EditText emailedit;
    private Button submitbtn;

    private CheckBox checkBox;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_login,container,false );
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

        submitbtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                LoginUserController loginUserController = new LoginUserController(loginusercallback);
                loginUserController.start(userenamedit.getText ().toString (),passedit.getText ().toString ());
            }
        } );
    }


    private void introduction(View view)
    {
        userenamedit=(EditText) view.findViewById ( R.id.user_id );
        passedit=(EditText) view.findViewById ( R.id.pass_id );
        emailedit=(EditText) view.findViewById ( R.id.email_id );
        submitbtn=(Button) view.findViewById ( R.id.login_btn );
        checkBox=(CheckBox) view.findViewById ( R.id.checkbox_login );
    }

    ShopsAPIS.Loginusercallback loginusercallback = new ShopsAPIS.Loginusercallback () {
        @Override
        public void onResponse(boolean successful, String errorDescription, TokenResponse tokenResponse) {
            Toast.makeText ( getActivity (), "انجام شد", Toast.LENGTH_LONG ).show ();
//            Toast.makeText ( getActivity (),""+ tokenResponse.getAccessToken (), Toast.LENGTH_SHORT ).show ();
            MypreferenceManager.getInstance ( getActivity () ).putAccesstoken (tokenResponse.getAccessToken ());
            LocalBroadcastManager.getInstance ( getActivity () ).sendBroadcast ( new Intent ( "open_products" ) );
//            MypreferenceManager.getInstance ( getActivity () ).putUsernameSignOut (String.valueOf(userenamedit));

            MypreferenceManager.getInstance ( getActivity () ).putUsernameSignOut(userenamedit.getText().toString());
            MypreferenceManager.getInstance ( getActivity () ).putPassSignOut ( passedit.getText ().toString () );
            MypreferenceManager.getInstance ( getActivity () ).putEmailsignout ( emailedit.getText ().toString () );
        }

        @Override
        public void onFailure(String cause) {

        }
    };
}
