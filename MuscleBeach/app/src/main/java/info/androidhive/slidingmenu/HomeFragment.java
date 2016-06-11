package info.androidhive.slidingmenu;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import info.androidhive.slidingmenu.adapter.FragmentChangeListener;

public class HomeFragment extends Fragment {
	
	Button testButton;
    public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
         
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        testButton = (Button) view.findViewById(R.id.testButton);
        testButton.setOnClickListener( new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Fragment fr = new FindPeopleFragment();

                                               FragmentManager fm = getFragmentManager();
                                               android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                               fragmentTransaction.replace(R.id.frame_container, fr);
                                               fragmentTransaction.commit();
                                           }
                                       }
        );
    }
}
