package com.example.mah.onlineshop.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mah.onlineshop.Data.RegisterOrderController;
import com.example.mah.onlineshop.Data.ShopsAPIS;
import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.ComponentsModel;
import com.example.mah.onlineshop.models.RegisterOrder;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;

public class RegisterOrderFragment extends Fragment {

    private EditText userName;
    private EditText addressID;
    private EditText totalPrice;
    private EditText productId;
    private EditText count;
    private Button buttonRegOrder;
    private Button buttonSaveInformations;
    private CoordinatorLayout coordinatorLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragmnet_register_order , container ,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        introduction(view);


        buttonSaveInformations.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                MypreferenceManager.getInstance ( getActivity () ).putProductId ( productId.getText ().toString () );
                MypreferenceManager.getInstance ( getActivity () ).putAddressId ( addressID.getText ().toString () );

            }
        } );




        productId.setText ( MypreferenceManager.getInstance ( getActivity () ).getProductId () );
        addressID.setText ( MypreferenceManager.getInstance ( getActivity () ).getAddressId () );


        Snackbar snackbar = Snackbar.make(coordinatorLayout,"جهت وارد کردن آی دی محصول و آی دی آدرس آنها را از فرگمت مربوطه کپی کرده و در فیلد آن پیست کنید" , Snackbar.LENGTH_LONG);
        snackbar.setDuration(10000);
        snackbar.show ();


        buttonRegOrder.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                ComponentsModel componentsModel = new ComponentsModel ();

                componentsModel.setCount(count.getText ().toString ());
                componentsModel.setProductId(productId.getText ().toString ());

                RegisterOrder registerOrder = new RegisterOrder ();
                registerOrder.setAddressId(addressID.getText ().toString());
                registerOrder.setUsername ( userName.getText ().toString());

                String mytotalprice = totalPrice.getText ().toString ();
                Integer priceParse = Integer.parseInt(mytotalprice);

                registerOrder.setTotalPrice(priceParse);


                RegisterOrderController registerOrderController = new RegisterOrderController(getRegisterOrderCallback);
                registerOrderController.start("Bearer" + MypreferenceManager.getInstance ( getActivity () ).getAccessToken (),registerOrder);

                Toast.makeText ( getActivity (), "اطلاعات با موفقیت ثبت گردید", Toast.LENGTH_LONG ).show ();
            }
        } );



    }

    ShopsAPIS.getRegisterOrderCallback getRegisterOrderCallback = new ShopsAPIS.getRegisterOrderCallback () {
        @Override
        public void onResponse(RegisterOrder registerOrder) {

        }

        @Override
        public void onFailure(String cause) {

        }
    };

    private void introduction(View view)
    {
        userName=(EditText) view.findViewById ( R.id.username_id_reg_order );
        addressID=(EditText) view.findViewById ( R.id.address_id_reg_order );
        totalPrice=(EditText) view.findViewById ( R.id.total_price_reg_order );
        productId=(EditText) view.findViewById ( R.id.product_id_reg_order );
        count=(EditText) view.findViewById ( R.id.product_numbers_reg_order );
        buttonRegOrder=(Button) view.findViewById ( R.id.btn_reg_order );
        coordinatorLayout=(CoordinatorLayout) view.findViewById (R.id.coordinator_reg_frag);
        buttonSaveInformations=(Button) view.findViewById ( R.id.save_information_btn );
    }
}
