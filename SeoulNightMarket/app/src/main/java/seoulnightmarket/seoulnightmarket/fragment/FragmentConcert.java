package seoulnightmarket.seoulnightmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.ConcertAdapter;
import seoulnightmarket.seoulnightmarket.adapter.ConcertSpinnerAdapter;

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
    String[] dayMarch = {"24일(금)", "25일(토)", "31일(금)", "1일(토)"};
    String[] dayApril = {"7일(금)", "8일(토)", "14일(금)", "15일(토)", "21일(금)", "22일(토)", "28일(금)", "29일(토)"};
    String[] dayMay = {"5일(금)", "6일(토)", "12일(금)", "13일(토)", "19일(금)", "20일(토)", "26일(금)", "27일(토)"};
    String[] dayJune = {"2일(금)", "3일(토)", "9일(금)", "10일(토)", "16일(금)", "17일(토)", "23일(금)", "24일(토)", "30일(금)", "1일(토)"};
    String[] dayJuly = {"7일(금)", "8일(토)", "14일(금)", "15일(토)", "21일(금)", "22일(토)", "28일(금)", "29일(토)"};
    String[] dayAugust = {"4일(금)", "5일(토)", "11일(금)", "12일(토)", "18일(금)", "19일(토)", "25일(금)", "26일(토)"};
    String[] daySeptember = {"1일(금)", "2일(토)", "8일(금)", "9일(토)", "15일(금)", "16일(토)", "22일(금)", "23일(토)", "29일(금)", "30일(토)"};
    String[] dayOctober = {"13일(금)", "14일(토)", "20일(금)", "21일(토)", "27일(금)", "28일(토)"};

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
                        initSpinner(spinnerDay, dayOctober);
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
//        ArrayAdapter<String> adapter; // 스피너에 뿌려질 Array형식의 Data를 담을 Adapter
        ConcertSpinnerAdapter concertSpinnerAdapter;

        if (date == month) {
            for (int i = 0; i < date.length; i++) { // 어레이 리스트에 저장
                monthList.add(date[i]);
            }
//            adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item_custom, monthList); // 어댑터 생성
            concertSpinnerAdapter = new ConcertSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, month);
        } else {
            dayList.clear();

            for (int i = 0; i < date.length; i++) { // 어레이 리스트에 저장
                dayList.add(date[i]);
            }
//            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, dayList);
            concertSpinnerAdapter = new ConcertSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, date);
        }

        concertSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
        spinner.setAdapter(concertSpinnerAdapter);
    }
}