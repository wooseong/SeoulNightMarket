package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import seoulnightmarket.seoulnightmarket.R;

public class FragmentIntroduction extends Fragment {
    public TextView textView0;
    public TextView textView1;
    public TextView textView2;
    public ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) { // Fragment가 생성될때 호출
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_introduction, container, false);

        textView0 = view.findViewById(R.id.introductionText0);
        textView1 = view.findViewById(R.id.introductionText1);
        textView2 = view.findViewById(R.id.introductionText2);
        imageView = view.findViewById(R.id.introductionImage);

        imageView.setImageResource(R.drawable.banpo);

        return view;
    }
}