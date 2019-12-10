package com.example.bmcatering;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmcatering.Common.Common;
import com.example.bmcatering.Database.Database;
import com.example.bmcatering.Model.Order;
import com.example.bmcatering.Model.Request;
import com.example.bmcatering.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    TextView totalPrice;
    Button place;

    List<Order> cart =new ArrayList<>();
    CartAdapter adapter;


    FirebaseDatabase db;
    DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        db= FirebaseDatabase.getInstance();
        requests=db.getReference("Requests");

        recyclerView=(RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        totalPrice=(TextView) findViewById(R.id.total);

        place=(Button)findViewById(R.id.placeOrder);

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });


        loadCart();

    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("One More Step");
        alertDialog.setMessage("Enter Your Address");

        final EditText edtAddress= new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               // String name=Common.CurrentUser.getPhone();
                Request request= new Request(
                        Common.CurrentUser.getPhone(),
                        edtAddress.getText().toString(),
                        Common.CurrentUser.getAddress(),
                        totalPrice.getText().toString(),
                        cart

                );

                //submiting this data to firebase.
                requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);

                // Delete cart After submitting data.
                new Database(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this,"Thank You , Your Order is placed.",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();

    }

    private void loadCart() {
       cart = new Database(getBaseContext()).getCarts();
       adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);

        int total=0;
        for (Order order:cart)
            total+= (Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
        Locale locale= new Locale("en","us");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        totalPrice.setText(fmt.format(total));
    }
}
