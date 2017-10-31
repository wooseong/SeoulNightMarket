package seoulnightmarket.seoulnightmarket.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.handMadeToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.handMadeDrawer);

        ImageView placePicutre = (ImageView) findViewById(R.id.handMadeImage);
        Singleton.getInstance().setStoreImageView(placePicutre);

        ViewPager viewPager = (ViewPager) findViewById(R.id.handMadeViewpager);
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) findViewById(R.id.handMadeTabs);
        tabs.setupWithViewPager(viewPager);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.md_yellow_500, getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        final Button btnyyd = (Button) findViewById(R.id.btnyyd);
        btnyyd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marketName = btnyyd.getText().toString();
                Singleton.getInstance().setRegion(marketName);

                startActivity(new Intent(HandMadeActivity.this, AreaInformationWithTabBar.class));
                finish();

                mDrawerLayout.closeDrawers();
            }
        });

        final Button btnddp = (Button) findViewById(R.id.btnddp);
        btnddp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marketName = btnddp.getText().toString();
                Singleton.getInstance().setRegion(marketName);

                startActivity(new Intent(HandMadeActivity.this, AreaInformationWithTabBar.class));
                finish();

                mDrawerLayout.closeDrawers();
            }
        });

        final Button btnbp = (Button) findViewById(R.id.btnbp);
        btnbp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marketName = btnbp.getText().toString();
                Singleton.getInstance().setRegion(marketName);

                startActivity(new Intent(HandMadeActivity.this, AreaInformationWithTabBar.class));
                finish();

                mDrawerLayout.closeDrawers();
            }
        });

        final Button btncgc = (Button) findViewById(R.id.btncgc);
        btncgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marketName = btncgc.getText().toString();
                Singleton.getInstance().setRegion(marketName);

                startActivity(new Intent(HandMadeActivity.this, AreaInformationWithTabBar.class));
                finish();

                mDrawerLayout.closeDrawers();
            }
        });

        final Button btncggj = (Button) findViewById(R.id.btncggj);
        btncggj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marketName = btncggj.getText().toString();
                Singleton.getInstance().setRegion(marketName);

                startActivity(new Intent(HandMadeActivity.this, AreaInformationWithTabBar.class));
                finish();

                mDrawerLayout.closeDrawers();
            }
        });

        Button btnMyReserve = (Button) findViewById(R.id.btnMyReserve);
        btnMyReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Singleton.getInstance().getNowLogin()) {
                    startActivity(new Intent(HandMadeActivity.this, NumberTicketActivity.class));
                } else if (!Singleton.getInstance().getNowLogin()) {
                    startActivity(new Intent(HandMadeActivity.this, LoginActivity.class));
                }

                finish();
                mDrawerLayout.closeDrawers();
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.md_black_1000));
        }
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
        int id = item.getItemId();

        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
}