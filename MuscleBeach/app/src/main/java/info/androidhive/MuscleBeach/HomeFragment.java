package info.androidhive.MuscleBeach;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {
	
	public HomeFragment(){}
    private Button testButton;


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
                                               FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                               fragmentTransaction.replace(R.id.frame_container, fr);
                                               fragmentTransaction.commit();
                                           }
                                       }
        );
    }
}
