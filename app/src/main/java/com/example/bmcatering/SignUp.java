package com.example.bmcatering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bmcatering.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    private EditText fname,addr,contact,pass1,pass2;
   private Button rgst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fname=(EditText)findViewById(R.id.fname);
        addr=(EditText)findViewById(R.id.Address);
        contact=(EditText)findViewById(R.id.phone);
        pass1=(EditText)findViewById(R.id.passw1);
        pass2=(EditText)findViewById(R.id.passw2);
        rgst=(Button)findViewById(R.id.rgst);

        //Init Firebase.
        final FirebaseDatabase db= FirebaseDatabase.getInstance();
        final DatabaseReference tbl_user=db.getReference("User");




            setTitle("Customer Registration");
                //Button Signup code .
        rgst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog m = new ProgressDialog(SignUp.this);
                m.setMessage("Please Wait...");
                m.show();
                tbl_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(contact.getText().toString()).exists())
                        {
                            ProgressDialog m = new ProgressDialog(SignUp.this);
                            m.dismiss();
                            Toast.makeText(SignUp.this, "User Already Exists...", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            m.dismiss();
                            User user = new User(addr.getText().toString(),pass2.getText().toString(),pass1.getText().toString(),contact.getText().toString());
                            tbl_user.child(fname.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this,"Sign Up Successfully..",Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent= new Intent(SignUp.this,Login.class);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
