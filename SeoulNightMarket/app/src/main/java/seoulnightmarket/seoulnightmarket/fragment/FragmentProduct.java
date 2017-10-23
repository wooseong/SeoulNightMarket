package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.ProductAdapter;

public class FragmentProduct extends Fragment {
    private String[] productName = {"실버 반지", "실버 팔찌", "실버 발찌", "실버 귀걸이", "실버 목걸이"};
    private String[] productPrice = {"5000-25000", "10000-50000", "15000-20000", "7000-30000", "25000-35000"};
    private ArrayList<String> productNameList;
    private ArrayList<String> productPriceList; // 서버에서 받을 데이터 리스트

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productNameList = new ArrayList<>();
        productPriceList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_product, null);

        ProductAdapter adapter = new ProductAdapter();
        ListView listView = view.findViewById(R.id.listViewProduct);
        listView.setAdapter(adapter);

        for (int i = 0; i < productName.length; i++) {
            adapter.addItem(productName[i], productPrice[i]); // 서버에서 받은 만큼 데이터를 어댑터에 연결한다
        }

        return view;
    }
}