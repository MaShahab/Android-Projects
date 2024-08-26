package com.example.mah.onlineshop.otherClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mah.onlineshop.R;
import com.example.mah.onlineshop.models.ComponentsModel;
import com.example.mah.onlineshop.models.RegisterOrder;

import java.util.List;

public class MyInnerOrdersAdapter extends  RecyclerView.Adapter<MyInnerOrdersAdapter.ViewHolder>{

    private List<ComponentsModel> items;

    public MyInnerOrdersAdapter(List <ComponentsModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext ()).inflate ( R.layout.template_recycler_inner , viewGroup ,false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if(viewHolder.productIdInner == null)
        {
            viewHolder.productIdInner.setText("آی دی و تعداد برای این محصول ثبت نشذه است");
        }

        viewHolder.productIdInner.setText ( items.get ( i ).getProductId () );
        viewHolder.countInner.setText ( items.get ( i ).getCount () );

    }

    @Override
    public int getItemCount() {
        return items.size ();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView productIdInner;
        private TextView countInner;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            productIdInner=(TextView) itemView.findViewById ( R.id.product_id_inner );
            countInner=(TextView) itemView.findViewById ( R.id.count_inner );
        }
    }
}
