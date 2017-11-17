package seoulnightmarket.seoulnightmarket.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;
import seoulnightmarket.seoulnightmarket.fragment.FragmentInformation;
import seoulnightmarket.seoulnightmarket.fragment.FragmentMenu;
import seoulnightmarket.seoulnightmarket.fragment.FragmentReview;

public class DetailActivity extends AppCompatActivity {
    private TextView currentOrderNumber;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Singleton.getInstance().setDuplicated(false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        currentOrderNumber = (TextView) findViewById(R.id.currentOrderNumber);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.detailDrawer);

        ImageView placePicutre = (ImageView) findViewById(R.id.detailImage);
        Singleton.getInstance().setStoreImageView(placePicutre);

        TextView textView = (TextView) findViewById(R.id.store_name);
        Singleton.getInstance().setStoreTextView(textView);
        Singleton.getInstance().setWaitTextView(currentOrderNumber);

        ViewPager viewPager = (ViewPager) findViewById(R.id.detailViewpager);
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) findViewById(R.id.detailTabs);
        tabs.setupWithViewPager(viewPager);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme()); // 드로월 모양
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.md_yellow_500, getTheme())); // 드로월 색깔
            supportActionBar.setHomeAsUpIndicator(indicator);
        }

        final Button btnyyd = (Button) findViewById(R.id.btnyyd);
        btnyyd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marketName = btnyyd.getText().toString();
                Singleton.getInstance().setRegion(marketName);

                startActivity(new Intent(DetailActivity.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(DetailActivity.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(DetailActivity.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(DetailActivity.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(DetailActivity.this, AreaInformationWithTabBar.class));
                finish();

                mDrawerLayout.closeDrawers(); // Closing drawer on item click
            }
        });

        Button btnMyReserve = (Button) findViewById(R.id.btnMyReserve);
        btnMyReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Singleton.getInstance().getNowLogin()) {
                    startActivity(new Intent(DetailActivity.this, NumberTicketActivity.class));
                } else if (!Singleton.getInstance().getNowLogin()) {
                    startActivity(new Intent(DetailActivity.this, LoginActivity.class));
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
        Adapter adapter = new Adapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentMenu(), "메뉴");
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

    public void btnOrder(View v) { // 번호표 뽑기 버튼 이벤트
        Singleton.getInstance().setBtnOrder(true);

        if (Singleton.getInstance().getNowLogin()) {
            if (Singleton.getInstance().getDuplicated() == false)
            {
                String uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/ticket")
                        .buildUpon()
                        .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowStore()))
                        .build().toString();

                TicketAsyncTask ticketAsyncTask = new TicketAsyncTask("번호표 보기");
                ticketAsyncTask.execute(uri);
            } else {
                Toast.makeText(getApplicationContext(), "이미 번호표를 발급 받았습니다", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "로그인이 필요한 서비스입니다", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DetailActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        }
    }

    public class TicketAsyncTask extends AsyncTask<String, Void, String> {
        String type;

        TicketAsyncTask(String type) {
            this.type = type;
        }

        @Override
        protected String doInBackground(String... urls) {
            //urls[0] 은 URL 주소
            return HttpTask.getInstance().GET(urls[0], type);
        }
        // onPostExecute displays the results of the AsyncTask.

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            if (Singleton.getInstance().getDuplicated() == false) {
                String uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/ticket/make")
                        .buildUpon()
                        .appendQueryParameter("number", (Singleton.getInstance().getLastClient() + 1) + "")
                        .appendQueryParameter("store", Singleton.getInstance().getNowStore())
                        .appendQueryParameter("place", Singleton.getInstance().getRegion())
                        .appendQueryParameter("phone", Singleton.getInstance().getNowLoginID())
                        .appendQueryParameter("source", Singleton.getInstance().getNowStoreImage())
                        .build().toString();

                HttpAsyncTask httpAsyncTask = new HttpAsyncTask("번호표 발급");
                httpAsyncTask.execute(uri);
            }else {
                Toast.makeText(getApplicationContext(), "이미 번호표를 발급 받았습니다", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class HttpAsyncTask extends AsyncTask<String, Void, String> {
        String type;

        HttpAsyncTask(String type) {
            this.type = type;
        }

        @Override
        protected String doInBackground(String... urls) {
            //urls[0] 은 URL 주소
            return HttpTask.getInstance().POST(urls[0], type);
        }
        // onPostExecute displays the results of the AsyncTask.

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            currentOrderNumber.setText((Singleton.getInstance().getLastClient() - Singleton.getInstance().getNowClient() + 1) + "");
            Toast.makeText(getApplicationContext(), "번호표 발급 완료", Toast.LENGTH_SHORT).show();
        }
    }
}