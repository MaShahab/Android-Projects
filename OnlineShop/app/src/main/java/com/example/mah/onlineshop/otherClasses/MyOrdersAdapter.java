package com.example.mah.onlineshop.otherClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.RegisterOrder;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {

    private List<RegisterOrder> items;

    public MyOrdersAdapter(List <RegisterOrder> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.template_orders , viewGroup ,false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        viewHolder.myOrderUsername.setText(items.get ( i ).getUsername ());
        viewHolder.totalPriceUsername.setText ( String.valueOf ( items.get ( i ).getTotalPrice () ) );
        if(items.get ( i ).getTotalPrice ()==null){viewHolder.totalPriceUsername.setText ("قیمتی برای این محصول ثبت نشده است");}
        viewHolder.idOrder.setText ( items.get ( i ).get_id () );
        viewHolder.myAddressIdOrder.setText ( items.get(i).getAddressId () );
        viewHolder.myorderDate.setText ( items.get ( i ).getCreatedAt () );

        MyInnerOrdersAdapter myInnerOrdersAdapter = new MyInnerOrdersAdapter ( items.get ( i).getComponentsModels () );
        if(items.get ( i ).getComponentsModels ()!= null)

        {
            viewHolder.errorDescription.setVisibility ( View.VISIBLE );
            viewHolder.recyclerViewInner.setLayoutManager ( new LinearLayoutManager ( viewHolder.recyclerViewInner.getContext () ) );
            viewHolder.recyclerViewInner.setAdapter ( myInnerOrdersAdapter );
        }

        else {
            viewHolder.errorDescription.setVisibility(View.VISIBLE);
        }




    }

    @Override
    public int getItemCount() {
        return items.size ();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        private TextView myOrderUsername;
        private TextView totalPriceUsername;
        private TextView idOrder;
        private TextView myAddressIdOrder;
        private TextView myorderDate;
        private TextView errorDescription;
        private RecyclerView recyclerViewInner;
//        private RecyclerView expand;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );

            myOrderUsername=(TextView) itemView.findViewById ( R.id.username_my_orders );
            totalPriceUsername=(TextView) itemView.findViewById ( R.id.total_price_my_orders );
            idOrder=(TextView) itemView.findViewById ( R.id.id_my_order );
            myAddressIdOrder=(TextView) itemView.findViewById ( R.id.address_id_my_order );
            myorderDate=(TextView) itemView.findViewById ( R.id.date_my_order );
            recyclerViewInner = itemView.findViewById ( R.id.recycler_inner );
//            expand=(TextView) itemView.findViewById ( R.id.button_expand_my_order );
            errorDescription=(TextView) itemView.findViewById ( R.id.error_describe );

        }
    }
}
