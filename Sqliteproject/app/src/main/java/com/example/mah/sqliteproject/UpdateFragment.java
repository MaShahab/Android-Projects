package com.example.mah.sqliteproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFragment extends Fragment {

    private EditText id;
    private EditText name;
    private EditText family;
    private EditText age;

    private Button updateButton2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_update , container ,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        introduction(view);

        updateButton2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                dataUpdate ();
            }
        } );

    }

    private void introduction(View view)
    {
        id = (EditText) view.findViewById ( R.id.id_id );
        name = (EditText) view.findViewById ( R.id.id_name2 );
        family = (EditText) view.findViewById ( R.id.id_family2 );
        age = (EditText) view.findViewById ( R.id.id_age2 );
        updateButton2 = (Button) view.findViewById ( R.id.btn_update2 );
    }

    public void dataUpdate()
    {
        DataBaseHelper dataBaseHelper = new DataBaseHelper ( getActivity () );
        boolean isUpdate = dataBaseHelper.updateData(id.getText ().toString (),name.getText ().toString (),family.getText ().toString (),age.getText ().toString ());
        if(isUpdate==true)
        {
            Toast.makeText ( getActivity (), "The student with "+id.getText ().toString ()+ " id updated successfully", Toast.LENGTH_SHORT ).show ();
        }
        else {
            Toast.makeText ( getActivity (), "Unfortunately occurred a disorder to updating data ! ", Toast.LENGTH_SHORT ).show ();
        }
    }
}
