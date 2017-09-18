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

        View header = getLayoutInflater(savedInstanceState).inflate(R.layout.introductionheader, null, false);
        listView.addHeaderView(header);

        adapter.addItem("인포센터 오늘 밤의 낭만이 시작되는 곳", "종합 안내소 겸 상황실입니다. 운영본부와 의료지원 부스가 마련되어 있으니\n 궁금한 점이 있거나 비상 및 응급 상황 발생시 인포센터를 찾아주세요");
        adapter.addItem("푸드존 한강에서 즐기는 로맨틱한 저녁 식사", "한강과 가장 가까운 특별한 식당\n 전 세계 음식을 맛볼 수 있는 푸드존에서 낭만적인 저녁 식사를 즐겨보세요");
        adapter.addItem("셀러존 나만을 위한 특별한 작품", "다양한 분야의 핸드메이드 작가들이 반짝이는 아이디어와 솜씨로 여러분을 위한\n 세상에 단 하나뿐인 작품을 준비했습니다");

        TextView textView0 = view.findViewById(R.id.introductionText0);
        TextView textView1 = view.findViewById(R.id.introductionText1);
        TextView textView2 = view.findViewById(R.id.introductionText2);
        TextView textView3 = view.findViewById(R.id.introductionText3);
        TextView textView4 = view.findViewById(R.id.introductionText4);
        ImageView imageView = view.findViewById(R.id.introductionImage);

        return view;
    }
}