package com.example.bmcatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bmcatering.Common.Common;
import com.example.bmcatering.Model.Request;
import com.example.bmcatering.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderStatus extends AppCompatActivity {
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    FirebaseDatabase db;
    DatabaseReference dr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        db= FirebaseDatabase.getInstance();
        dr= db.getReference("Requests");

        recyclerView=(RecyclerView)findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //starting activity from notification.
        if(getIntent()!=null) {
            loadOrders(Common.CurrentUser.getPhone());
        }else{
            loadOrders(getIntent().getStringExtra("UserPhone"));
        }
    }

    private void loadOrders(String phone) {
        adapter= new FirebaseRecyclerAdapter<Request, OrderViewHolder>(Request.class,R.layout.order_layout,OrderViewHolder.class,dr.orderByChild("phone").equalTo(phone)) {
            @Override
            protected void populateViewHolder(OrderViewHolder orderViewHolder, Request request, int i) {
                orderViewHolder.orderid.setText(adapter.getRef(i).getKey());
                orderViewHolder.orderstatus.setText(Common.convertCodeToStatus(request.getStatus()));
                orderViewHolder.orderphone.setText(request.getPhone());
                orderViewHolder.orderaddress.setText(request.getAddress());

            }
        };
        recyclerView.setAdapter(adapter);
    }


}
