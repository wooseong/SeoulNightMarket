package seoulnightmarket.seoulnightmarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

public class NightMarket extends AppCompatActivity {
    public int foodTruck[] = {R.drawable.herotruck, R.drawable.shimlimphinhiya, R.drawable.chickenfit, R.drawable.imsteak, R.drawable.jayfresh, R.drawable.pandagrill};
    public int handMade[] = {R.drawable.andro, R.drawable.babo, R.drawable.bom, R.drawable.soso};
    public String type = "foodTruck";
    public CustomAdapter adapter;
    public GridView gridView;
    public String foodTruckName[] = {"hero truck", "쉬림프컵히야", "치킨핏", "아임 스테이크", "제이프레시", "팬더그릴"};
    public String handMadeName[] = {"안드로행성712공방", "바보공방", "봄이네", "소소공방"};
//    public ArrayList<String> foodTruckName = new ArrayList<String>();
//    public ArrayList<String> handMadeName = new ArrayList<String>(); // 서버에서 데이터 받아올때 어레이리스트 쓸 것

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_market);

        if (type == "foodTruck") {
            adapter = new CustomAdapter(getApplicationContext(), foodTruck, foodTruckName);
        } else if (type == "handMade") {
            adapter = new CustomAdapter(getApplicationContext(), handMade, handMadeName);
        }

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter); // 그리드뷰에 어댑터 연결
    }

    public void foodTruck(View v) { // 푸드트럭 버튼
        type = "foodTruck";
        Toast.makeText(getApplicationContext(), "푸드트럭", Toast.LENGTH_LONG).show();

        if (type == "foodTruck") {
            adapter = new CustomAdapter(getApplicationContext(), foodTruck, foodTruckName);
            gridView.invalidateViews();
            gridView.setAdapter(adapter);
        }
    }

    public void handMade(View v) { // 핸드메이드 버튼
        type = "handMade";
        Toast.makeText(getApplicationContext(), "핸드메이드", Toast.LENGTH_LONG).show();

        if (type == "handMade") {
            adapter = new CustomAdapter(getApplicationContext(), handMade, handMadeName);
            gridView.invalidateViews();
            gridView.setAdapter(adapter);
        }
    }
}