package seoulnightmarket.seoulnightmarket.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
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
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private AnimationDrawable drawable;
    private ImageView placePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.areainformationwithtabbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // 화면에 툴바 추가
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        region = Singleton.getInstance().getRegion();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs); // 탭바에 뷰페이거 설정
        tabs.setupWithViewPager(viewPager);
        TabLayout.Tab tabDefault = tabs.getTabAt(1);
        tabDefault.select();

        TextView textViewRegion = (TextView) findViewById(R.id.textViewRegion);
        placePicture = (ImageView) findViewById(R.id.image);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.line);

        switch (region) {
            case "여의도":
                placePicture.setBackgroundResource(R.drawable.anim_yyd);
                drawable = (AnimationDrawable) placePicture.getBackground();
                drawable.start();
                textViewRegion.setText("여의도 월드나이트마켓");
                appBarLayout.setBackgroundColor(getResources().getColor(R.color.md_deep_orange_400));
                textViewRegion.setTextColor(getResources().getColor(R.color.md_white_1000));
                tabs.setTabTextColors(ContextCompat.getColor(this, R.color.md_black_1000), ContextCompat.getColor(this, R.color.md_black_1000));
                tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_orange_400));
                collapsingToolbarLayout.setContentScrimResource(R.color.md_deep_orange_400);
                linearLayout.setBackgroundResource(R.color.md_orange_400);
                break;
            case "DDP":
                placePicture.setBackgroundResource(R.drawable.anim_ddp);
                drawable = (AnimationDrawable) placePicture.getBackground();
                drawable.start();
                textViewRegion.setText("DDP 청춘런웨이마켓");
                appBarLayout.setBackgroundColor(Color.parseColor("#5e008a"));
                textViewRegion.setTextColor(getResources().getColor(R.color.md_white_1000));
                tabs.setTabTextColors(ContextCompat.getColor(this, R.color.md_black_1000), ContextCompat.getColor(this, R.color.md_black_1000));
                tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_deep_purple_400));
                collapsingToolbarLayout.setContentScrimResource(R.color.md_deep_purple_A200);
                linearLayout.setBackgroundResource(R.color.md_deep_purple_400);
                break;
            case "반포":
                placePicture.setBackgroundResource(R.drawable.anim_bp);
                drawable = (AnimationDrawable) placePicture.getBackground();
                drawable.start();
                textViewRegion.setText("반포 낭만달빛마켓");
                appBarLayout.setBackgroundColor(getResources().getColor(R.color.md_yellow_600));
                textViewRegion.setTextColor(getResources().getColor(R.color.md_white_1000));
                tabs.setTabTextColors(ContextCompat.getColor(this, R.color.md_black_1000), ContextCompat.getColor(this, R.color.md_black_1000));
                tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_yellow_500));
                collapsingToolbarLayout.setContentScrimResource(R.color.md_yellow_600);
                linearLayout.setBackgroundResource(R.color.md_yellow_500);
                break;
            case "청계천":
                placePicture.setBackgroundResource(R.drawable.anim_cgc);
                drawable = (AnimationDrawable) placePicture.getBackground();
                drawable.start();
                textViewRegion.setText("청계천 타임슬립마켓");
                appBarLayout.setBackgroundColor(Color.parseColor("#bdcf1b"));
                textViewRegion.setTextColor(getResources().getColor(R.color.md_white_1000));
                tabs.setTabTextColors(ContextCompat.getColor(this, R.color.md_black_1000), ContextCompat.getColor(this, R.color.md_black_1000));
                tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_light_green_500));
                collapsingToolbarLayout.setContentScrimResource(R.color.md_lime_500);
                linearLayout.setBackgroundResource(R.color.md_light_green_500);
                break;
            case "청계광장":
                placePicture.setBackgroundResource(R.drawable.anim_cggj);
                drawable = (AnimationDrawable) placePicture.getBackground();
                drawable.start();
                textViewRegion.setText("청계광장");
                appBarLayout.setBackgroundColor(getResources().getColor(R.color.md_cyan_800));
                textViewRegion.setTextColor(getResources().getColor(R.color.md_white_1000));
                tabs.setTabTextColors(ContextCompat.getColor(this, R.color.md_black_1000), ContextCompat.getColor(this, R.color.md_black_1000));
                tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_teal_600));
                collapsingToolbarLayout.setContentScrimResource(R.color.md_cyan_800);
                linearLayout.setBackgroundResource(R.color.md_teal_600);
                break;
            default:
                break;
        }

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        ActionBar supportActionBar = getSupportActionBar(); // 툴바에 메뉴 추가
        if (supportActionBar != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme()); // 드로월 모양
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.md_white_1000, getTheme())); // 드로월 색깔
            supportActionBar.setHomeAsUpIndicator(indicator);
        }

        final Button btnyyd = (Button) findViewById(R.id.btnyyd);
        btnyyd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marketName = btnyyd.getText().toString();
                Singleton.getInstance().setRegion(marketName);

                startActivity(new Intent(AreaInformationWithTabBar.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(AreaInformationWithTabBar.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(AreaInformationWithTabBar.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(AreaInformationWithTabBar.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(AreaInformationWithTabBar.this, AreaInformationWithTabBar.class));
                finish();

                mDrawerLayout.closeDrawers();
            }
        });

        Button btnMyReserve = (Button) findViewById(R.id.btnMyReserve);
        btnMyReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Singleton.getInstance().getNowLogin()) {
                    startActivity(new Intent(AreaInformationWithTabBar.this, NumberTicketActivity.class));
                } else if (!Singleton.getInstance().getNowLogin()) {
                    startActivity(new Intent(AreaInformationWithTabBar.this, LoginActivity.class));
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

    private void setupViewPager(ViewPager viewPager) // 탭바에 프레그먼트 추가
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
        int id = item.getItemId();

        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() { // 액티비티 중단 되었을때
        super.onPause();

        drawable.stop();
        Log.e("Pause Test", "pause!");
    }

    @Override
    protected void onResume() { // 액티비티 재개 했을때
        super.onResume();

        drawable.start();
        Log.e("Resume Test", "resume!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("Destroy Test", "destroy!");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Log.e("BackPressed Test", "BackPressed!");
    }
}