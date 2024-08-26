package com.example.mah.onlineshop.fragments;

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

import com.example.mah.onlineshop.Data.AddAddressController;
import com.example.mah.onlineshop.Data.ShopsAPIS;
import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.AddAddressModel;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;

public class AddAdressFragment extends Fragment {

    private EditText userEdit;
    private EditText phoneEdit;
    private EditText addressEdit;

    private Button registerIntroduction;
    private Button hereBtn2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragmnet_add_address , container ,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        introduction (view);

        registerIntroduction.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                AddAddressModel addAddressModel = new AddAddressModel ();
                addAddressModel.setUsername(userEdit.getText ().toString ());
                addAddressModel.setPhoneNumber(phoneEdit.getText ().toString ());
                addAddressModel.setDetail (addressEdit.getText ().toString ());

                AddAddressController addAddressController = new AddAddressController ( addAddressCallBack );

                addAddressController.start("Bearer" + MypreferenceManager.getInstance ( getActivity () ).getAccessToken (),addAddressModel);

            }
        } );

        hereBtn2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                SpeciphicationsFragment speciphicationsFragment = new SpeciphicationsFragment ();
                getFragmentManager ().beginTransaction ()
                        .add ( R.id.frame_container,speciphicationsFragment )
                        .addToBackStack ( null )
                        .commit ();
            }
        } );

    }

    ShopsAPIS.addAddressCallBack addAddressCallBack = new ShopsAPIS.addAddressCallBack () {
        @Override
        public void onResponse(AddAddressModel addAddressModel) {
            Toast.makeText ( getActivity (), "مشخصات با موفقیت ثبت گردید", Toast.LENGTH_LONG ).show ();

        }

        @Override
        public void onFailure(String cause) {

        }
    };

    private void introduction(View view)
    {
        userEdit=(EditText) view.findViewById ( R.id.username_id );
        phoneEdit=(EditText) view.findViewById ( R.id.id_phone_number );
        addressEdit=(EditText) view.findViewById ( R.id.id_address );
        registerIntroduction=(Button) view.findViewById ( R.id.btn_reg_id );
        hereBtn2=(Button) view.findViewById ( R.id.here_btn2 );
    }
}
