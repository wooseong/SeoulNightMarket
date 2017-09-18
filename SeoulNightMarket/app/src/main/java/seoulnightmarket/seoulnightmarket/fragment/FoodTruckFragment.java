package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class FoodTruckFragment extends AppCompatActivity implements View.OnClickListener {
    public String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_truck_fragment);

        findViewById(R.id.btnMenu).setOnClickListener(this);
        findViewById(R.id.btnInformation).setOnClickListener(this);
        findViewById(R.id.btnReview).setOnClickListener(this);

        type = Singleton.getInstance().getType();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnMenu:
                if (type.equals("foodTruck")) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFoodTruck, new FragmentMenu()).commit();
                }

                else if (type.equals("handMade")) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFoodTruck, new FragmentProduct()).commit();
                }
                break;
            case R.id.btnInformation:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFoodTruck, new FragmentInformation()).commit();
                break;
            case R.id.btnReview:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFoodTruck, new FragmentReview()).commit();
                break;
        }
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