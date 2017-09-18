package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 액션버튼 메뉴 액션바에 넣기
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchview_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { // 검색어 완료시
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) { // 검색어 입력할 때

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}