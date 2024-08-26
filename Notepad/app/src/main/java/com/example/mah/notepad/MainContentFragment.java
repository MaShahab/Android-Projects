package com.example.mah.notepad;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainContentFragment extends Fragment {

    private TextView content;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_content , container ,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        content = (TextView) view.findViewById ( R.id.main_content );
        imageView = (ImageView) view.findViewById ( R.id.scheme_copy );

        String maincontent = getArguments ().getString("content_text");

        content.setText (maincontent);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager)getActivity ().getSystemService( Context.CLIPBOARD_SERVICE);
                cm.setText(content.getText());
                Toast.makeText(getActivity (), "محتوا با موفقیت کپی شد", Toast.LENGTH_LONG).show();
            }
        });
    }
}
