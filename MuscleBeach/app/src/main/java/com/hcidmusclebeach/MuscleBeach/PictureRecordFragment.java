package com.hcidmusclebeach.MuscleBeach;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureRecordFragment extends Fragment {

    private TextView dateLabel;
    private ImageView recordImage;

    public PictureRecordFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_picture_record, container, false);
         
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        int year, month, date;
        SharedPreferences prefs = getActivity().getSharedPreferences("DATE", Context.MODE_PRIVATE);
        year = prefs.getInt("year", 2016);
        month = prefs.getInt("month", 5);
        date = prefs.getInt("date", 13);
        dateLabel = (TextView) view.findViewById(R.id.pictureRecordTextLabel);
        recordImage = (ImageView) view.findViewById(R.id.pictureRecordImageView);
        if(date == 8) {
            //int imageResource = getResources().getIdentifier("@drawable/arm_leg", null, getActivity().getPackageName());
            recordImage.setImageDrawable(
                    getResources().getDrawable(
                            getResources().getIdentifier("@drawable/arm_leg", null, getActivity().getPackageName())
                    ));
        } else if(date == 9) {
            recordImage.setImageDrawable(
                    getResources().getDrawable(
                            getResources().getIdentifier("@drawable/belly", null, getActivity().getPackageName())
                    ));
        }
        dateLabel.setText(
                Integer.toString(year)+ " " +
                Integer.toString(month+1)+ " " +
                Integer.toString(date)
        );
        





    }
}
