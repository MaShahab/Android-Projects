package com.example.mah.whichoneislarger;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GameDialog extends Dialog
{
    private EditText editText;
    private Button Confirm;
    private OnnameSelectedListener listener;

    public GameDialog(@NonNull Context context, OnnameSelectedListener listener) {
        super ( context );
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_game);

        getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT , ConstraintLayout.LayoutParams.MATCH_PARENT);

        introduction();

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.OnNameSelected(editText.getText().toString());
                dismiss();

            }
        });


    }

    private void introduction()
    {
        editText=(EditText) findViewById(R.id.user_edit);
        Confirm=(Button) findViewById(R.id.user_button);
    }
}
