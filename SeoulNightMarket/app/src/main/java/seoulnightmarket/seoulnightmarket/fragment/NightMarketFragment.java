package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import seoulnightmarket.seoulnightmarket.R;

public class NightMarketFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_market_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager(); // Fragment 관리
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); // Fragment 작업 수행
    }

    public void btnIntroduction(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMarket, new FragmentIntroduction()).commit();
    }

    public void btnMarket(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMarket, new FragmentMarket()).commit();
    }

    public void btnConcert(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMarket, new FragmentConcert()).commit();
    }

    public void btnRoad(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMarket, new FragmentDirections()).commit();
    }
}