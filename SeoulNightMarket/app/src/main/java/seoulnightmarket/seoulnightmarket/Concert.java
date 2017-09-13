package seoulnightmarket.seoulnightmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Concert extends AppCompatActivity {
    public int[] musicianImage = {R.drawable.ddalgi, R.drawable.gonayoung, R.drawable.kichin};
    public String[] musicianName = {"딸기주스가너무달아", "고나영", "마멀레이드키친"};
    public ConcertAdapter concertAdapter;
    public GridView gridView;
    private TextView textView;
    private Spinner spinnerMonth;
    private Spinner spinnerDay;
    private ArrayList<String> monthList = new ArrayList<>();
    private ArrayList<String> dayList = new ArrayList<>();
    String[] month = { "3월", "4월", "5월", "6월", "7월", "8월", "9월"};
    String[] day = { "1일", "10일", "15일", "16일", "24일", "25일", "30일"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert);

        concertAdapter = new ConcertAdapter(getApplicationContext(), musicianImage, musicianName);

        textView = (TextView) findViewById(R.id.concertDate);
        gridView = (GridView) findViewById(R.id.gridViewConcert);
        gridView.setAdapter(concertAdapter);

        spinnerMonth = (Spinner) findViewById(R.id.spinnerMonth);
        spinnerDay = (Spinner) findViewById(R.id.spinnerDay);

        initSpinner(spinnerMonth, month); // month
        initSpinner(spinnerDay, day); // day

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) { // 목록을 선택 했을 때
                Toast.makeText(getApplicationContext(), month[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), day[position], Toast.LENGTH_SHORT).show();
                textView.setText(day[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void initSpinner(Spinner spinner, String[] date) { // 스피너 초기화 함수 // 나중에 서버에서 데이터 긁어 오면 됨
        ArrayAdapter<String> adapter; // 스피너에 뿌려질 Array형식의 Data를 담을 Adapter

        if (date == month) {
            for (int i = 0; i < date.length; i++) { // 어레이 리스트에 저장
                monthList.add(date[i]);
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthList);
        }
        else {
            for (int i = 0; i < date.length; i++) { // 어레이 리스트에 저장
                dayList.add(date[i]);
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dayList);
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}