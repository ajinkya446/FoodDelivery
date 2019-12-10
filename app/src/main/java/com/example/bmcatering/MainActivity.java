package com.example.bmcatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private SliderAdapter slideAdapter;
    private Button prev,next;

    private TextView[] mdots;
    private  int mCurrentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=(ViewPager)findViewById(R.id.slideViewPager);
        linearLayout=(LinearLayout)findViewById(R.id.dots);

        next=(Button)findViewById(R.id.next);
        prev=(Button)findViewById(R.id.prev);

        slideAdapter= new SliderAdapter(this);
        viewPager.setAdapter(slideAdapter);

        addDots(0);

        viewPager.addOnPageChangeListener(viewListener);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(mCurrentPage +1);
                if(mCurrentPage==2){
                    Intent intent= new Intent(MainActivity.this,Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(mCurrentPage -1);
            }
        });

    }
    public void addDots(int position){
        mdots= new TextView[3];
        linearLayout.removeAllViews();

        for(int i=0;i<mdots.length;i++){
            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colorTransperentWhite));

            linearLayout.addView(mdots[i]);
        }

        if(mdots.length > 0){
            mdots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            if (position == 0){
                next.setEnabled(true);
                prev.setEnabled(false);
                prev.setVisibility(View.INVISIBLE);

                next.setText("Next");
                prev.setText("");
            }else if(position== mdots.length -1){
                next.setEnabled(true);
                prev.setEnabled(true);
                prev.setVisibility(View.VISIBLE);

                next.setText("Finish");
                prev.setText("Back");
            }else{
                next.setEnabled(true);
                prev.setEnabled(true);
                prev.setVisibility(View.VISIBLE);

                next.setText("Finish");
                prev.setText("Back");
            }
            mCurrentPage=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
