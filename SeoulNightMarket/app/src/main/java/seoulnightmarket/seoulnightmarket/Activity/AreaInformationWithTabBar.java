package seoulnightmarket.seoulnightmarket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

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
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager); // Setting ViewPager for each Tabs
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs); // Set Tabs inside Toolbar
        tabs.setupWithViewPager(viewPager);

        TextView textViewRegion = (TextView) findViewById(R.id.textViewRegion);
        TextView textViewDate = (TextView) findViewById(R.id.textViewDate);

        ImageView placePicutre = (ImageView) findViewById(R.id.image);
//        Singleton.getInstance().setStoreImageView(placePicutre);

        switch (region) {
            case "여의도":
                placePicutre.setImageResource(R.drawable.yydintro);
                textViewRegion.setText("여의도 월드나이트마켓");
                textViewDate.setText("매주 금토 18:00~23:00");
                appBarLayout.setBackgroundColor(getResources().getColor(R.color.md_orange_800));
                textViewRegion.setTextColor(getResources().getColor(R.color.md_light_blue_800));
                textViewDate.setTextColor(getResources().getColor(R.color.md_light_blue_800));
                tabs.setTabTextColors(ContextCompat.getColor(this, R.color.md_white_1000), ContextCompat.getColor(this, R.color.md_light_blue_800));
                tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_light_blue_800));
                collapsingToolbarLayout.setContentScrimResource(R.color.md_orange_800);
                break;
            case "DDP":
                placePicutre.setImageResource(R.drawable.ddpintro);
                textViewRegion.setText("DDP 청춘런웨이마켓");
                textViewDate.setText("매주 금토 18:00~23:00");
                appBarLayout.setBackgroundColor(getResources().getColor(R.color.md_deep_purple_400));
                textViewRegion.setTextColor(getResources().getColor(R.color.md_yellow_600));
                textViewDate.setTextColor(getResources().getColor(R.color.md_yellow_600));
                tabs.setTabTextColors(ContextCompat.getColor(this, R.color.md_white_1000), ContextCompat.getColor(this, R.color.md_yellow_600));
                tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_yellow_600));
                collapsingToolbarLayout.setContentScrimResource(R.color.md_deep_purple_400);
                break;
            case "반포":
                placePicutre.setImageResource(R.drawable.bpintro);
                textViewRegion.setText("반포 낭만달빛마켓");
                textViewDate.setText("매주 금토 18:00~23:00");
                appBarLayout.setBackgroundColor(getResources().getColor(R.color.md_yellow_600));
                textViewRegion.setTextColor(getResources().getColor(R.color.md_blue_900));
                textViewDate.setTextColor(getResources().getColor(R.color.md_blue_900));
                tabs.setTabTextColors(ContextCompat.getColor(this, R.color.md_white_1000), ContextCompat.getColor(this, R.color.md_blue_900));
                tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_blue_900));
                collapsingToolbarLayout.setContentScrimResource(R.color.md_yellow_600);
                break;
            case "청계천":
                placePicutre.setImageResource(R.drawable.cgcintro);
                textViewRegion.setText("청계천 타임슬립마켓");
                textViewDate.setText("매주 토 16:30~22:30 일 16:00~21:00");
                appBarLayout.setBackgroundColor(getResources().getColor(R.color.md_lime_500));
                textViewRegion.setTextColor(getResources().getColor(R.color.md_deep_purple_700));
                textViewDate.setTextColor(getResources().getColor(R.color.md_deep_purple_700));
                tabs.setTabTextColors(ContextCompat.getColor(this, R.color.md_white_1000), ContextCompat.getColor(this, R.color.md_deep_purple_700));
                tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_deep_purple_700));
                collapsingToolbarLayout.setContentScrimResource(R.color.md_lime_500);
                break;
            case "청계광장":
                placePicutre.setImageResource(R.drawable.cggjintro);
                textViewRegion.setText("청계광장");
                textViewDate.setText("시즌별 금,토,일 18:00~23:00");
                appBarLayout.setBackgroundColor(getResources().getColor(R.color.md_cyan_800));
                textViewRegion.setTextColor(getResources().getColor(R.color.md_white_1000));
                textViewDate.setTextColor(getResources().getColor(R.color.md_white_1000));
                tabs.setTabTextColors(ContextCompat.getColor(this, R.color.md_white_1000), ContextCompat.getColor(this, R.color.md_yellow_500));
                tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_yellow_500));
                collapsingToolbarLayout.setContentScrimResource(R.color.md_cyan_800);
                break;
            default:
                break;
        }

        ActionBar supportActionBar = getSupportActionBar(); // Adding menu icon to Toolbar
        if (supportActionBar != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme()); // 드로월 모양
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.white, getTheme())); // 드로월 색깔
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view); // Create Navigation drawer and inlfate layout
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() { // Set behavior of Navigation drawer
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) { // This method will trigger on item Click of navigation menu
                menuItem.setChecked(true); // Set item in checked state

                String menuName = menuItem.getTitle().toString();
                Singleton.getInstance().setRegion(menuName);

                finish();
                startActivity(new Intent(AreaInformationWithTabBar.this, AreaInformationWithTabBar.class));

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

        if (region.equals("여의도") || region.equals("DDP") || region.equals("반포")) {
            adapter.addFragment(new FragmentConcert(), "공연");
        } else if (region.equals("청계천")) {
            adapter.addFragment(new FragmentConcertCheonggye(), "공연");
        } else if (region.equals("청계광장")) {
            adapter.addFragment(new FragmentConcertPlaza(), "공연");
        }

        adapter.addFragment(new FragmentDirections(), "오시는길");

        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentStatePagerAdapter {
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