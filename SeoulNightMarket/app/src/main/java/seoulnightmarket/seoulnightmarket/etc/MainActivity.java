package seoulnightmarket.seoulnightmarket.etc;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.MainAdapter;

public class MainActivity extends AppCompatActivity {
    public int[] nightMarket = {R.drawable.home_banpo, R.drawable.home_cgc, R.drawable.home_cggj, R.drawable.home_ddp, R.drawable.home_yyd};
    public GridView gridView;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(3000);
            setContentView(R.layout.activity_main);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageViewInitial);
        AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground(); // 이미지뷰의 배경으로 애니메이션 객체 얻음
        drawable.start(); // 애니메이션 시작

        mainAdapter = new MainAdapter(getApplicationContext(), nightMarket); // 그리드뷰에 어댑터 연결
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