package seoulnightmarket.seoulnightmarket;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import seoulnightmarket.seoulnightmarket.adapter.MainAdapter;

public class InitialScreen extends AppCompatActivity {
    public int[] nightMarket = {R.drawable.home_banpo, R.drawable.home_cgc, R.drawable.home_cggj, R.drawable.home_ddp, R.drawable.home_yyd};
    public GridView gridView;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);

        mainAdapter = new MainAdapter(getApplicationContext(), nightMarket);
        gridView = (GridView) findViewById(R.id.gridViewInitial);
        gridView.setAdapter(mainAdapter);
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