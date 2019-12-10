package com.example.bmcatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bmcatering.Interface.FitemClickListener;
import com.example.bmcatering.Model.Item;
import com.example.bmcatering.ViewHolder.ItemViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Item_category extends AppCompatActivity {

    FirebaseDatabase fb;
    DatabaseReference dbref;

    RecyclerView itemrecycler;
    RecyclerView.LayoutManager layoutManager;
    String categoryId="";

    FirebaseRecyclerAdapter<Item, ItemViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_category);
        setTitle("Items.");

        fb=FirebaseDatabase.getInstance();
        dbref=fb.getReference("Items");

        itemrecycler=(RecyclerView)findViewById(R.id.recycler_items);
        itemrecycler.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        itemrecycler.setLayoutManager(layoutManager);

        //get activity here.

        if(getIntent()!=null){
            categoryId=getIntent().getStringExtra("categoryId");
        }
        if(!categoryId.isEmpty() && categoryId!= null){
            loadItem(categoryId);
        }

    }

    private void loadItem(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(Item.class,R.layout.food,ItemViewHolder.class,dbref.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(ItemViewHolder itemViewHolder, Item item, int i) {
                itemViewHolder.foodName.setText(item.getName());
                Picasso.with(getBaseContext()).load(item.getImage())
                        .into(itemViewHolder.foodImage);
                final Item local= item;
                itemViewHolder.setFitemClickListener(new FitemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                            Intent intent = new Intent(Item_category.this, ItemDetail.class);
                            intent.putExtra("FoodId", adapter.getRef(position).getKey());
                            startActivity(intent);


                    }
                });
            }
        };

        itemrecycler.setAdapter(adapter);
    }

}
