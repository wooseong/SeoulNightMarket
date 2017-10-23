package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.MenuAdapter;

public class FragmentMenu extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_menu, null);

        MenuAdapter adapter = new MenuAdapter();
        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.chickenfit), "치킨핏", 12000);
        adapter.addItem(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.pandagrill), "팬더그릴", 25000);
        adapter.addItem(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.imsteak), "아임스테이크", 16000);

        return view;
    }
}