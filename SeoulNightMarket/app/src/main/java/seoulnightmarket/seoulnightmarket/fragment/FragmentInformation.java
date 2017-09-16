package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.InformationAdapter;

public class FragmentInformation extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_information, null);

        InformationAdapter adapter = new InformationAdapter();
        ListView listView = view.findViewById(R.id.informationlistview);
        listView.setAdapter(adapter);

        adapter.addItem("1회차", "03.24-04.09", "여의도");
        adapter.addItem("2회차", "04.10-04.30", "청계천");
        adapter.addItem("3회차", "05.01-05.21", "반포");

        return view;
    }
}