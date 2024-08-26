package com.example.mah.onlineshop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.ProductOrdering;
import com.example.mah.onlineshop.otherClasses.ProductInquiryAdapter;

public class DetailsOrderFragment extends Fragment {

    private TextView orderUsername;
    private TextView orderCount;
    private TextView orderProductId;
//    private EditText editText;

    private Button addAddress;
    private Button inquiryBtn;
    private Button registerOrder;
    private Button myOrderBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.order_details , container ,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        introduction (view);

        ProductOrdering productOrdering = (ProductOrdering) getArguments ().getSerializable("productOrderClass");
        orderUsername.setText("نام کاربری : "+productOrdering.getUsername ());
        orderCount.setText("تعداد سفارش محصول : " +productOrdering.getCount ());
        orderProductId.setText("آی دی محصول : "+productOrdering.getProductId ());
        orderProductId.setTextIsSelectable(true);
//        Toast.makeText ( getActivity (), "count : "+productOrdering.getCount (), Toast.LENGTH_SHORT ).show ();




//        ProductInquiryAdapter productInquiryAdapter = new ProductInquiryAdapter ();
//        Bundle bundle = new Bundle ();
//        bundle.putString("count_order",productOrdering.getCount ());
//        productInquiryAdapter.setArguments ( bundle );
//
////        Intent intent = this.getActivity ().getIntent ();

        addAddress.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                AddAdressFragment addAdressFragment = new AddAdressFragment ();
                getFragmentManager ().beginTransaction ()
                        .add(R.id.frame_container,addAdressFragment)
                        .addToBackStack ( null )
                        .commit ();
            }
        } );



        inquiryBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                InquiryFragment inquiryFragment = new InquiryFragment ();
                getFragmentManager ().beginTransaction ()
                        .add(R.id.frame_container,inquiryFragment)
                        .addToBackStack ( null )
                        .commit ();
            }
        } );

        registerOrder.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                RegisterOrderFragment registerOrderFragment = new RegisterOrderFragment ();
                getFragmentManager ().beginTransaction ()
                        .add ( R.id.frame_container,registerOrderFragment )
                        .addToBackStack ( null )
                        .commit ();
            }
        } );

        myOrderBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                getOrdersFragment getOrdersFragment1 = new getOrdersFragment ();
                getFragmentManager ().beginTransaction ()
                        .add ( R.id.frame_container,getOrdersFragment1 )
                        .addToBackStack ( null )
                        .commit ();
            }
        } );

    }

    private void introduction(View view)
    {
        orderUsername=(TextView) view.findViewById ( R.id.username_order );
        orderCount=(TextView) view.findViewById ( R.id.count_order );
        orderProductId=(TextView) view.findViewById ( R.id.product_id_order );
        inquiryBtn=(Button) view.findViewById ( R.id.button_inquiry );
//        editText=(EditText) view.findViewById ( R.id.edit_textt );
        addAddress=(Button) view.findViewById ( R.id.button_add_address );
        registerOrder=(Button) view.findViewById ( R.id.register_order_btn );
        myOrderBtn=(Button) view.findViewById ( R.id.my_order_btn );

    }
}
