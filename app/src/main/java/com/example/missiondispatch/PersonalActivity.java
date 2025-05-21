package com.example.missiondispatch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class PersonalActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    TabLayout tabLayout;
    private int einsatzkraftId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal);

        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new PersonalFragment())
                .addToBackStack(null)
                .commit();

        Intent intentPersonalDetail = getIntent();

        einsatzkraftId = intentPersonalDetail.getIntExtra("einsatzkraftID", -1);
        Toast.makeText(this, "Sie haben auf ID " + einsatzkraftId + " geklickt.", Toast.LENGTH_LONG).show();
        if (einsatzkraftId != -1)
        {
            TabLayout.Tab tab = tabLayout.getTabAt(2);
            tab.select();
            Fragment fragmentSwitch = null;
            fragmentSwitch = new PersonalDetailFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragmentSwitch)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }



        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
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

                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}