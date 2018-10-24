package com.pink.pinkpineaple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class DashBoard extends AppCompatActivity {

    private Profile profiles_data[];
    private ProfileAdapter arrayAdapter;
    private int i;

    ListView list;
    List<Profile> rowItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        final Profile placeholder = new Profile("null");//default profile

        final CardView bioCard = (CardView) findViewById(R.id.bioCard);
        TextView bioText = (TextView) findViewById(R.id.bioText);
        TextView bioName = (TextView) findViewById(R.id.bioName);
        ImageView profileImage = (ImageView) findViewById(R.id.profileImage);

        Profile pf1 = new Profile("Lewis"); //creating new profile objects.
        Profile pf2 = new Profile("Mary");
        Profile pf3 = new Profile("Aria");

        pf1.setBio("HELLO I LIKE RACE CARS");    //manually setting their bio.
        pf2.setBio("HI IM MARY I LICK DINOSAURS");
        pf3.setBio("I like people called Aamir");

        pf1.setImageId(R.drawable.placeholder);  //manually setting their profile pics.
        pf2.setImageId(R.drawable.placeholder2b);
        pf3.setImageId(R.drawable.placeholder3);

        placeholder.setImageId(R.drawable.placeholder2b);


        rowItems = new ArrayList<Profile>();  //adding the premade profiles to an array list.
        rowItems.add(pf1);
        rowItems.add(pf2);
        rowItems.add(pf3);

        arrayAdapter = new ProfileAdapter(this, R.layout.item, rowItems); // context, layout resource, TextView, list.

        final SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter); //setting the profile adapter to the swiping thing.

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {


            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                /*
                * if match then create a new instance of match else
                * */
                Toast.makeText(DashBoard.this, "ugly", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(DashBoard.this, "onion", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                rowItems.add(placeholder); // on empty add placeholder profile.
                TextView bioText = (TextView) findViewById(R.id.bioText);//on empty add set corresponding bio.
                TextView bioName = (TextView) findViewById(R.id.bioName);
                bioText.setText(rowItems.get(0).getBio());
                bioName.setText(rowItems.get(0).getName() + ", " + rowItems.get(0).getAge());
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        // Optionally add an OnItemClickListener



        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() { // bio animations.
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(DashBoard.this, "Clicked", Toast.LENGTH_SHORT).show();
                Animation upToDown = AnimationUtils.loadAnimation(DashBoard.this, R.anim.uptodown);
                Animation goUp = AnimationUtils.loadAnimation(DashBoard.this, R.anim.goup);
                Animation goDown = AnimationUtils.loadAnimation(DashBoard.this, R.anim.godown);
                bioCard.startAnimation(goDown);
                flingContainer.startAnimation(goUp);
                flingContainer.setVisibility(INVISIBLE);
                bioCard.setVisibility(VISIBLE);
            }
        });

        LinearLayout bioLayout = (LinearLayout) findViewById(R.id.bioLayout);

        bioLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation DownToUp = AnimationUtils.loadAnimation(DashBoard.this, R.anim.downtoup);
                Animation goUp = AnimationUtils.loadAnimation(DashBoard.this, R.anim.goup);
                Animation goDown = AnimationUtils.loadAnimation(DashBoard.this, R.anim.godown);
                flingContainer.startAnimation(goDown);
                bioCard.startAnimation(goUp);
                flingContainer.setVisibility(VISIBLE);
                bioCard.setVisibility(INVISIBLE);
            }
        });

        ImageButton settingsBtn = (ImageButton) findViewById(R.id.settingsBtn); //settings menu button.
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(DashBoard.this, Settings.class);
                DashBoard.this.startActivity(settingsIntent);
                overridePendingTransition(R.anim.lefttorightsettings, R.anim.lefttoright);
            }
        });

        ImageButton messageBtn = (ImageButton) findViewById(R.id.messageBtn); //messages menu button.
        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent messageIntent = new Intent(DashBoard.this, MainActivity.class);
                messageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                messageIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                DashBoard.this.startActivity(messageIntent);
                overridePendingTransition(R.anim.righttoleft,R.anim.righttoleftmessages);
            }
        });

/*
        final FlingCardListener flc = new FlingCardListener(this, rowItems.get(0), );
        ImageButton likeBtn = (ImageButton) findViewById(R.id.likeBtn);
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flc.selectLeft();
            }
        });
        */

    }



}
