package com.example.mah.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateDataFrag extends Fragment {

    private EditText title;
    private EditText content;

    private Button register;

    MyDatabase myDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_update_data , container ,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        introduction(view);

        final DatasModel datasModel = (DatasModel) getArguments ().getSerializable("serialize_dataModel");

        title.setText(datasModel.getTitle ());
        content.setText ( datasModel.getContent () );

        register.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                myDatabase = new MyDatabase(getActivity ());

                Date date = Calendar.getInstance ().getTime ();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                String updateDate = simpleDateFormat.format(date);

                Date date1 = new Date();
                SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
                String updateTime = df.format(date1);

                boolean isUpdate = myDatabase.updateData(datasModel.getID (),title.getText ().toString (),content.getText ().toString (),updateDate,updateTime);
                if(isUpdate == true)
                {
                    Toast.makeText ( getActivity (), "اطلاعات با موفقیت بروزرسانی شد", Toast.LENGTH_LONG ).show ();
//                    DataListFragment dataListFragment = new DataListFragment ();
//                    getFragmentManager ().beginTransaction ()
//                            .add ( R.id.frame_container,dataListFragment )
//                            .commit ();
                    startActivity(new Intent ( getActivity (),MainActivity.class ));
                }
                else
                {
                    Toast.makeText ( getActivity (), "بروز رسانی اطلاعات با مشکل مواجه شده است!", Toast.LENGTH_SHORT ).show ();
                }

            }
        } );

    }

    private void introduction(View view)
    {
        title = (EditText) view.findViewById ( R.id.edit_title_update );
        content = (EditText) view.findViewById ( R.id.id_content_update );
        register = (Button) view.findViewById ( R.id.btn_reg_update );
    }
}
