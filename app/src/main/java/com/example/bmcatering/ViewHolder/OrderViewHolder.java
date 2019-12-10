package com.example.bmcatering.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmcatering.Interface.ItemClickListener;
import com.example.bmcatering.R;


public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView orderid,orderstatus,orderphone,orderaddress;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        orderid= (TextView)itemView.findViewById(R.id.OrderId);
        orderstatus= (TextView)itemView.findViewById(R.id.order_status);
        orderphone= (TextView)itemView.findViewById(R.id.order_phone);
        orderaddress= (TextView)itemView.findViewById(R.id.order_address);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
