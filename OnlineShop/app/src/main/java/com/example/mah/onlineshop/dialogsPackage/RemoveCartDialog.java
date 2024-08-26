package com.example.mah.onlineshop.dialogsPackage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.interfacesPackage.OnNameSelectedListener;

public class RemoveCartDialog extends Dialog {

    private EditText myeditText;
    private Button mybutton;
    private OnNameSelectedListener onNameSelectedListener4;

    public RemoveCartDialog(@NonNull Context context , OnNameSelectedListener onNameSelectedListener4) {
        super ( context );
        this.onNameSelectedListener4 = onNameSelectedListener4;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.dialog_name_remove );

        getWindow ().setLayout(1000,1700);

        introduction ();

        mybutton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                onNameSelectedListener4.onNameSelected(myeditText.getText ().toString ());
                dismiss ();

            }
        } );

    }

    private void introduction()
    {
        myeditText=(EditText) findViewById ( R.id.edit_username4 );
        mybutton=(Button) findViewById ( R.id.btn_reg3 );
    }
}