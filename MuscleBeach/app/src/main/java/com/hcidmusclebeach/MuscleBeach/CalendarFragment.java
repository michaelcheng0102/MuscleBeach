package com.hcidmusclebeach.MuscleBeach;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class CalendarFragment extends Fragment {
	
	private CalendarView calendar;

    public CalendarFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
         
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        calendar = (CalendarView) view.findViewById(R.id.calendarView);
//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
//
//            @Override
//            public void onSelectedDayChange(CalendarView view,
//                                            int year, int month, int dayOfMonth) {
//                Toast.makeText(getActivity().getApplicationContext(),
//                        dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();}});
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int date) {
                SharedPreferences.Editor editor
                        = getActivity().getSharedPreferences("DATE", Context.MODE_PRIVATE).edit();
                editor.putInt("year", year);
                editor.putInt("month", month);
                editor.putInt("date", date);
                editor.commit();
                if(date == 6 || date == 8 || date == 9) {
                    Fragment fr = new PictureRecordFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.frame_container, fr);
                    fragmentTransaction.commit();
                } else {
                    final Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "There is no record on this date.", Toast.LENGTH_LONG);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1000);
                }



            }
        });
    }
}
