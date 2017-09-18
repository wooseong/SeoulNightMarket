package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.IntroductionAdapter;

public class FragmentIntroduction extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) { // Fragment가 생성될때 호출
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_introduction, null);

        IntroductionAdapter adapter = new IntroductionAdapter();
        ListView listView = view.findViewById(R.id.introductionListView);
        listView.setAdapter(adapter);

        View header = getLayoutInflater(savedInstanceState).inflate(R.layout.introductionheader, null, false); // 리스트뷰 헤더
        listView.addHeaderView(header);

        TextView textView0 = view.findViewById(R.id.introductionText0);
        TextView textView1 = view.findViewById(R.id.introductionText1);
        TextView textView2 = view.findViewById(R.id.introductionText2);
        TextView textView3 = view.findViewById(R.id.introductionText3);
        TextView textView4 = view.findViewById(R.id.introductionText4);
        ImageView imageView = view.findViewById(R.id.introductionHeaderImage);

        View footer = getLayoutInflater(savedInstanceState).inflate(R.layout.introductionfooter, null, false); // 리스트뷰 푸터
        listView.addFooterView(footer);

        ImageView imageView1 = view.findViewById(R.id.introductionFooterImage);

        adapter.addItem("인포센터 ", getString(R.string.introduction));
        adapter.addItem("푸드존 ", getString(R.string.introduction1));
        adapter.addItem("셀러존 ", getString(R.string.introduction2));

        return view;
    }
}