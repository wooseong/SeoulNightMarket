package seoulnightmarket.seoulnightmarket.fragment;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
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
import seoulnightmarket.seoulnightmarket.adapter.ConcertAdapter;

public class FragmentConcert extends Fragment {
    public int[] musicianImage = {R.drawable.ddalgi, R.drawable.gonayoung, R.drawable.kichin};
    public String[] musicianName = {"딸기주스가너무달아", "고나영", "마멀레이드키친"};
    public ConcertAdapter concertAdapter;
    public GridView gridView;
    public TextView textView;
    public ArrayList<String> monthList;
    public ArrayList<String> dayList;
    private String selectedDate; // 텍스트 바꿀 날짜형식 10월 31일 (수)
    private String serverDate; // 서버에 쓸 날짜형식 09.17

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        monthList = new ArrayList<>();
        dayList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_concert, container, false);

        concertAdapter = new ConcertAdapter(view.getContext(), musicianImage, musicianName); // 그리드뷰 어댑터 연결
        textView = view.findViewById(R.id.concertDate);
        gridView = view.findViewById(R.id.gridViewConcert);
        gridView.setAdapter(concertAdapter);

        Button btnDate = view.findViewById(R.id.btnConcert);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.activity_date_dialog);

                final MaterialCalendarView calendarView = dialog.findViewById(R.id.calendarView);
                calendarView.addDecorators(new HolidayDecorator(), new FridayDecorator(), new SaturdayDecorator(), new OneDayDecorator());

                calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                        String day = String.valueOf(date.getDate()).substring(0, 3);

                        switch (day) {
                            case "Mon":
                                day = "월";
                                break;
                            case "Tue":
                                day = "화";
                                break;
                            case "Wed":
                                day = "수";
                                break;
                            case "Thu":
                                day = "목";
                                break;
                            case "Fri":
                                day = "금";
                                break;
                            case "Sat":
                                day = "토";
                                break;
                            case "Sun":
                                day = "일";
                                break;
                            default:
                                break;
                        }

                        if (day != "금" && day != "토") {
                            Toast.makeText(getActivity(), "야시장 개장날이 아닙니다", Toast.LENGTH_SHORT).show();
                        } else {
                            selectedDate = String.valueOf(date.getMonth() + 1) + "월 " + String.valueOf(date.getDay()) + "일 " + "(" + day + ")";
                            Toast.makeText(getActivity(), selectedDate, Toast.LENGTH_SHORT).show();

                            if (String.valueOf(date.getMonth() + 1).length() == 1) { // 1월~9월
                                if (String.valueOf(date.getDay()).length() == 1) {
                                    serverDate = "0" + String.valueOf(date.getMonth() + 1) + "." + "0" + String.valueOf(date.getDay());
                                } else if (String.valueOf(date.getDay()).length() == 2) {
                                    serverDate = "0" + String.valueOf(date.getMonth() + 1) + "." + String.valueOf(date.getDay());
                                }
                            } else if (String.valueOf(date.getMonth() + 1).length() == 2) { // 10월~12월
                                if (String.valueOf(date.getDay()).length() == 1) {
                                    serverDate = String.valueOf(date.getMonth() + 1) + "." + "0" + String.valueOf(date.getDay());
                                } else if (String.valueOf(date.getDay()).length() == 2) {
                                    serverDate = String.valueOf(date.getMonth() + 1) + "." + String.valueOf(date.getDay());
                                }
                            }

                            textView.setText(selectedDate);
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            }
        });

        return view;
    }

    public class HolidayDecorator implements DayViewDecorator {
        private final Calendar calendar = Calendar.getInstance();

        public HolidayDecorator() {

        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);

            return (weekDay == Calendar.SUNDAY) || (weekDay == Calendar.MONDAY) || weekDay == Calendar.TUESDAY || weekDay == Calendar.WEDNESDAY || (weekDay == Calendar.THURSDAY);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.LTGRAY));
        }
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
            view.addSpan(new ForegroundColorSpan(Color.BLACK));
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.4f));
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
            view.addSpan(new ForegroundColorSpan(Color.BLACK));
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.4f));
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
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }

        public void setDate(Date date) {
            this.date = CalendarDay.from(date);
        }
    }
}