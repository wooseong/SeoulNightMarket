package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.Singleton;

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
}