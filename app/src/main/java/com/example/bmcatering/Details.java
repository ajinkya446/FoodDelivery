package com.example.bmcatering;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmcatering.Interface.ItemClickListener;
import com.example.bmcatering.Model.Category;
import com.example.bmcatering.Service.ListenOrder;
import com.example.bmcatering.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    Context context=this;
    FirebaseDatabase db;
    DatabaseReference category;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle("Menu Category.");

        db=FirebaseDatabase.getInstance();
        category=db.getReference("Category");


        //Load data
        recyclerView=(RecyclerView)findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadMenu();

        //service resgistration.
        Intent service = new Intent(Details.this, ListenOrder.class);
        startService(service);


    }

    private void loadMenu() {
        adapter= new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, Category category, int i) {
                menuViewHolder.txtMenuName.setText(category.getName());
                Picasso.with(getBaseContext()).load(category.getImage())
                .into(menuViewHolder.imgName);

                final Category clickItem= category;
                menuViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(Details.this,Item_category.class);
                        intent.putExtra("categoryId",adapter.getRef(position).getKey());
                        startActivity(intent);

                       /* String name=clickItem.getName();
                        if(position==0){

                        }
                        else if(position==1){
                            Intent nonvegIntent = new Intent(Details.this,NonVeg.class);
                            startActivity(nonvegIntent);
                        }
                        else if(position==2){
                            Intent startIntent = new Intent(Details.this, starterdetails.class);
                            startActivity(startIntent);
                        }
                        else if(position==3){
                            Intent startIntent = new Intent(Details.this, Drinks.class);
                            startActivity(startIntent);
                        }
                        else if(position==4){
                            Intent startIntent = new Intent(Details.this, dessert_menu.class);
                            startActivity(startIntent);
                        }
                        else
                        {
                            Toast.makeText(Details.this,""+clickItem.getName(),Toast.LENGTH_SHORT).show();
                        }*/

                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
    }
}
