package com.pink.pinkpineaple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView settings = (TextView) findViewById(R.id.settings);
        settings.setText("Settings");

        ImageButton backArrow = (ImageButton) findViewById(R.id.backArrow); // back arrow menu button.
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.righttoleft,R.anim.righttoleftmessages);//overiding animation.
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.righttoleft,R.anim.righttoleftmessages);
    }


}
