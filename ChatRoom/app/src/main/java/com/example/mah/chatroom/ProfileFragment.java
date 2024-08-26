package com.example.mah.chatroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    private TextView Username;
    private Button Logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile , container ,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        introduction(view);
        Username.setText("User name : " + MyprefrenceManager.getInstance(getActivity()).getUSername());
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyprefrenceManager.getInstance(getActivity()).putAccessToken(null);
                getActivity().finish();
            }
        });
    }
    private void introduction(View view)
    {
        Username = (TextView) view.findViewById(R.id.profile_user);
        Logout = (Button) view.findViewById(R.id.logout_button);
    }
}
