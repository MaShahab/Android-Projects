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

import com.example.mah.onlineshop.Data.InquiryController;
import com.example.mah.onlineshop.Data.ShopsAPIS;
import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.dialogsPackage.NameDialog;
import com.example.mah.onlineshop.interfacesPackage.OnNameSelectedListener;
import com.example.mah.onlineshop.models.InquiryInCartModel;
import com.example.mah.onlineshop.models.UserNameModel;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;
import com.example.mah.onlineshop.otherClasses.ProductInquiryAdapter;

import java.util.List;

public class InquiryFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragmnet_inquiry ,container ,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        introduction ( view );


        NameDialog nameDialog = new NameDialog ( getActivity (), new OnNameSelectedListener () {
            @Override
            public void onNameSelected(String username) {

                InquiryController inquiryController = new InquiryController(getInquiryCallback);

                UserNameModel userNameModel = new UserNameModel ();
                userNameModel.setUsername ( username );

                inquiryController.start("Bearer" + MypreferenceManager.getInstance ( getActivity () ).getAccessToken (),userNameModel);

            }
        } );
        nameDialog.show ();



//        String orderUsername = getArguments ().getString("username_order2");



        String userID = MypreferenceManager.getInstance ( getActivity () ).getUsernameOrder ();



    }

    private ShopsAPIS.getInquiryCallback getInquiryCallback = new ShopsAPIS.getInquiryCallback () {
        @Override
        public void onResponse(List <InquiryInCartModel> inquiryInCartModels) {
            configList(inquiryInCartModels,MypreferenceManager.getInstance ( getActivity () ).getUsernameOrder ());
        }

        @Override
        public void onFailure(String cause) {

        }
    };

    private void introduction(View view)
    {
        recyclerView=(RecyclerView) view.findViewById ( R.id.recycler_inquiry );
    }

    private void configList(List<InquiryInCartModel> inquiryInCartModels , String userName)
    {
//        InquiryInCartModel inquiryInCartModel = new InquiryInCartModel ();
        ProductInquiryAdapter productInquiryAdapter = new ProductInquiryAdapter(inquiryInCartModels,userName);
        recyclerView.setAdapter ( productInquiryAdapter );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );
    }
}
