package com.example.bmcatering;

import android.content.Intent;
import android.os.Bundle;

import com.example.bmcatering.Common.Common;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView fullUser,phone;
    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        mainGrid=(GridLayout)findViewById(R.id.newgrid);

        setSingleEvent(mainGrid);

        //setToggleEvent(mainGrid);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartintent = new Intent(Home.this,Cart.class);
                startActivity(cartintent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //setting username to navbar.
        View headerView= navigationView.getHeaderView(0);
        fullUser=(TextView)headerView.findViewById(R.id.fullname);
        fullUser.setText(Common.CurrentUser.getAddress());

        phone=(TextView)headerView.findViewById(R.id.phoneUser);
        phone.setText(Common.CurrentUser.getPhone());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_menu) {
            Intent orderintent= new Intent(Home.this,OrderStatus.class);
            startActivity(orderintent);

        } else if (id == R.id.nav_cart) {
            Intent intent= new Intent(Home.this,Cart.class);
            startActivity(intent);

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_logout) {
            Intent logout= new Intent(Home.this,Login.class);
            logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(logout);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setSingleEvent(GridLayout mainGrid) {
        for(int i=0;i<mainGrid.getChildCount();i++){
            CardView cardView= (CardView)mainGrid.getChildAt(i);
            final int finalI=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI==0){
                        Intent intent= new Intent(Home.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI==1){
                        Intent intent= new Intent(Home.this,Details.class);
                        startActivity(intent);
                    }
                    else if(finalI==2){
                        Intent intent= new Intent(Home.this,Details.class);
                        startActivity(intent);
                    }
                    else if(finalI==3){
                        Intent intent= new Intent(Home.this,Details.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Home.this,"Invalied Clicked",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

   /*public void setToggleEvent(GridLayout toggleEvent) {
        for(int i=0;i<=mainGrid.getChildCount();i++) {
            CardView cardView= (CardView)mainGrid.getChildAt(i);
            final int finalI=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        }*/
}
