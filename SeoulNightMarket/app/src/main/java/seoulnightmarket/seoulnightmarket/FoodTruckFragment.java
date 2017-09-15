package seoulnightmarket.seoulnightmarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FoodTruckFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_truck_fragment);
    }

    public void btnMenu(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFoodTruck, new FragmentMenu()).commit();
    }

    public void btnInformation(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFoodTruck, new FragmentInformation()).commit();
    }

    public void btnReview(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFoodTruck, new FragmentReview()).commit();
    }
}