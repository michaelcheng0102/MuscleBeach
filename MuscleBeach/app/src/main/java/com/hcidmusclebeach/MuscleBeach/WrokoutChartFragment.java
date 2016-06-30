package com.hcidmusclebeach.MuscleBeach;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class WrokoutChartFragment extends Fragment {

    private ImageView chartImage;
    private int selectPart;
    private int imageId;

    public WrokoutChartFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_workout_chart, container, false);
         
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        chartImage = (ImageView) view.findViewById(R.id.chartImage);
        SharedPreferences prefs = getActivity().getSharedPreferences("PressImage", Context.MODE_PRIVATE);
        selectPart = prefs.getInt("part", 0);
        if(selectPart == 1) {
            imageId = R.drawable.belly_workout;
            chartImage.setImageResource(imageId);
        } else if(selectPart == 2) {
            imageId = R.drawable.leg_workout;
            chartImage.setImageResource(imageId);
        } else {
            imageId = R.drawable.hand_workout;
            chartImage.setImageResource(imageId);
        }
    }
}
