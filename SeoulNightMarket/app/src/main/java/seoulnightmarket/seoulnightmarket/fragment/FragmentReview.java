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
import seoulnightmarket.seoulnightmarket.adapter.ReviewAdapter;

public class FragmentReview extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_review, container, false);

        ReviewAdapter adapter = new ReviewAdapter();
        ListView listView = view.findViewById(R.id.reviewListView);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.hyowan), "내이름은 효스완스",
                ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.fivestar), "2017-09-16", "치킨이 정말 끝내줘요");
        adapter.addItem(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.taegyun), "감태균씨",
                ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.fivestar), "2017-09-17", "나는 엔샵 차기회장");

        return view;
    }
}