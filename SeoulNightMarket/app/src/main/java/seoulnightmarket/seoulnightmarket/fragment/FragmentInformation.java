package seoulnightmarket.seoulnightmarket.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.InformationAdapter;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FragmentInformation extends Fragment {
    private ArrayList<String> regionList = new ArrayList<>();
    private String[] region = {"청계천", "여의도", "DDP", "반포", "여의도", "DDP", "반포", "청계천", "반포", "여의도"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        View view = inflater.inflate(R.layout.activity_fragment_information, null);

        InformationAdapter adapter = new InformationAdapter();
        ListView listView = view.findViewById(R.id.informationlistview);
        listView.setAdapter(adapter);

        adapter.addItem("1회차", "03.24-04.09", region[0]);
        adapter.addItem("2회차", "04.10-04.30", region[1]);
        adapter.addItem("3회차", "05.01-05.21", region[2]);
        adapter.addItem("4회차", "05.22-06.11", region[3]);
        adapter.addItem("5회차", "06.12-07.02", region[4]);
        adapter.addItem("6회차", "07.03-07.23", region[5]);
        adapter.addItem("7회차", "07.24-08.13", region[6]);
        adapter.addItem("8회차", "08.14-09.03", region[7]);
        adapter.addItem("9회차", "09.04-09.24", region[8]);
        adapter.addItem("10회차", "09.25-10.22", region[9]);

        MaterialCalendarView calendarView = view.findViewById(R.id.calendarView);

        calendarView.addDecorators(new FridayDecorator(), new SaturdayDecorator(), new OneDayDecorator());

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() { // 달력 클릭 이벤트
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(getApplicationContext(), String.valueOf(date.getMonth() + 1) + "월" + String.valueOf(date.getDay()) + "일", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public class FridayDecorator implements DayViewDecorator { // 금요일
        private final Calendar calendar = Calendar.getInstance();

        public FridayDecorator() {

        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.FRIDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.RED));
        }
    }

    public class SaturdayDecorator implements DayViewDecorator { // 토요일
        private final Calendar calendar = Calendar.getInstance();

        public SaturdayDecorator() {

        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SATURDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }
    }

    public class OneDayDecorator implements DayViewDecorator { // 오늘 날짜
        private CalendarDay date;

        public OneDayDecorator() {
            date = CalendarDay.today();
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return date != null && day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.4f));
            view.addSpan(new ForegroundColorSpan(Color.RED));
        }

        public void setDate(Date date) {
            this.date = CalendarDay.from(date);
        }
    }
}