package com.example.mah.onlineshop.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mah.onlineshop.Data.ShopsAPIS;
import com.example.mah.onlineshop.Data.SpeciphicationsController;
import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.dialogsPackage.NameDialog;
import com.example.mah.onlineshop.dialogsPackage.NameDialogSecond;
import com.example.mah.onlineshop.interfacesPackage.OnNameSelectedListener;
import com.example.mah.onlineshop.models.GetSpeciphications;
import com.example.mah.onlineshop.models.UserNameModel;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;
import com.example.mah.onlineshop.otherClasses.SpeciphicationsAdapter;

import java.util.List;

public class SpeciphicationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private SpeciphicationsAdapter speciphicationsAdapter;
    private OnNameSelectedListener onNameSelectedListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_speciphications , container ,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );


        NameDialog nameDialog = new NameDialog ( getActivity (), new OnNameSelectedListener () {
            @Override
            public void onNameSelected(String username) {

                UserNameModel userNameModel = new UserNameModel ();
                userNameModel.setUsername(username);

                SpeciphicationsController speciphicationsController = new SpeciphicationsController(getSpeciphicationsCallback);
                speciphicationsController.start ( "Bearer" + MypreferenceManager.getInstance ( getActivity () ).getAccessToken (), userNameModel);

            }
        } );
        nameDialog.show ();

        introduction(view);
    }

    ShopsAPIS.getSpeciphicationsCallback getSpeciphicationsCallback = new ShopsAPIS.getSpeciphicationsCallback () {
        @Override
        public void onResponse(List<GetSpeciphications> getSpeciphications) {

            configureLsit (getSpeciphications);

        }

        @Override
        public void onFailure(String cause) {

        }
    };

    private void configureLsit(List<GetSpeciphications> getSpeciphications)
    {
        speciphicationsAdapter = new SpeciphicationsAdapter(getSpeciphications);
        recyclerView.setAdapter(speciphicationsAdapter);
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );
    }

    private void introduction(View view)
    {
        recyclerView=(RecyclerView) view.findViewById ( R.id.recycler_speciphications );
    }
}
