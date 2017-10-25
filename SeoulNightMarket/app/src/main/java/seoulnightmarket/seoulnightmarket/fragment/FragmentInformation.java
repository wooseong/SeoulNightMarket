package seoulnightmarket.seoulnightmarket.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.Date;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.InformationAdapter;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class FragmentInformation extends Fragment {
    private View view;
    private ListView listView;
    private InformationAdapter adapter;
    private String uri;

    public FragmentInformation() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // onCreate 후에 화면을 구성할때 호출
        view = inflater.inflate(R.layout.activity_fragment_information, null);

        listView = view.findViewById(R.id.informationlistview);

        if (Singleton.getInstance().getType() == "foodTruck") {
            uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/foodstore/course")
                    .buildUpon()
                    .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowStore()))
                    .build().toString();
        } else {
            uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/handmadestore/course")
                    .buildUpon()
                    .appendQueryParameter("place", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getRegion()))
                    .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowStore()))
                    .build().toString();
        }

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask("순환일정");
        httpAsyncTask.execute(uri);

        Button btnDate = view.findViewById(R.id.btnDate);
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
                            Toast.makeText(getActivity(), String.valueOf(date.getMonth() + 1) + "월 " + String.valueOf(date.getDay()) + "일 " + "(" + day + ")", Toast.LENGTH_SHORT).show();
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

    public class HttpAsyncTask extends AsyncTask<String, Void, String> {
        int count;
        int beforeIndex;
        String type;

        HttpAsyncTask(String type) {
            this.type = type;
        }

        @Override
        protected String doInBackground(String... urls) {
            //urls[0] 은 URL 주소
            return HttpTask.getInstance().GET(urls[0], type);
        }
        // onPostExecute displays the results of the AsyncTask.

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            String[] region = Singleton.getInstance().getCourse().split("/");
            adapter = new InformationAdapter();

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

            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.invalidateViews();

            setListViewHeightBasedOnItems(listView);
        }
    }

    public void setListViewHeightBasedOnItems(ListView listView) { // 리스트뷰 높이 계산
        // Get list adpter of listview;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) return;

        int numberOfItems = listAdapter.getCount();

        // Get total height of all items.
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = listAdapter.getView(itemPos, null, listView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }

        // Get total height of all item dividers.
        int totalDividersHeight = listView.getDividerHeight() * (numberOfItems - 1);

        // Set list height.
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}