package com.example.ganesh.sutransit;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements MainFragment.OnButtonSelectedListener {

    private RelativeLayout mDrawer;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerAdapter mDrawerAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);

        mDrawer = (RelativeLayout) findViewById(R.id.drawer);
        mDrawerList = (RecyclerView) findViewById(R.id.drawer_list);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));

        mDrawerAdapter = new DrawerAdapter(this,  (new DrawerData()).getDrawerList());

        mDrawerAdapter.SetOnItemClickListener(new DrawerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                selectItem(position);
            }

        });
        mDrawerList.setAdapter(mDrawerAdapter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                mToolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.drawerOpen,  /* "open drawer" description for accessibility */
                R.string.drawerClose  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {
        switch(position) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
                break;
            case 1:
                Intent intent = new Intent(this, SearchBusActivity.class);
                startActivity(intent);
                break;
            /*case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, MyRecyclerviewFragment.newInstance(1)).commit();
                break;
            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(0)).commit();
                break;
            case 6:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(1)).commit();
                break;
            case 7:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(2)).commit();
                break;*/
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
                break;
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onButtonItemSelected(int id){
        Intent intent;

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            Button searchBus = (Button) rootView.findViewById(R.id.SearchBus);
            Button nearestStop = (Button) rootView.findViewById(R.id.NearestStop);
            Button notifyMe = (Button) rootView.findViewById(R.id.NotifyMe);
            Button specialSchedules = (Button) rootView.findViewById(R.id.SpecialSchedules);
            Button busTracker = (Button) rootView.findViewById(R.id.BusTracker);

            searchBus.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Toast.makeText(getActivity(), "Search Buses is Clicked!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(),SearchBusActivity.class);
                    startActivity(intent);
                }
            });

            nearestStop.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Toast.makeText(getActivity(), "Nearest Stop is Clicked!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(),NearestStop.class);
                    startActivity(intent);
                }
            });

            notifyMe.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Toast.makeText(getActivity(),"Notify Me is Clicked!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(),NotifyMe.class);
                    startActivity(intent);
                }
            });

            specialSchedules.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Toast.makeText(getActivity(),"Special Schedules is Clicked!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(),SpecialSchedules.class);
                    startActivity(intent);
                }
            });

            //busTracker.setOnClickListener(new View.OnClickListener(){
            //    @Override
            //    public void onClick(View view){
            //       Toast.makeText(getActivity(),"Bus Tracker is Clicked!",Toast.LENGTH_SHORT).show();
            //        Intent intent = new Intent(view.getContext(),BusTracker.class);
            //        startActivity(intent);
            //    }
            //});
            return rootView;
        }
    }
}
