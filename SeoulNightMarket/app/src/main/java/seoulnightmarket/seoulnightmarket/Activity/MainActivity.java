package seoulnightmarket.seoulnightmarket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.MainAdapter;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class MainActivity extends AppCompatActivity {
    public int[] nightMarket = {R.drawable.order_main, R.drawable.yyd_main, R.drawable.ddp_main, R.drawable.banpo_main, R.drawable.cgc_main, R.drawable.cggj_main};
    public GridView gridView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.mainDrawer);

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

                startActivity(new Intent(MainActivity.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(MainActivity.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(MainActivity.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(MainActivity.this, AreaInformationWithTabBar.class));
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

                startActivity(new Intent(MainActivity.this, AreaInformationWithTabBar.class));
                finish();

                mDrawerLayout.closeDrawers();
            }
        });

        Button btnMyReserve = (Button) findViewById(R.id.btnMyReserve);
        btnMyReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Singleton.getInstance().getNowLogin())
                {
                    if(Singleton.getInstance().getNowLoginID().contains("Admin"))
                    {
                        startActivity(new Intent(MainActivity.this, SellerActivity.class));
                    }
                    else {
                        startActivity(new Intent(MainActivity.this, NumberTicketActivity.class));
                    }
                } else if (!Singleton.getInstance().getNowLogin()) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }

                finish();
                mDrawerLayout.closeDrawers();
            }
        });

        MainAdapter mainAdapter = new MainAdapter(getApplicationContext(), nightMarket); // 그리드뷰에 어댑터 연결
        gridView = (GridView) findViewById(R.id.gridViewInitial);
        gridView.setAdapter(mainAdapter);
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