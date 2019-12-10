package com.example.bmcatering;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.bmcatering.Database.Database;
import com.example.bmcatering.Model.Item;
import com.example.bmcatering.Model.Order;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ItemDetail extends AppCompatActivity {
   // Context context=this;
    TextView name,price,description;
    ImageView fimage;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton floatingActionButton;
    ElegantNumberButton numberButton;

    Item item;

    String foodId="";

    FirebaseDatabase fb;
    DatabaseReference menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        
        fb=FirebaseDatabase.getInstance();
        menu=fb.getReference("Items");

        numberButton= (ElegantNumberButton) findViewById(R.id.nb);
        floatingActionButton =(FloatingActionButton)findViewById(R.id.btn_cart);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        item.getName(),
                        numberButton.getNumber(),
                        item.getPrice(),
                        item.getDiscount()
                ));
                Toast.makeText(ItemDetail.this,"Added to Cart",Toast.LENGTH_SHORT).show();
            }
        });

        
        name =(TextView)findViewById(R.id.food_name);
        price =(TextView)findViewById(R.id.food_price);
        description =(TextView)findViewById(R.id.food_description);
        fimage =(ImageView) findViewById(R.id.detail_image);
        
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleColor(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.Widget_AppCompat_ActionBar_Solid);

        if(getIntent()!=null){
            foodId= getIntent().getStringExtra("FoodId");
        }
        if(!foodId.isEmpty() && foodId!= null){
            getDetail(foodId);
        }
        
        
    }

    private void getDetail(String foodId) {
        menu.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 item = dataSnapshot.getValue(Item.class);
                Picasso.with(getBaseContext()).load(item.getImage()).into(fimage);

                collapsingToolbarLayout.setTitle(item.getName());
                price.setText(item.getPrice());
                name.setText(item.getName());
                description.setText(item.getDescription());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
