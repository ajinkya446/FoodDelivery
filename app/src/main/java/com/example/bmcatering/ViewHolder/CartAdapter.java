package com.example.bmcatering.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.bmcatering.Interface.FitemClickListener;
import com.example.bmcatering.Model.Order;
import com.example.bmcatering.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView itemName;

    public void setItemName(TextView itemName) {
        this.itemName = itemName;
    }

    public TextView itemPrice;
    public ImageView itemcount;

    private FitemClickListener fitemClickListener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        itemName =(TextView)itemView.findViewById(R.id.cart_item_name);
        itemPrice =(TextView)itemView.findViewById(R.id.cart_item_price);
        itemcount =(ImageView) itemView.findViewById(R.id.cart_item_count);
    }

    @Override
    public void onClick(View view) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.cart_layout,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+listData.get(position).getQuantity(), Color.RED);
        holder.itemcount.setImageDrawable(drawable);
        Locale locale= new Locale("en","us");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price= (Integer.parseInt(listData.get(position).getPrice()))*(Integer.parseInt(listData.get(position).getQuantity()));
        holder.itemPrice.setText(fmt.format(price));
        holder.itemName.setText(listData.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
