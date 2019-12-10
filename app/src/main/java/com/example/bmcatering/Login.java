package com.example.bmcatering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmcatering.Common.Common;
import com.example.bmcatering.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
     EditText edtpassword, edtname;
     Button sign,l;
     TextView forget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Customer Login");
        setContentView(R.layout.activity_login);
        edtpassword=(EditText)findViewById(R.id.passw1);
        edtname=(EditText)findViewById(R.id.edtName);
        sign=(Button)findViewById(R.id.rgst);
        l=(Button)findViewById(R.id.login);

       /* forget=findViewById(R.id.fp);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Forget_password.class);
                startActivity(intent);
            }
        });*/

        //inti firebase
        final FirebaseDatabase db= FirebaseDatabase.getInstance();
        final DatabaseReference tbl_user=db.getReference("User");

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog m = new ProgressDialog(Login.this);
                m.setMessage("Please Wait...");
                m.show();
                tbl_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtname.getText().toString()).exists()) {
                            ProgressDialog m = new ProgressDialog(Login.this);
                            m.dismiss();
                            User user = dataSnapshot.child(edtname.getText().toString()).getValue(User.class);
                           /* String phone ="";
                            User user1= dataSnapshot.getValue(User.class);
                            phone=user1.getPhone();*/
                            if (user.getPassword().equals(edtpassword.getText().toString())) {
                                Toast.makeText(Login.this, "Sign In Successfully...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, Home.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                Common.CurrentUser=user;
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(Login.this, "Sign In Failed...!", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            {
                            Toast.makeText(Login.this, "User Not Exists in Db...!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}
