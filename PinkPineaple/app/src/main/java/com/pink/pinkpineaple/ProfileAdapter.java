package com.pink.pinkpineaple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProfileAdapter extends ArrayAdapter<Profile> {

    private android.content.Context Context;

    public ProfileAdapter(android.content.Context context, int resourceId, List<Profile> items){
        super(context, resourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Profile profile_item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView profileName = (TextView) convertView.findViewById(R.id.helloText);
        ImageView profileImage = (ImageView) convertView.findViewById(R.id.profileImage);



       // CardView bioCard = (CardView) convertView.findViewById(R.id.bioCard);
       // ScrollView bioScroll = (ScrollView) convertView.findViewById(R.id.bioScroll);
       // LinearLayout bioLayout = (LinearLayout) convertView.findViewById(R.id.bioLayout);
       // TextView profileAge = (TextView) convertView.findViewById(R.id.age);




        profileName.setText(profile_item.getName());
        profileImage.setImageResource(profile_item.getImageId());
        //profileBio.setText(profile_item.getBio());


        return convertView;
    }




}
