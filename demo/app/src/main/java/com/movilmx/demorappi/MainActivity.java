package com.movilmx.demorappi;

import android.os.Bundle;
import android.widget.FrameLayout;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.movilmx.core.ui.GenericActivity;
import com.movilmx.core.ui.GenericFragment;
import com.movilmx.core.ui.GenericFragmentFactory;
import com.movilmx.demorappi.fragments.PopularFragment;
import com.movilmx.demorappi.fragments.TopRatedFragment;
import com.movilmx.demorappi.fragments.UpComingFragment;

public class MainActivity extends GenericActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        getSupportFragmentManager()
                .beginTransaction()
                .add(flContainer.getId(),popularFragment, PopularFragment.class.getSimpleName())
                .add(flContainer.getId(),topRatedFragment, TopRatedFragment.class.getSimpleName())
                .add(flContainer.getId(),upComingFragment,UpComingFragment.class.getSimpleName())
                .hide(topRatedFragment)
                .hide(upComingFragment)
                .show(popularFragment)
                .commit();

    }

    @Override
    public <T extends GenericFragment> void addFragment(T fragment) {

    }

    @Override
    public <T extends GenericFragmentFactory> T getFragmentsFactory() {
        return null;
    }

    private void assignViews(){
        bnvContainer = findViewById(R.id.bnv_container);
        flContainer  = findViewById(R.id.fl_container);

        bnvContainer.setOnNavigationItemSelectedListener(menuItem -> {
            try{
                switch (menuItem.getItemId()){
                    case R.id.nav_popular:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .hide(topRatedFragment)
                                .hide(upComingFragment)
                                .show(popularFragment)
                                .commit();
                        break;
                    case R.id.nav_top_rated:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .hide(popularFragment)
                                .hide(upComingFragment)
                                .show(topRatedFragment)
                                .commit();
                        break;
                    case R.id.nav_up_coming:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .hide(topRatedFragment)
                                .hide(popularFragment)
                                .show(upComingFragment)
                                .commit();
                        break;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return true;
        });
    }

    private BottomNavigationView bnvContainer;
    private FrameLayout          flContainer;
    private PopularFragment      popularFragment  = new PopularFragment();
    private TopRatedFragment     topRatedFragment = new TopRatedFragment();
    private UpComingFragment     upComingFragment = new UpComingFragment();
}
