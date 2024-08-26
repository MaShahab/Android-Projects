package com.example.mah.testproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SecondFragment extends Fragment {
    private TextView txt;
    private CoordinatorLayout coordinatorLayout;
    private MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_second,container,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        txt=(TextView) view.findViewById ( R.id.id_fragment_second );
        coordinatorLayout=(CoordinatorLayout)view.findViewById ( R.id.snack_id );
        MyModels myModels = new MyModels();

        Toast.makeText ( getActivity (), "name : ", Toast.LENGTH_SHORT ).show ();
        Snackbar snackbar = Snackbar.make(coordinatorLayout,"simple snackbar" , Snackbar.LENGTH_LONG);
        snackbar.show ();

        Intent intent = getActivity ().getIntent ();
        String myname = intent.getStringExtra ( "nameid" );
        Toast.makeText ( getActivity (), "name : "+myname, Toast.LENGTH_SHORT ).show ();

        String name = myModels.getName ();

        txt.setText ( name );

//        Intent intent = new Intent ( Intent.ACTION_VIEW, Uri.parse("http://www.shahab95.ir") );
//        startActivity ( intent );
    }
}
