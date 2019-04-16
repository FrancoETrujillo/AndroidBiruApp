package com.example.edgarnunezzenuitycom.basicsandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.example.edgarnunezzenuitycom.basicsandroid.dummy.Playground;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements MapFragment.OnFragmentInteractionListener, ActivePlaces.OnFragmentInteractionListener {

    private static String TAG = "MAIN";
    SectionsPageAdapter m_sectionsPageAdapter;
    CustomViewPager mviewPager;
    private BottomNavigationView mBottomNav;

    @Override
    public void onAttachFragment(Fragment fragment) {
        //getSupportFragmentManager().beginTransaction().replace(R.id.container1, new MapFragment()).commit();

    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container1, new MapFragment()).commit();
        }

        mviewPager = findViewById(R.id.container1);
        m_sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mviewPager.setAdapter(m_sectionsPageAdapter);

        setupViewPager(mviewPager);

         //getting the navigation view
        mBottomNav = findViewById(R.id.navigation);

        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_recents:
                        mviewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_map:
                        mviewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_serving:
                        mviewPager.setCurrentItem(2);
                        break;
                    case R.id.navigation_profile:
                        mviewPager.setCurrentItem(3);
                        break;

                }
                return true;
            }
        });

        //TODO: FIX ISSUE WITH MAPBOX FRAGMENT, FIND A WAY TO DINAMICALLY LOAD THE MAP FRAGMENT
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        }


        //Pag adapter connecting the tabs with the fragments
    private void setupViewPager(ViewPager viewPager) {
        m_sectionsPageAdapter.addFragment(new ActivePlaces(),"TAB1");
        m_sectionsPageAdapter.addFragment(new Playground(),"TAB2");
        m_sectionsPageAdapter.addFragment(new MapFragment(), "TAB3");
        m_sectionsPageAdapter.addFragment(new Serving(), "TAB4");


        mviewPager.setAdapter(m_sectionsPageAdapter);
    }

}
