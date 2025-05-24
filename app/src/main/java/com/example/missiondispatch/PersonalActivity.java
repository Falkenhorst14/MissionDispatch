package com.example.missiondispatch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PersonalActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    TabLayout tabLayout;
    private int einsatzkraftId;
    DBHandler dbHandler;

    private TabLayout.OnTabSelectedListener tabSelectedListener;
    private boolean isProgrammaticallyTabChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal);

        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        dbHandler = new DBHandler(getApplicationContext());

        tabSelectedListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(isProgrammaticallyTabChange)
                {
                    isProgrammaticallyTabChange = false;
                    return;
                }

                Fragment fragment = null;
                Bundle arguments = null;

                switch (tab.getPosition())
                {
                    case 0:
                        fragment = new PersonalFragment();
                        break;
                    case 1:
                        fragment = new AbschnitteFragment();
                        break;
                    case 2:
                        fragment = new PersonalDetailFragment();
                        break;
                }

                if (fragment != null) {
                    if (arguments != null) {
                        fragment.setArguments(arguments);
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framelayout, fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            // No addToBackStack here if you want tabs to just replace content
                            // Or, if you do, ensure your onBackStackChanged listener also uses the flag
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
        tabLayout.addOnTabSelectedListener(tabSelectedListener);


        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new PersonalFragment())
                .addToBackStack(null)
                .commit();

        Intent intentPersonalDetail = getIntent();

        try {
            einsatzkraftId = intentPersonalDetail.getIntExtra("einsatzkraftID", -1);
            //Toast.makeText(this, "Es wurde auf ID " + einsatzkraftId + " geklickt.", Toast.LENGTH_SHORT).show();
            if (einsatzkraftId != -1) {
                Bundle bundle = new Bundle();
                bundle.putInt("einsatzkraftID", einsatzkraftId);
                PersonalDetailFragment detailFragment = new PersonalDetailFragment();
                detailFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, detailFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();

                TabLayout.Tab tab = tabLayout.getTabAt(2);
                if (tab != null) {
                    //flag change before firing the OnTabSelectedListener
                    isProgrammaticallyTabChange = true;
                    tab.select();
                }
            }
            else {


                //tabLayout.addOnTabSelectedListener(tabSelectedListener);
                /*Fragment fragmentSwitch = null;
                fragmentSwitch = new PersonalDetailFragment();
                fragmentSwitch.setArguments(bundle);*/

            }
        }

        catch (Exception e)
        {
            Log.e("PersonalActivity", "Error processing intent: " + e.getMessage(), e);
            // Load a default fragment in case of error
            if (getSupportFragmentManager().findFragmentById(R.id.framelayout) == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, new PersonalFragment())
                        .commit();
            }
        }


        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.framelayout);
            TabLayout.Tab tabToSelect = null;

            if (currentFragment instanceof PersonalFragment) {
                tabToSelect = tabLayout.getTabAt(0);
            } else if (currentFragment instanceof AbschnitteFragment) {
                tabToSelect = tabLayout.getTabAt(1);
            } else if (currentFragment instanceof PersonalDetailFragment) {
                tabToSelect = tabLayout.getTabAt(2);
            }
            if (tabToSelect != null && tabToSelect.getPosition() != tabLayout.getSelectedTabPosition()) {
                isProgrammaticallyTabChange = true; // flag wird vor Auswahl gesetzt
                tabToSelect.select();
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}