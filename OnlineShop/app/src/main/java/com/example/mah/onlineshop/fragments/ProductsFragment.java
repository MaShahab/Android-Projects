package com.example.mah.onlineshop.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mah.onlineshop.Data.ProductsController;
import com.example.mah.onlineshop.Data.RemoveCartController;
import com.example.mah.onlineshop.Data.ShopsAPIS;
import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.dialogsPackage.RemoveCartDialog;
import com.example.mah.onlineshop.interfacesPackage.OnNameSelectedListener;
import com.example.mah.onlineshop.models.ProductsModels;
import com.example.mah.onlineshop.models.RemoveModel;
import com.example.mah.onlineshop.models.UserNameModel;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;
import com.example.mah.onlineshop.otherClasses.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment{

    private RecyclerView recyclerView;
    private List<ProductsModels> modelsList = new ArrayList <> ();
    private ProductsAdapter productsAdapter;
    private Button buttonInquiry;
    private Button buttonRemove;
    private ProgressBar progressBar;
//    private TextView errorTxt;
//    private Button BTN;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_list_products,container,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );
        ProductsController productsController = new ProductsController(productsCallback);
        productsController.start( "Bearer" + MypreferenceManager.getInstance (getActivity ()).getAccessToken ());
        introduction (view);
//        progressBar.setVisibility ( View.VISIBLE );
        Log.d ("TAG" , "product name" + productsAdapter);

//        Toast.makeText ( getActivity (), "access token : "+MypreferenceManager.getInstance ( getActivity ()).getAccessToken (), Toast.LENGTH_SHORT ).show ();

//        BTN.setOnClickListener ( new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                TestFrag testFrag = new TestFrag ();
//                getFragmentManager ().beginTransaction ()
//                        .add( R.id.frame_container,testFrag )
//                        .addToBackStack ( null )
//                        .commit ();
//            }
//        } );


        buttonInquiry.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                InquiryFragment inquiryFragment = new InquiryFragment ();
                getFragmentManager ().beginTransaction ()
                        .add ( R.id.frame_container,inquiryFragment )
                        .addToBackStack ( null )
                        .commit ();
            }
        } );

        buttonRemove.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                RemoveCartDialog removeCartDialog = new RemoveCartDialog ( getActivity (), new OnNameSelectedListener () {
                    @Override
                    public void onNameSelected(String username) {
                        RemoveCartController removeCartController = new RemoveCartController(getRemoveCartCallback);
//                        UserNameModel userNameModel = new UserNameModel ();
//                        userNameModel.setUsername(username);
                        RemoveModel removeModel = new RemoveModel ();
                        removeModel.setDeletedCount(username);
                        removeCartController.start ( "Bearer"+MypreferenceManager.getInstance ( getActivity () ).getAccessToken (),removeModel );

                        Toast.makeText ( getActivity (), "سبد های خرید با نام کاربری "+username+" با موفقیت حذف گردید", Toast.LENGTH_LONG ).show ();

                    }
                } );
                removeCartDialog.show ();

            }
        } );


    }

    ShopsAPIS.getRemoveCartCallback getRemoveCartCallback = new ShopsAPIS.getRemoveCartCallback () {
        @Override
        public void onResponse(RemoveModel removeModel) {

        }

        @Override
        public void onFailure(String cause) {

        }
    };


    ShopsAPIS.ProductsCallback productsCallback = new ShopsAPIS.ProductsCallback () {
        @Override
        public void onResponse(List<ProductsModels> products) {
            productsAdapter=new ProductsAdapter (products);
            recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );
            recyclerView.setAdapter(productsAdapter);
            progressBar.setVisibility ( View.INVISIBLE );
        }

        @Override
        public void onFailure(String cause) {
//            errorTxt.setVisibility ( View.VISIBLE );
        }
    };

    private void introduction(View view)
    {
        recyclerView=(RecyclerView) view.findViewById ( R.id.products_list );
//        BTN=(Button) view.findViewById ( R.id.BTN);
        buttonInquiry=(Button) view.findViewById ( R.id.button_inquiry2 );
        buttonRemove=(Button) view.findViewById ( R.id.button_remove_cart );
        progressBar=(ProgressBar) view.findViewById ( R.id.progress_bar_list_products );
//        errorTxt=(TextView) view.findViewById ( R.id.error_txt_products );
    }

}
