package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.ReviewAdapter;
import seoulnightmarket.seoulnightmarket.adapter.ReviewSpinnerAdapter;

public class FragmentReview extends Fragment {
    private String[] nickName = {"내이름은 효스완스", "감태균입니다"}; // 서버에서 닉네임과 리뷰 받아옴
    private String[] review = {"치킨이 정말 끝내줘요", "나는 엔샵 귀염둥이"};
    private String today; // 리뷰 쓴 날짜
    private int[] flags = {R.drawable.onestar, R.drawable.twostar, R.drawable.threestar, R.drawable.fourstar, R.drawable.fivestar};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA); // 오늘 날짜
        today = simpleDateFormat.format(new Date());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_review, container, false);

        ReviewAdapter adapter = new ReviewAdapter();
        ListView listView = view.findViewById(R.id.reviewListView);
        listView.setAdapter(adapter);

        for (int i = 0; i < nickName.length; i++) {
            adapter.addItem(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.user), nickName[i],
                    ContextCompat.getDrawable(getActivity().getApplicationContext(), flags[i]), today, review[i]);
        }

        final EditText editText = view.findViewById(R.id.editText);
        final Spinner spinnerStar = view.findViewById(R.id.spinnerStar);

        ReviewSpinnerAdapter reviewSpinnerAdapter = new ReviewSpinnerAdapter(getActivity(), flags);
        spinnerStar.setAdapter(reviewSpinnerAdapter);

        spinnerStar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                spinnerStar.setBackground(null);
//                Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnSubmit = view.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() { // 리뷰 남기기 버튼
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), editText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}