package com.example.mah.onlineshop.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.User;
import com.example.mah.onlineshop.otherClasses.MyFragmnetPagerAdapter;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;

public class signOutFragmnet extends Fragment {

    private TextView usernameOut;
    private TextView passOut;
    private TextView emailOut;
    private Button signOutbtn;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_sign_out , container ,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        introduction(view);

//        Snackbar snackbar = Snackbar.make ( coordinatorLayout,"پسورد رمز نکاری شده است" ,Snackbar.LENGTH_LONG);
//        snackbar.show ();

        usernameOut.setText ("نام کاربری : " + MypreferenceManager.getInstance ( getActivity () ).getUsernameSignOut () );
        passOut.setText("رمز عبور : " + MypreferenceManager.getInstance ( getActivity () ).getPassSignOut ().hashCode ());
        emailOut.setText("ایمیل : "+MypreferenceManager.getInstance ( getActivity () ).getEmailSignOut ());

        signOutbtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                MypreferenceManager.getInstance(getActivity()).putAccesstoken (null);
                getActivity().finish();
            }
        } );
    }


    private void introduction(View view)
    {
        usernameOut=(TextView) view.findViewById ( R.id.username_out );
        passOut=(TextView)view.findViewById ( R.id.password_out );
        emailOut=(TextView) view.findViewById ( R.id.email_out );
        signOutbtn=(Button) view.findViewById ( R.id.btn_sign_out );
        textView=(TextView) view.findViewById ( R.id.password_hashed );
    }
}
