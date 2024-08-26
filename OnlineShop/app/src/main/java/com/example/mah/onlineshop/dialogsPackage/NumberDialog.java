package com.example.mah.onlineshop.dialogsPackage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.fragments.InquiryFragment;
import com.example.mah.onlineshop.interfacesPackage.OnNumberSelectedListener;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;

public class NumberDialog extends Dialog {

    private EditText editNumbers;
    private EditText editUsername;
    private Button buttonNumbers;
    private OnNumberSelectedListener onNumberSelectedListener;

    public NumberDialog(@NonNull Context context , OnNumberSelectedListener onNumberSelectedListener) {
        super ( context );
        this.onNumberSelectedListener=onNumberSelectedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView( R.layout.dialog_order);

        introduction ();

        getWindow ().setLayout(1000,1700);

        buttonNumbers.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                onNumberSelectedListener.onNumberSelected(editNumbers.getText ().toString (),editUsername.getText ().toString ());
//                onNumberSelectedListener.onUsernameSelected ( editUsername.getText ().toString () );
                MypreferenceManager.getInstance ( getContext () ).putUsernameOrder ( editUsername.getText ().toString () );


                InquiryFragment inquiryFragment = new InquiryFragment ();
                Bundle bundle = new Bundle ();
                bundle.putString("username_order2",editUsername.getText ().toString ());
                inquiryFragment.setArguments(bundle);

                dismiss ();

            }
        } );


    }

    private void introduction()
    {
        editNumbers=(EditText) findViewById ( R.id.edit_numbers );
        buttonNumbers=(Button) findViewById ( R.id.btn_number );
        editUsername=(EditText) findViewById ( R.id.edit_username );
    }
}
