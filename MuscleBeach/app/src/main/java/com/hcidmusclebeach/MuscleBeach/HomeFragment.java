package com.hcidmusclebeach.MuscleBeach;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeFragment extends Fragment {

    private ImageButton homeManImage;
    private int position;
    private final Integer[] imageId = {
            R.drawable.fat,
            R.drawable.original,
            R.drawable.original_musclearm,
            R.drawable.original_musclearm_fatbelly,
            R.drawable.original_musclearm_musclebelly,
            R.drawable.muscle,
            R.drawable.original_musclearm_fatbelly
    };

    public HomeFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
         
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        SharedPreferences prefs = getActivity().getSharedPreferences("FRESH_INSTALLATION", Context.MODE_PRIVATE);
        if(prefs.getBoolean("fresh", true)) {
            AlertDialog alertDialog = new AlertDialog.Builder(
                    getActivity()).create();
            // Setting Dialog Title
            alertDialog.setTitle("Some tips!!!");

            // Setting Dialog Message
            alertDialog.setMessage("This is a muscle man. It will grow as you work out!!!");

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.warning);

            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog closed

                }
            });

            // Showing Alert Message
            alertDialog.show();

            SharedPreferences.Editor editor
                    = getActivity().getSharedPreferences("FRESH_INSTALLATION", Context.MODE_PRIVATE).edit();
            editor.putBoolean("fresh", false);
            editor.commit();
        }

        homeManImage = (ImageButton) view.findViewById(R.id.homeImageButton);
        homeManImage.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                position = (position+1)%imageId.length;
                homeManImage.setImageResource(imageId[position]);
            }
        });

    }
}
