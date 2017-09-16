package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.ConcertAdapter;

public class FragmentConcert extends Fragment {
    public int[] musicianImage = {R.drawable.ddalgi, R.drawable.gonayoung, R.drawable.kichin};
    public String[] musicianName = {"딸기주스가너무달아", "고나영", "마멀레이드키친"};
    public ConcertAdapter concertAdapter;
    public GridView gridView;
    public TextView textView;
    public Spinner spinnerMonth;
    public Spinner spinnerDay;
    public ArrayList<String> monthList = new ArrayList<>();
    public ArrayList<String> dayList = new ArrayList<>();
    String[] month = { "3월", "4월", "5월", "6월", "7월", "8월", "9월"};
    String[] day = { "1일", "10일", "15일", "16일", "24일", "25일", "30일"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_concert, container, false);

        concertAdapter = new ConcertAdapter(view.getContext(), musicianImage, musicianName);

        textView = view.findViewById(R.id.concertDate);
        gridView = view.findViewById(R.id.gridViewConcert);
        gridView.setAdapter(concertAdapter);

        spinnerMonth = view.findViewById(R.id.spinnerMonth);
        spinnerDay = view.findViewById(R.id.spinnerDay);

        initSpinner(spinnerMonth, month); // month
        initSpinner(spinnerDay, day); // day

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) { // 목록을 선택 했을 때

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                textView.setText(day[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public void initSpinner(Spinner spinner, String[] date) { // 스피너 초기화 함수 // 나중에 서버에서 데이터 긁어 오면 됨
        ArrayAdapter<String> adapter; // 스피너에 뿌려질 Array형식의 Data를 담을 Adapter

        if (date == month) {
            for (int i = 0; i < date.length; i++) { // 어레이 리스트에 저장
                monthList.add(date[i]);
            }
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, monthList);
        }
        else {
            for (int i = 0; i < date.length; i++) { // 어레이 리스트에 저장
                dayList.add(date[i]);
            }
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dayList);
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}