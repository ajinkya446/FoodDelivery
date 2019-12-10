package com.example.bmcatering;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class Forget_password extends AppCompatActivity {

    private EditText name;
    private Button collect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        name=findViewById(R.id.rmail);
        collect=findViewById(R.id.send);


    }
}
