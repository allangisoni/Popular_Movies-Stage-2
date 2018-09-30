
package com.example.android.pendomoviz.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.pendomoviz.fragments.MovieDetailsFragment;
import com.example.android.pendomoviz.R;
import com.example.android.pendomoviz.fragments.ReviewsFragment;
import com.example.android.pendomoviz.fragments.TrailersFragment;
import com.example.android.pendomoviz.model.Moviz;

import java.util.ArrayList;
import java.util.List;

public class DetailsMain extends AppCompatActivity {



    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Moviz moviz;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_main);
        initUIComponents();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        moviz = intent.getParcelableExtra("movieDetails");


    }

    private void initUIComponents() {

        toolbar = findViewById(R.id.toolbar);
        viewPager =  findViewById(R.id.viewpager);

        tabLayout =  findViewById(R.id.tabs);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MovieDetailsFragment(), "Details");
        adapter.addFragment(new TrailersFragment(), "Trailers");
        adapter.addFragment(new ReviewsFragment(), "Reviews");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
           // return mFragmentList.get(position);

            switch (position) {

                case 0:
                    MovieDetailsFragment tab1 = new MovieDetailsFragment();
                    tab1.setArguments(movieParcelable());
                    return tab1;

                case 1:

                    TrailersFragment tab2 = new TrailersFragment();
                    tab2.setArguments(movieParcelable());
                    return tab2;
                case 2:

                    ReviewsFragment tab3 = new ReviewsFragment();
                    tab3.setArguments(movieParcelable());
                    return tab3;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public Bundle movieParcelable(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("movieDetails", moviz);
        return bundle;
    }

/**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trailer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.ic_share)
         {

         }

        return true;

    } **/

}
