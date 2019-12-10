package com.example.bmcatering.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmcatering.Interface.FitemClickListener;
import com.example.bmcatering.Interface.ItemClickListener;
import com.example.bmcatering.R;


public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView foodName;
    public ImageView foodImage;
    private FitemClickListener fitemClickListener;

    public void setFitemClickListener(FitemClickListener fitemClickListener) {
        this.fitemClickListener = fitemClickListener;
    }

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        foodName=(TextView)itemView.findViewById(R.id.fitem_name);
        foodImage=(ImageView)itemView.findViewById(R.id.fitem_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        fitemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
