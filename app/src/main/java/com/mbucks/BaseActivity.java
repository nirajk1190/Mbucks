package com.mbucks;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mbucks.fragment.DasgboardFragment;
import com.mbucks.fragment.GoalFragment;
import com.mbucks.fragment.HelpFragment;
import com.mbucks.fragment.NotficationFragment;
import com.mbucks.fragment.ToolsFragment;


public class BaseActivity extends AppCompatActivity {
    private FloatingActionButton fab_button1;

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        init();

    }

    private void init() {
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Fragment fragment = new DasgboardFragment();
        loadFragment(fragment);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.dashboard:
                fragment = new DasgboardFragment();
                loadFragment(fragment);
                return true;
            case R.id.goals:
                fragment = new GoalFragment();
                loadFragment(fragment);
                return true;
            case R.id.notification:
                fragment = new NotficationFragment();
                loadFragment(fragment);
                return true;
            case R.id.help:
                fragment = new HelpFragment();
                loadFragment(fragment);
                return true;
            case R.id.tools:
                fragment = new ToolsFragment();
                loadFragment(fragment);
                return true;

        }
        return false;
    };


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
