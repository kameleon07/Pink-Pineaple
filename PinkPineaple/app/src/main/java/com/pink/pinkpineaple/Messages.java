package com.pink.pinkpineaple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Messages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        TextView messageText = (TextView) findViewById(R.id.message);
        messageText.setText("Messages");

        ImageButton backArrowMessages = (ImageButton) findViewById(R.id.backArrowMessages);
        backArrowMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.lefttorightsettings, R.anim.lefttoright);
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.lefttorightsettings, R.anim.lefttoright);
    }
}
