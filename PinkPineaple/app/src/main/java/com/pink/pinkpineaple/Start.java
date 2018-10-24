package com.pink.pinkpineaple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Start extends AppCompatActivity {

    ViewPager viewPager;
    SliderAdapter sliderAdapter;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderAdapter = new SliderAdapter(this,this);
        viewPager.setAdapter(sliderAdapter);
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        if (mUser!=null){
            Intent intent = new Intent(Start.this,DashBoard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}