package edu.nd.phr;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class LandingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private ArrayAdapter<String> healthAdapter;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_landing, container, false);
            List<String> health_menu = new ArrayList<String>();
            health_menu.add("Appointments");
            health_menu.add("Blood Glucose");
            health_menu.add("Blood Pressure");
            health_menu.add("Cholestoral");
            health_menu.add("Emergency Profile");
            health_menu.add("Exercise");
            health_menu.add("Family History");
            health_menu.add("Height");
            health_menu.add("Immunizations");
            health_menu.add("Measurements");
            health_menu.add("Medication");
            health_menu.add("Menstruation");

            healthAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_health_options,R.id.health_options_textview,health_menu);
            ListView listView = (ListView) rootView.findViewById(R.id.list_health_options);
            listView.setAdapter(healthAdapter);
            return rootView;
        }
    }
}
