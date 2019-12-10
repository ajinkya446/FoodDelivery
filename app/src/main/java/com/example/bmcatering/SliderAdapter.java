package com.example.bmcatering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //Arrays
    public int[] slide_images={
          R.drawable.a6,
          R.drawable.cart,
          R.drawable.pay
    };

    public String[] slide_heading={
      "SELECTMENU",
      "BOOKMENU",
      "PAY"
    };


    public String[] slide_description={
            "Welcome to our new Food Delivery Service Application.",
            "Book Menu what wanted to and This Food Will be delivered ",
            "U can pay cash on delivery or also Online through using payment gateway"
    };


    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view== (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.slide1,container,false);

        ImageView img=(ImageView)view.findViewById(R.id.img1);
        TextView head=(TextView)view.findViewById(R.id.heading);
        TextView desc=(TextView)view.findViewById(R.id.slide_description);

        img.setImageResource(slide_images[position]);
        head.setText(slide_heading[position]);
        desc.setText(slide_description[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
