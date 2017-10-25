package seoulnightmarket.seoulnightmarket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.etc.Singleton;
import seoulnightmarket.seoulnightmarket.fragment.FragmentInformation;
import seoulnightmarket.seoulnightmarket.fragment.FragmentProduct;
import seoulnightmarket.seoulnightmarket.fragment.FragmentReview;

public class HandMadeActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_made);

        Toolbar toolbar = (Toolbar) findViewById(R.id.handMadeToolbar); // Adding Toolbar to Main screen
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.handMadeDrawer);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.handMade_collapsing_toolbar); // Set Collapsing Toolbar layout to the screen
        collapsingToolbar.setTitle("도오오깨애애비이");

        ImageView placePicutre = (ImageView) findViewById(R.id.handMadeImage);
        Singleton.getInstance().setStoreImageView(placePicutre);

        ViewPager viewPager = (ViewPager) findViewById(R.id.handMadeViewpager); // Setting ViewPager for each Tabs
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) findViewById(R.id.handMadeTabs); // Set Tabs inside Toolbar
        tabs.setupWithViewPager(viewPager);

        ActionBar supportActionBar = getSupportActionBar(); // Adding menu icon to Toolbar
        if (supportActionBar != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.md_orange_500, getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.handMade_nav_view); // Create Navigation drawer and inlfate layout
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() { // Set behavior of Navigation drawer
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) { // This method will trigger on item Click of navigation menu
                menuItem.setChecked(true); // Set item in checked state

                String menuName = menuItem.getTitle().toString();
                Singleton.getInstance().setRegion(menuName);

                finish();
                startActivity(new Intent(HandMadeActivity.this, AreaInformationWithTabBar.class));

                mDrawerLayout.closeDrawers(); // Closing drawer on item click

                return true;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) { // Add Fragments to Tabs
        HandMadeActivity.Adapter adapter = new Adapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentProduct(), "상품");
        adapter.addFragment(new FragmentInformation(), "정보");
        adapter.addFragment(new FragmentReview(), "리뷰");

        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
}