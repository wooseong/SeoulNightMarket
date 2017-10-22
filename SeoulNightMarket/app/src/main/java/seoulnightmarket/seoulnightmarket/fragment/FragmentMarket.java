package seoulnightmarket.seoulnightmarket.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.MarketAdapter;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class FragmentMarket extends Fragment {
    public Integer[] foodTruck = {R.drawable.herotruck, R.drawable.shimlimphinhiya, R.drawable.chickenfit, R.drawable.imsteak, R.drawable.jayfresh, R.drawable.pandagrill, R.drawable.girl};
    public Integer[] handMade = {R.drawable.andro, R.drawable.babo, R.drawable.bom, R.drawable.soso};
    private String type = "foodTruck";
    private String region = "";
    private MarketAdapter adapter;
    public String[] foodTruckName = {"hero truck", "쉬림프컵히야", "치킨핏", "아임 스테이크", "제이프레시", "팬더그릴", "보이"};
    public String[] handMadeName = {"안드로행성712공방", "바보공방", "봄이네", "소소공방"};
//    public ArrayList<String> foodTruckName = new ArrayList<String>();
//    public ArrayList<String> handMadeName = new ArrayList<String>(); // 서버에서 데이터 받아올때 어레이리스트 쓸 것

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_market, container, false);
        final GridView gridView = view.findViewById(R.id.gridView);

        adapter = new MarketAdapter(getActivity().getApplicationContext(), foodTruck, foodTruckName); // 디폴트 푸드트럭
        gridView.invalidateViews();
        gridView.setAdapter(adapter); // 그리드뷰에 어댑터 연결

        Singleton.getInstance().setType(type);

        final Button btnFoodTruck = view.findViewById(R.id.foodTruck);
        final Button btnHandMade = view.findViewById(R.id.handMade);

        region = Singleton.getInstance().getRegion(); // 지역에 따라 바뀜
        switch (region) {
            case "Yeouido":
                btnFoodTruck.setTextColor(Color.parseColor("#ffffff"));
                btnFoodTruck.setBackgroundResource(R.color.md_deep_orange_400);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            case "DDP":
                btnFoodTruck.setTextColor(Color.parseColor("#FFEB3B"));
                btnFoodTruck.setBackgroundResource(R.color.md_deep_purple_400);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            case "Banpo":
                btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                btnFoodTruck.setBackgroundResource(R.color.md_yellow_500);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            case "Cheonggyecheon":
                btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                btnFoodTruck.setBackgroundResource(R.color.md_light_green_500);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            case "CheonggyePlaza":
                btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                btnFoodTruck.setBackgroundResource(R.color.md_blue_400);
                btnHandMade.setTextColor(Color.parseColor("#000000"));
                btnHandMade.setBackgroundResource(R.color.md_white_1000);
                break;
            default:
                break;
        }

        btnFoodTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "foodTruck";
                Singleton.getInstance().setType(type);

                if (type == "foodTruck") {
                    switch (region) {
                        case "Yeouido":
                            btnFoodTruck.setTextColor(Color.parseColor("#ffffff"));
                            btnFoodTruck.setBackgroundResource(R.color.md_deep_orange_400);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        case "DDP":
                            btnFoodTruck.setTextColor(Color.parseColor("#FFEB3B"));
                            btnFoodTruck.setBackgroundResource(R.color.md_deep_purple_400);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        case "Banpo":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_yellow_500);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        case "Cheonggyecheon":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_light_green_500);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        case "CheonggyePlaza":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_blue_400);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_white_1000);
                            break;
                        default:
                            break;
                    }

                    adapter = new MarketAdapter(getActivity().getApplicationContext(), foodTruck, foodTruckName);
                    gridView.invalidateViews();
                    gridView.setAdapter(adapter);
                }
            }
        });

        btnHandMade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "handMade";
                Singleton.getInstance().setType(type);

                if (type == "handMade") {
                    switch (region) {
                        case "Yeouido":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#ffffff"));
                            btnHandMade.setBackgroundResource(R.color.md_deep_orange_400);
                            break;
                        case "DDP":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#FFEB3B"));
                            btnHandMade.setBackgroundResource(R.color.md_deep_purple_400);
                            break;
                        case "Banpo":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_yellow_500);
                            break;
                        case "Cheonggyecheon":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_light_green_500);
                            break;
                        case "CheonggyePlaza":
                            btnFoodTruck.setTextColor(Color.parseColor("#000000"));
                            btnFoodTruck.setBackgroundResource(R.color.md_white_1000);
                            btnHandMade.setTextColor(Color.parseColor("#000000"));
                            btnHandMade.setBackgroundResource(R.color.md_blue_400);
                            break;
                        default:
                            break;
                    }

                    adapter = new MarketAdapter(getActivity().getApplicationContext(), handMade, handMadeName);
                    gridView.invalidateViews();
                    gridView.setAdapter(adapter);
                }
            }
        });

        return view;
    }
}