package com.example.ganesh.sutransit;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.RelativeLayout;


public class SearchBusActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_bus);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_bus, menu);
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
                Intent intent0 = new Intent(this, SearchBusActivity.class);
                startActivity(intent0);
                break;
            case 1:
                Intent intent1 = new Intent(this, NearestStop.class);
                startActivity(intent1);
                break;
            /*case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(0)).commit();
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_search_bus, container, false);
            return rootView;
        }
    }
}
