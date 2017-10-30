package seoulnightmarket.seoulnightmarket.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.IntroductionAdapter;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class FragmentIntroduction extends Fragment
{
    String region;
    String uri;
    View view;
    IntroductionAdapter adapter;
    TextView textView0;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageColorView;
    ImageView imageColorView1;
    ImageView imageColorView2;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> adapterHead = new ArrayList<String>();
    ArrayList<String> adapterTitle = new ArrayList<String>();
    ArrayList<String> adapterString = new ArrayList<String>();

    public FragmentIntroduction() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { // Fragment가 생성될때 호출
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    { // onCreate 후에 화면을 구성할때 호출
        view = inflater.inflate(R.layout.activity_fragment_introduction, null);
        region = Singleton.getInstance().getRegion();

        final FragmentActivity fragment = getActivity();

        recyclerView = (RecyclerView) view.findViewById(R.id.intro_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(fragment);
        recyclerView.setLayoutManager(layoutManager);

        textView0 = view.findViewById(R.id.introductionText0);
        textView1 = view.findViewById(R.id.introductionText1);
        textView2 = view.findViewById(R.id.introductionText2);
        textView3 = view.findViewById(R.id.introductionText3);
        textView4 = view.findViewById(R.id.introductionText4);
        textView5 = view.findViewById(R.id.introductionTimeText0);
        textView6 = view.findViewById(R.id.introductionTimeText1);
        imageView = view.findViewById(R.id.introductionHeaderImage);
        imageView1 = view.findViewById(R.id.introductionFooterImage);
        imageColorView = view.findViewById(R.id.indexLineColor0);
        imageColorView1 = view.findViewById(R.id.indexLineColor1);
        imageColorView2 = view.findViewById(R.id.indexLineColor2);

        adapter = new IntroductionAdapter();
        adapterHead.clear();
        adapterTitle.clear();
        adapterString.clear();

        if (region.equals("여의도"))
        {
            textView0.setText(R.string.textView0y);
            textView1.setText(R.string.textView1y);
            textView2.setText(R.string.textView2y);
            textView3.setText(R.string.textView3y);
            textView4.setText(R.string.textView4y);
            textView5.setText(R.string.timeView0y);
            textView6.setText(R.string.timeView1y);
            imageView.setImageResource(R.drawable.yydintro);
            imageView1.setImageResource(R.drawable.yydintro1);
            imageColorView.setBackgroundColor(Color.parseColor("#FFA726"));
            imageColorView1.setBackgroundColor(Color.parseColor("#FFA726"));
            imageColorView2.setBackgroundColor(Color.parseColor("#FFA726"));

            adapterHead.add(getString(R.string.listView0y));
            adapterHead.add(getString(R.string.listView3y));
            adapterHead.add(getString(R.string.listView6y));
            adapterHead.add(getString(R.string.listView9y));
            adapterHead.add(getString(R.string.listView12y));

            adapterTitle.add(getString(R.string.listView1y));
            adapterTitle.add(getString(R.string.listView4y));
            adapterTitle.add(getString(R.string.listView7y));
            adapterTitle.add(getString(R.string.listView10y));
            adapterTitle.add(getString(R.string.listView13y));

            adapterString.add(getString(R.string.listView2y));
            adapterString.add(getString(R.string.listView5y));
            adapterString.add(getString(R.string.listView8y));
            adapterString.add(getString(R.string.listView11y));
            adapterString.add(getString(R.string.listView14y));
        }
        else if (region.equals("DDP"))
        {
            textView0.setText(R.string.textView0d);
            textView1.setText(R.string.textView1d);
            textView2.setText(R.string.textView2d);
            textView3.setText(R.string.textView3d);
            textView4.setText(R.string.textView4d);
            textView5.setText(R.string.timeView0d);
            textView6.setText(R.string.timeView1d);
            imageView.setImageResource(R.drawable.ddpintro);
            imageView1.setImageResource(R.drawable.ddpintro1);
            imageColorView.setBackgroundColor(Color.parseColor("#7E57C2"));
            imageColorView1.setBackgroundColor(Color.parseColor("#7E57C2"));
            imageColorView2.setBackgroundColor(Color.parseColor("#7E57C2"));

            adapterHead.add(getString(R.string.listView0d));
            adapterHead.add(getString(R.string.listView3d));
            adapterHead.add(getString(R.string.listView6d));
            adapterHead.add(getString(R.string.listView9d));
            adapterHead.add(getString(R.string.listView12d));
            adapterHead.add(getString(R.string.listView15d));

            adapterTitle.add(getString(R.string.listView1d));
            adapterTitle.add(getString(R.string.listView4d));
            adapterTitle.add(getString(R.string.listView7d));
            adapterTitle.add(getString(R.string.listView10d));
            adapterTitle.add(getString(R.string.listView13d));
            adapterTitle.add(getString(R.string.listView16d));

            adapterString.add(getString(R.string.listView2d));
            adapterString.add(getString(R.string.listView5d));
            adapterString.add(getString(R.string.listView8d));
            adapterString.add(getString(R.string.listView11d));
            adapterString.add(getString(R.string.listView14d));
            adapterString.add(getString(R.string.listView17d));

        } else if (region.equals("반포")) {
            textView0.setText(R.string.textView0b);
            textView1.setText(R.string.textView1b);
            textView2.setText(R.string.textView2b);
            textView3.setText(R.string.textView3b);
            textView4.setText(R.string.textView4b);
            textView5.setText(R.string.timeView0b);
            textView6.setText(R.string.timeView1b);
            imageView.setImageResource(R.drawable.bpintro);
            imageView1.setImageResource(R.drawable.bpintro1);
            imageColorView.setBackgroundColor(Color.parseColor("#FDD835"));
            imageColorView1.setBackgroundColor(Color.parseColor("#FDD835"));
            imageColorView2.setBackgroundColor(Color.parseColor("#FDD835"));

            adapterHead.add(getString(R.string.listView0b));
            adapterHead.add(getString(R.string.listView3b));
            adapterHead.add(getString(R.string.listView6b));
            adapterHead.add(getString(R.string.listView9b));

            adapterTitle.add(getString(R.string.listView1b));
            adapterTitle.add(getString(R.string.listView4b));
            adapterTitle.add(getString(R.string.listView7b));
            adapterTitle.add(getString(R.string.listView10b));

            adapterString.add(getString(R.string.listView2b));
            adapterString.add(getString(R.string.listView5b));
            adapterString.add(getString(R.string.listView8b));
            adapterString.add(getString(R.string.listView11b));
        } else if (region.equals("청계천")) {
            textView0.setText(R.string.textView0c);
            textView1.setText(R.string.textView1c);
            textView2.setText(R.string.textView2c);
            textView3.setText(R.string.textView3c);
            textView4.setText(R.string.textView4c);
            textView5.setText(R.string.timeView0c);
            textView6.setText(R.string.timeView1c);
            imageView.setImageResource(R.drawable.cgcintro);
            imageView1.setImageResource(R.drawable.cgcintro1);
            imageColorView.setBackgroundColor(Color.parseColor("#CDDC39"));
            imageColorView1.setBackgroundColor(Color.parseColor("#CDDC39"));
            imageColorView2.setBackgroundColor(Color.parseColor("#CDDC39"));

            adapterHead.add(getString(R.string.listView0c));
            adapterHead.add(getString(R.string.listView3c));
            adapterHead.add(getString(R.string.listView6c));
            adapterHead.add(getString(R.string.listView9c));
            adapterHead.add(getString(R.string.listView12c));

            adapterTitle.add(getString(R.string.listView1c));
            adapterTitle.add(getString(R.string.listView4c));
            adapterTitle.add(getString(R.string.listView7c));
            adapterTitle.add(getString(R.string.listView10c));
            adapterTitle.add(getString(R.string.listView13c));

            adapterString.add(getString(R.string.listView2c));
            adapterString.add(getString(R.string.listView5c));
            adapterString.add(getString(R.string.listView8c));
            adapterString.add(getString(R.string.listView11c));
            adapterString.add(getString(R.string.listView14c));

        } else if (region.equals("청계광장")) {
            textView0.setText(R.string.textView0j);
            textView1.setText(R.string.textView1j);
            textView2.setText(R.string.textView2j);
            textView3.setText(R.string.textView3j);
            textView4.setText(R.string.textView4j);
            textView5.setText(R.string.timeView0j);
            textView6.setText(R.string.timeView1j);
            imageView.setImageResource(R.drawable.cggjintro);
            imageView1.setImageResource(R.drawable.cggjintro1);
            imageColorView.setBackgroundColor(Color.parseColor("#00838F"));
            imageColorView1.setBackgroundColor(Color.parseColor("#00838F"));
            imageColorView2.setBackgroundColor(Color.parseColor("#00838F"));

            adapterHead.add(getString(R.string.listView0j));
            adapterHead.add(getString(R.string.listView3j));
            adapterHead.add(getString(R.string.listView6j));
            adapterHead.add(getString(R.string.listView9j));
            adapterHead.add(getString(R.string.listView12j));

            adapterTitle.add(getString(R.string.listView1j));
            adapterTitle.add(getString(R.string.listView4j));
            adapterTitle.add(getString(R.string.listView7j));
            adapterTitle.add(getString(R.string.listView10j));
            adapterTitle.add(getString(R.string.listView13j));

            adapterString.add(getString(R.string.listView2j));
            adapterString.add(getString(R.string.listView5j));
            adapterString.add(getString(R.string.listView8j));
            adapterString.add(getString(R.string.listView11j));
            adapterString.add(getString(R.string.listView14j));
        }

        for (int i = 0; i < adapterHead.size(); i++) {
            adapter.addItem(adapterHead.get(i), adapterTitle.get(i), adapterString.get(i));
        }

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.invalidate();

        return view;
    }
}