package com.hcidmuscle.musclebeach;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class HomeActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.home);
                break;
            case 2:
                mTitle = getString(R.string.record);
                break;
            case 3:
                mTitle = getString(R.string.friends);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    private static class NavigationDrawerFragment extends Fragment {

        /**
         * Remember the position of the selected item.
         */
        private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

        /**
         * Per the design guidelines, you should show the drawer on launch until the user manually
         * expands it. This shared preference tracks this.
         */
        private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

        /**
         * A pointer to the current callbacks instance (the Activity).
         */
        private NavigationDrawerCallbacks mCallbacks;
        public Activity mCurrentActivity;

        /**
         * Helper component that ties the action bar to the navigation drawer.
         */
        private ActionBarDrawerToggle mDrawerToggle;

        private DrawerLayout mDrawerLayout;
        private ListView mDrawerListView;
        private View mFragmentContainerView;

        private int mCurrentSelectedPosition = 0;
        private boolean mFromSavedInstanceState;
        private boolean mUserLearnedDrawer;

        public NavigationDrawerFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Read in the flag indicating whether or not the user has demonstrated awareness of the
            // drawer. See PREF_USER_LEARNED_DRAWER for details.
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
            mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

            if (savedInstanceState != null) {
                mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
                mFromSavedInstanceState = true;
            }

            // Select either the default item (0) or the last selected item.
            selectItem(mCurrentSelectedPosition);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            // Indicate that this fragment would like to influence the set of actions in the action bar.
            setHasOptionsMenu(true);
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            mDrawerListView = (ListView) inflater.inflate(
                    R.layout.fragment_navigation_drawer, container, false);
            mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectItem(position);
                }
            });
            mDrawerListView.setAdapter(new ArrayAdapter<String>(
                    getActionBar().getThemedContext(),
                    android.R.layout.simple_list_item_activated_1,
                    android.R.id.text1,
                    new String[]{
                            getString(R.string.home),
                            getString(R.string.record),
                            getString(R.string.friends),
                    }));
            mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
            return mDrawerListView;
        }

        public boolean isDrawerOpen() {
            return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
        }

        /**
         * Users of this fragment must call this method to set up the navigation drawer interactions.
         *
         * @param fragmentId   The android:id of this fragment in its activity's layout.
         * @param drawerLayout The DrawerLayout containing this fragment's UI.
         */
        public void setUp(int fragmentId, DrawerLayout drawerLayout) {
            mFragmentContainerView = getActivity().findViewById(fragmentId);
            mDrawerLayout = drawerLayout;

            // set a custom shadow that overlays the main content when the drawer opens
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
            // set up the drawer's list view with items and click listener

            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);

            // ActionBarDrawerToggle ties together the the proper interactions
            // between the navigation drawer and the action bar app icon.
            mDrawerToggle = new ActionBarDrawerToggle(
                    getActivity(),                    /* host Activity */
                    mDrawerLayout,                    /* DrawerLayout object */
                    R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                    R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                    R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
            ) {
                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    if (!isAdded()) {
                        return;
                    }

                    getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    if (!isAdded()) {
                        return;
                    }

                    if (!mUserLearnedDrawer) {
                        // The user manually opened the drawer; store this flag to prevent auto-showing
                        // the navigation drawer automatically in the future.
                        mUserLearnedDrawer = true;
                        SharedPreferences sp = PreferenceManager
                                .getDefaultSharedPreferences(getActivity());
                        sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                    }

                    getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
                }
            };

            // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
            // per the navigation drawer design guidelines.
            if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
                mDrawerLayout.openDrawer(mFragmentContainerView);
            }

            // Defer code dependent on restoration of previous instance state.
            mDrawerLayout.post(new Runnable() {
                @Override
                public void run() {
                    mDrawerToggle.syncState();
                }
            });

            mDrawerLayout.setDrawerListener(mDrawerToggle);
        }

        void selectItem(int position) {
            mCurrentSelectedPosition = position;
            if (mDrawerListView != null) {
                mDrawerListView.setItemChecked(position, true);
            }
            if (mDrawerLayout != null) {
                mDrawerLayout.closeDrawer(mFragmentContainerView);
            }
            if (mCallbacks != null) {
                mCallbacks.onNavigationDrawerItemSelected(position);
            }
            Log.d("NavigationDrawerFragment", Integer.toString(position));
            Intent intent;
            switch (position) {
                case 0:
                    Log.d("NavigationDrawerFragment", Integer.toString(0));
                    mCurrentSelectedPosition = position;
                    intent = new Intent(mCurrentActivity, HomeActivity.class);
                    startActivity(intent);

                    break;
                case 1:
                    Log.d("NavigationDrawerFragment", Integer.toString(1));
                    mCurrentSelectedPosition = position;
                    intent = new Intent(mCurrentActivity, RecordActivity.class);
                    startActivity(intent);

                    break;
                case 2:
                    Log.d("NavigationDrawerFragment", Integer.toString(2));
                    mCurrentSelectedPosition = position;
                    intent = new Intent(mCurrentActivity, FriendsActivity.class);
                    startActivity(intent);
                    break;
            }
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            try {
                mCallbacks = (NavigationDrawerCallbacks) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
            }
        }

        @Override
        public void onDetach() {
            super.onDetach();
            mCallbacks = null;
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            // Forward the new configuration the drawer toggle component.
            mDrawerToggle.onConfigurationChanged(newConfig);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            // If the drawer is open, show the global app actions in the action bar. See also
            // showGlobalContextActionBar, which controls the top-left area of the action bar.
            if (mDrawerLayout != null && isDrawerOpen()) {
                inflater.inflate(R.menu.global, menu);
                showGlobalContextActionBar();
            }
            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }

            if (item.getItemId() == R.id.action_example) {
                Toast.makeText(getActivity(), "Example action.", Toast.LENGTH_SHORT).show();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        /**
         * Per the navigation drawer design guidelines, updates the action bar to show the global app
         * 'context', rather than just what's in the current screen.
         */
        private void showGlobalContextActionBar() {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            actionBar.setTitle(R.string.app_name);
        }

        private ActionBar getActionBar() {
            return getActivity().getActionBar();
        }

        /**
         * Callbacks interface that all activities using this fragment must implement.
         */
        public interface NavigationDrawerCallbacks {
            /**
             * Called when an item in the navigation drawer is selected.
             */
            void onNavigationDrawerItemSelected(int position);
        }
    }

}

