package com.movilmx.demorappi;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.movilmx.core.ui.GenericActivity;
import com.movilmx.core.ui.GenericFragment;
import com.movilmx.core.ui.GenericFragmentFactory;
import com.movilmx.demorappi.fragments.DetailVideoFragment;
import com.movilmx.demorappi.fragments.PopularFragment;
import com.movilmx.demorappi.fragments.TopRatedFragment;
import com.movilmx.demorappi.fragments.UpComingFragment;
import com.movilmx.demorappi.fragments.VFragmentFactory;

public class MainActivity extends GenericActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

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
    public void addFragment(GenericFragment fragment) {
        Log.d(TAG, "addFragment() called with: fragment = [" + fragment + "]");

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fAdded = getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName());
        /*el fragmento ya fue agregado, y no es pdp */
        if(fAdded != null && !fAdded.getClass().getSimpleName().equals(DetailVideoFragment.TAG) &&fAdded.isAdded()){//Si el fragmento fue previamente agregado
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            while (fragmentManager.getBackStackEntryCount() > 0) {
                final int last = fragmentManager.getBackStackEntryCount() - 1;
                final FragmentManager.BackStackEntry entry =
                        fragmentManager.getBackStackEntryAt(last);
                if (fragment.getClass().getSimpleName().equals(entry.getName())) {
                    break;
                }
                fragmentManager.popBackStackImmediate();
            }

            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        }else {//Si no fue agregado, lo agregamos.
            fragmentManager.beginTransaction()
                    .add(flContainer.getId(), fragment, fragment.getClass().getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    public VFragmentFactory getFragmentsFactory() {
        return vFragmentFactory;
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

    /**
     * fabrica de fragmentos mostrados en UI
     */
    private VFragmentFactory     vFragmentFactory = new VFragmentFactory();
    private BottomNavigationView bnvContainer;
    private FrameLayout          flContainer;
    private PopularFragment      popularFragment  = new PopularFragment();
    private TopRatedFragment     topRatedFragment = new TopRatedFragment();
    private UpComingFragment     upComingFragment = new UpComingFragment();
}
