package seoulnightmarket.seoulnightmarket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentMenu extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_menu, container, false);

        ImageView menuImage0 = view.findViewById(R.id.menuImage0);
        ImageView menuImage1 = view.findViewById(R.id.menuImage1);
        ImageView menuImage2 = view.findViewById(R.id.menuImage2);

        TextView menuText0 = view.findViewById(R.id.menuText0);
        TextView menuText1 = view.findViewById(R.id.menuText1);
        TextView menuText2 = view.findViewById(R.id.menuText2);

        TextView menuPrice0 = view.findViewById(R.id.menuPrice0);
        TextView menuPrice1 = view.findViewById(R.id.menuPrice1);
        TextView menuPrice2 = view.findViewById(R.id.menuPrice2);

        menuImage0.setImageResource(R.drawable.pandagrill);
        menuImage1.setImageResource(R.drawable.chickenfit);
        menuImage2.setImageResource(R.drawable.herotruck);

        return view;
    }
}