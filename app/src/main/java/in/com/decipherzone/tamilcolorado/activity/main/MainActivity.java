package in.com.decipherzone.tamilcolorado.activity.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.com.decipherzone.base.BaseAppCompatActivity;
import in.com.decipherzone.base.MainView;
import in.com.decipherzone.tamilcolorado.R;
import in.com.decipherzone.tamilcolorado.fragment.base.BaseFragment;
import in.com.decipherzone.tamilcolorado.fragment.base.FragmentNames;
import in.com.decipherzone.tamilcolorado.fragment.committee.CommitteeFragment;
import in.com.decipherzone.tamilcolorado.fragment.history.HistoryFragment;
import in.com.decipherzone.tamilcolorado.fragment.home.HomeFragment;
import in.com.decipherzone.tamilcolorado.fragment.membership.MembershipFragment;
import in.com.decipherzone.tamilcolorado.img_slider.SlidingImage_Adapter;

public class MainActivity extends BaseAppCompatActivity
        implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private BaseFragment fragment;


    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.pager)
    ViewPager mPager;

    private int currentPage = 0;
    private int NUM_PAGES = 0;

    ArrayList<Integer> ImagesArray = new ArrayList<>();

    {
        ImagesArray.add(R.drawable.header_banner);
        ImagesArray.add(R.drawable.header_banner);
        ImagesArray.add(R.drawable.header_banner);
        ImagesArray.add(R.drawable.header_banner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initializeView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);  // provide compatibility to all the versions
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            replaceFragment(FragmentNames.HomeFragment, null, true, true);
        } else if (id == R.id.nav_membership) {
            replaceFragment(FragmentNames.MembershipFragment, null, false, false);
            makeToast("Membership");
        } else if (id == R.id.nav_history) {
            replaceFragment(FragmentNames.HistoryFragment, null, false, false);
            makeToast("History");
        } else if (id == R.id.nav_committee) {
            replaceFragment(FragmentNames.CommitteeFragment, null, false, false);
            makeToast("Committee");
        } else if (id == R.id.nav_about) {
            makeToast("About");
        } else if (id == R.id.nav_event) {
            makeToast("Event");
        } else if (id == R.id.nav_contact) {
            makeToast("Contact");

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initializeView() {
        setSlider();
        replaceFragment(FragmentNames.HomeFragment, null, true, true);
    }

    private void setSlider() {
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this, ImagesArray));

        NUM_PAGES = ImagesArray.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    private void replaceFragment(FragmentNames fragmentName, Bundle bundle, boolean isFirst, boolean clearBackStack) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (clearBackStack) {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        switch (fragmentName) {
            case HomeFragment:
                fragment = new HomeFragment();
                break;
            case MembershipFragment:
                fragment = new MembershipFragment();
                break;
            case HistoryFragment:
                fragment = new HistoryFragment();
                break;
            case CommitteeFragment:
                fragment = new CommitteeFragment();
                break;
        }
        // if user passes the @bundle in not null, then can be added to the fragment
        if (bundle != null) {
            try {
                fragment.setArguments(bundle);
            } catch (Exception e) {
                System.out.println(fragment.getArguments());  // print exception
            }
        }

        // this is for the very first fragment not to be added into the back stack.
        if (!isFirst) {
            ft.addToBackStack(fragment.getClass().getName() + "");
        } else {
            // TODO Need to clear back stack
//            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
//                fm.popBackStack();
//            }
        }

        fragment.fragmentType = fragmentName.getValue();

        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

}
