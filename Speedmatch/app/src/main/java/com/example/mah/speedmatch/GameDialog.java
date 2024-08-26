package com.example.mah.speedmatch;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GameDialog extends Dialog {

    private EditText editTextUSer;
    private Button confirmButton;
    private OnNameSelctedListener listener;

    public GameDialog(@NonNull Context context , OnNameSelctedListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_game);

        getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT , ConstraintLayout.LayoutParams.MATCH_PARENT);

        introduction();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNameSelected(editTextUSer.getText().toString());
                dismiss();

            }
        });

    }

    private void introduction()
    {
        editTextUSer = (EditText) findViewById(R.id.user_name_edit);
        confirmButton = (Button) findViewById(R.id.confirm_button);
    }
}
