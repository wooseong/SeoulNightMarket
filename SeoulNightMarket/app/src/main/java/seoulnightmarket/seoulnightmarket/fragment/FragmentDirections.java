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

public class FragmentDirections extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_directions, null);

        TextView textDirection0 = view.findViewById(R.id.textDirection0);
        TextView textDirection1 = view.findViewById(R.id.textDirection1);
        TextView textAddress = view.findViewById(R.id.textAddress);
        TextView textBus = view.findViewById(R.id.textBus);
        TextView textSubway = view.findViewById(R.id.textSubway);
        TextView textCar = view.findViewById(R.id.textCar);
        ImageView imageView = view.findViewById(R.id.imageViewDirection);

        return view;
    }
}