package com.example.mah.onlineshop.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mah.onlineshop.Data.CommentsController;
import com.example.mah.onlineshop.Data.ShopsAPIS;
import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.CommentModels;
import com.example.mah.onlineshop.models.MyID;
import com.example.mah.onlineshop.models.ProductsId;
import com.example.mah.onlineshop.otherClasses.CommentsAdapter;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;

import java.util.List;

public class CommentsFrag extends Fragment {

    private RecyclerView recyclerView;
    private CommentsAdapter commentsAdapter;
    private CommentModels commentModels;
    private TextView ERROR;
    private CoordinatorLayout coordinatorLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_comment,container,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        introduction(view);

//        CommentModels commentModels = (CommentModels) getArguments ().getSerializable("items_comments");

//        String id = getArguments ().getString("productID",null);

        String productId = MypreferenceManager.getInstance ( getActivity () ).getID ();


        MyID myID = new MyID ();
        myID.setMyID(productId);


//        ProductsId productsId = new ProductsId ();
//        productsId.setProductsId(productId);

        CommentsController commentsController = new CommentsController (productsCommentsCallback);
        commentsController.start("Bearer" + MypreferenceManager.getInstance ( getActivity ()).getAccessToken (),myID);




    }

    ShopsAPIS.ProductsCommentsCallback productsCommentsCallback = new ShopsAPIS.ProductsCommentsCallback () {
        @Override
        public void onResponse(List<CommentModels> commentModels) {
            MyID myID = new MyID ();
            configure(commentModels, myID);

        }

        @Override
        public void onFailure(String cause) {

        }
    };

    private void introduction(View view)
    {
        recyclerView=(RecyclerView) view.findViewById ( R.id.recycler_comment );
        ERROR=(TextView) view.findViewById ( R.id.error_txt1 );
        coordinatorLayout = (CoordinatorLayout) view.findViewById ( R.id.coordinate );

    }
    private void configure(List<CommentModels> commentModels , MyID myID)
    {
        commentsAdapter = new CommentsAdapter(commentModels);
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );
        recyclerView.setAdapter (commentsAdapter);
        if(commentModels.size () == 0)
        {
            Snackbar snackbar = Snackbar.make(coordinatorLayout ,"برای این محصول نظری ثبت نشده است"  , Snackbar.LENGTH_LONG);
            snackbar.setDuration(6500);
            snackbar.show ();
        }
    }
}
