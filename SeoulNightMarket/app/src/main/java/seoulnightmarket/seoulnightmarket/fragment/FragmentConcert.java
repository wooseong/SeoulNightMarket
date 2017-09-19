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
    public ArrayList<String> monthList;
    public ArrayList<String> dayList;
    String[] month = {"3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월"};
    String[] dayMarch = {"24일", "25일", "31일", "1일"};
    String[] dayApril = {"7일", "8일", "14일", "15일", "21일", "22일", "28일", "29일"};
    String[] dayMay = {"5일", "6일", "12일", "13일", "19일", "20일", "26일", "27일"};
    String[] dayJune = {"2일", "3일", "9일", "10일", "16일", "17일", "23일", "24일", "30일", "1일"};
    String[] dayJuly = {"7일", "8일", "14일", "15일", "21일", "22일", "28일", "29일"};
    String[] dayAugust = {"4일", "5일", "11일", "12일", "18일", "19일", "25일", "26일"};
    String[] daySeptember = {"1일", "2일", "8일", "9일", "15일", "16일", "22일", "23일", "29일", "30일"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        monthList = new ArrayList<>();
        dayList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_concert, container, false);

        concertAdapter = new ConcertAdapter(view.getContext(), musicianImage, musicianName); // 그리드뷰 어댑터 연결

        textView = view.findViewById(R.id.concertDate);
        gridView = view.findViewById(R.id.gridViewConcert);
        gridView.setAdapter(concertAdapter);

        spinnerMonth = view.findViewById(R.id.spinnerMonth);
        spinnerDay = view.findViewById(R.id.spinnerDay);

        initSpinner(spinnerMonth, month); // month
        initSpinner(spinnerDay, dayAugust); // day

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // month 스피너 리스너
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) { // month를 선택하면 day가 바뀜
                switch (position) {
                    case 0:
                        initSpinner(spinnerDay, dayMarch);
                        break;
                    case 1:
                        initSpinner(spinnerDay, dayApril);
                        break;
                    case 2:
                        initSpinner(spinnerDay, dayMay);
                        break;
                    case 3:
                        initSpinner(spinnerDay, dayJune);
                        break;
                    case 4:
                        initSpinner(spinnerDay, dayJuly);
                        break;
                    case 5:
                        initSpinner(spinnerDay, dayAugust);
                        break;
                    case 6:
                        initSpinner(spinnerDay, daySeptember);
                        break;
                    case 7: // 10월

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { // 아무것도 선택 안했을 때

            }
        });

        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // day 스피너 리스너
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                textView.setText(dayList.get(position));
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
            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, monthList); // 어댑터 생성
        }
        else {
            dayList.clear();

            for (int i = 0; i < date.length; i++) { // 어레이 리스트에 저장
                dayList.add(date[i]);
            }
            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, dayList);
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}