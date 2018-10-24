package com.pink.pinkpineaple;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    Start main;
    public int[] images = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4};
    public String[] titleText = {
            "FIND YOUR PERFECT MATCH",
            "GO ON MOVIE DATES",
            "FIND YOUR HIME",
            "OR YOUR PRINCE"
    };
    public String[] desc = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
    };

    public int[] backgroundArray = {Color.rgb(139,0,0),Color.rgb(128,0,0),
        Color.rgb(178,34,34),
        Color.rgb(0,0,0)};

    public SliderAdapter(Context context,Start main){
        this.context = context;this.main = main;
    }

    @Override
    public int getCount() {
        return titleText.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (LinearLayout) o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        ImageView imageView = (ImageView) view.findViewById(R.id.slideimg);
        TextView txTitle = (TextView) view.findViewById(R.id.txtTitle);
        TextView txDesc = (TextView) view.findViewById(R.id.txtDesc);

        linearLayout.setBackgroundColor(backgroundArray[position]);
        imageView.setImageResource(images[position]);
        txTitle.setText(titleText[position]);
        txDesc.setText(desc[position]);


        Button backButton = (Button) view.findViewById(R.id.button123);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main, Login.class);
                main.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }
}
