package com.example.mah.jsonapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GetNameDialog extends Dialog {

    private Button Confirm;
    private EditText editText;
    private OnNameSelectedListener listener;

    public GetNameDialog(@NonNull Context context, OnNameSelectedListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_get_name);

        introduction();

        getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT , ConstraintLayout.LayoutParams.MATCH_PARENT);

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onNameSelected(editText.getText().toString());

            }
        });

    }

    private void introduction()
    {
        Confirm=(Button) findViewById(R.id.confirm_button);
        editText=(EditText) findViewById(R.id.my_edit);


    }
}
