package com.hcidmusclebeach.MuscleBeach;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hcidmusclebeach.MuscleBeach.adapter.CustomList;

public class FriendsFragment extends Fragment {
	
	private ListView friendList;
    private final String[] names = new String[] { "Michael Cheng",
            "Mark Yang",
            "Sophia Hsieh",
            "Calvin Chen",
            "Kevin Shih",
            "Felix Hsu",
            "Mike Tsai",
            "Sunny Chang"
    };
    private final Integer[] imageId = {
            R.drawable.michael,
            R.drawable.mark,
            R.drawable.sophia,
            R.drawable.calvin,
            R.drawable.kevin,
            R.drawable.felix,
            R.drawable.mike,
            R.drawable.sunny
    };

    public FriendsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
         
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        friendList = (ListView) view.findViewById(R.id.list);
        CustomList adapter = new
                CustomList(getActivity(), names, imageId);
        friendList.setAdapter(adapter);
        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) friendList.getItemAtPosition(position);

                final Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);
                SharedPreferences.Editor editor
                        = getActivity().getSharedPreferences("SELECTED_FRIEND", Context.MODE_PRIVATE).edit();
                editor.putString("name", itemValue);
                editor.putInt("position", position);
                editor.commit();
                Fragment fr = new FriendsRecentFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.frame_container, fr);
                fragmentTransaction.commit();

            }

        });
    }
}
