package com.hcidmusclebeach.MuscleBeach.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcidmusclebeach.MuscleBeach.R;

public class FriendList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    public FriendList(Activity context,
                      String[] web, Integer[] imageId) {
        super(context, R.layout.friend_list, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.friend_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.friendName);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.friendIcon);
        txtTitle.setText(web[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
