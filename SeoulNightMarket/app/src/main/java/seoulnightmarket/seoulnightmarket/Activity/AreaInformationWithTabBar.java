package seoulnightmarket.seoulnightmarket.Activity;

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
import seoulnightmarket.seoulnightmarket.fragment.FragmentConcert;
import seoulnightmarket.seoulnightmarket.fragment.FragmentConcertCheonggye;
import seoulnightmarket.seoulnightmarket.fragment.FragmentConcertPlaza;
import seoulnightmarket.seoulnightmarket.fragment.FragmentDirections;
import seoulnightmarket.seoulnightmarket.fragment.FragmentIntroduction;
import seoulnightmarket.seoulnightmarket.fragment.FragmentMarket;

public class AreaInformationWithTabBar extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private String region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.areainformationwithtabbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Adding Toolbar to Main screen
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        region = Singleton.getInstance().getRegion();

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar); // Set Collapsing Toolbar layout to the screen
        ImageView placePicutre = (ImageView) findViewById(R.id.image);
        placePicutre.setImageDrawable(getResources().getDrawable(R.drawable.bom));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager); // Setting ViewPager for each Tabs
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs); // Set Tabs inside Toolbar
        tabs.setupWithViewPager(viewPager);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view); // Create Navigation drawer and inlfate layout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBar supportActionBar = getSupportActionBar(); // Adding menu icon to Toolbar
        if (supportActionBar != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme()); // 드로월 모양
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.white, getTheme())); // 드로월 색깔
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() { // Set behavior of Navigation drawer

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) { // This method will trigger on item Click of navigation menu
                menuItem.setChecked(true); // Set item in checked state

                // TODO: handle navigation

                mDrawerLayout.closeDrawers(); // Closing drawer on item click

                return true;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) // Add Fragments to Tabs
    {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentIntroduction(), "소개");
        adapter.addFragment(new FragmentMarket(), "야시장");

        if (region.equals("Yeouido") || region.equals("DDP") || region.equals("Banpo")) {
            adapter.addFragment(new FragmentConcert(), "공연");
        } else if (region.equals("Cheonggyecheon")) {
            adapter.addFragment(new FragmentConcertCheonggye(), "공연");
        } else if (region.equals("CheonggyePlaza")) {
            adapter.addFragment(new FragmentConcertPlaza(), "공연");
        }

        adapter.addFragment(new FragmentDirections(), "오시는길");

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
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}