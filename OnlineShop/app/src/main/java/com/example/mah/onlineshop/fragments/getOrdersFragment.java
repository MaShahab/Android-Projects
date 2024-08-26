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
import com.example.mah.onlineshop.Data.getOrdersController;
import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.dialogsPackage.UserNameOrderDialog;
import com.example.mah.onlineshop.interfacesPackage.OnNameSelectedListener;
import com.example.mah.onlineshop.models.ComponentsModel;
import com.example.mah.onlineshop.models.RegisterOrder;
import com.example.mah.onlineshop.models.UserNameModel;
import com.example.mah.onlineshop.otherClasses.MyInnerOrdersAdapter;
import com.example.mah.onlineshop.otherClasses.MyOrdersAdapter;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;

import java.util.List;

public class getOrdersFragment extends Fragment {

    private RecyclerView recyclerViewOrders;
    private MyOrdersAdapter myOrdersAdapter;
    private RecyclerView recyclerView;
    private MyInnerOrdersAdapter myInnerOrdersAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_get_orders , container ,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        introduction ( view );

        UserNameOrderDialog userNameOrderDialog = new UserNameOrderDialog ( getActivity (), new OnNameSelectedListener () {
            @Override
            public void onNameSelected(String username) {

                getOrdersController getOrdersController1 = new getOrdersController(getOrdersCallback);
                UserNameModel userNameModel = new UserNameModel ();
                userNameModel.setUsername(username);
                getOrdersController1.start ( "Bearer"+ MypreferenceManager.getInstance ( getActivity () ).getAccessToken (), userNameModel);

            }
        } );
        userNameOrderDialog.show ();

    }



    ShopsAPIS.getOrdersCallback getOrdersCallback = new ShopsAPIS.getOrdersCallback () {
        @Override
        public void onResponse(List<RegisterOrder> registerOrders) {
            configureList(registerOrders);

        }

        @Override
        public void onFailure(String cause) {

        }
    };


    private void introduction(View view)
    {
        recyclerViewOrders=(RecyclerView) view.findViewById ( R.id.recycler_list_orders );
        recyclerView=(RecyclerView) view.findViewById ( R.id.recycler_inner );
    }

    private void configureList(List<RegisterOrder> registerOrders)
    {
        myOrdersAdapter = new MyOrdersAdapter(registerOrders);
        recyclerViewOrders.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );
        recyclerViewOrders.setAdapter ( myOrdersAdapter );
    }
    private void configuration(List<ComponentsModel> componentsModels)
    {
        myInnerOrdersAdapter= new MyInnerOrdersAdapter ( componentsModels );
        recyclerView.setAdapter(myInnerOrdersAdapter);
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );
    }
}
