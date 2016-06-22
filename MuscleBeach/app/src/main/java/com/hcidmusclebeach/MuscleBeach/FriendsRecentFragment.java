package com.hcidmusclebeach.MuscleBeach;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendsRecentFragment extends Fragment {

	private TextView friendsNameText;
    private ImageView friendsImage;
    private int imageId;
    private String name;
    private int position;
    private EditText friendsMessage;
    private Button sendButton;

    public FriendsRecentFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_friends_recent, container, false);
         
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        friendsNameText = (TextView) view.findViewById(R.id.friendsRecordName);
        friendsImage = (ImageView) view.findViewById(R.id.friendsRecordImage);
        friendsMessage = (EditText) view.findViewById(R.id.friendMessage);
        sendButton = (Button) view.findViewById(R.id.sendButton);

        SharedPreferences prefs = getActivity().getSharedPreferences("SELECTED_FRIEND", Context.MODE_PRIVATE);
        name = prefs.getString("name", "Michael Cheng");
        position = prefs.getInt("position", position);
        switch (position) {
            case 0:
                imageId = R.drawable.original_musclearm_musclebelly;
                break;
            case 1:
                imageId = R.drawable.muscle;
                break;
            case 2:
                imageId = R.drawable.original;
                break;
            case 3:
                imageId = R.drawable.muscle;
                break;
            case 4:
                imageId = R.drawable.original_musclearm;
                break;
            case 5:
                imageId = R.drawable.original;
                break;
            case 6:
                imageId = R.drawable.original_musclearm_fatbelly;
                break;
            case 7:
                imageId = R.drawable.original;
                break;
        }

        friendsImage.setImageResource(imageId);
        friendsNameText.setText(name);

    }
}
