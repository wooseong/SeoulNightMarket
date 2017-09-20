package seoulnightmarket.seoulnightmarket.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.TicketAdapter;

public class NumberTicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_ticket);

        TicketAdapter adapter = new TicketAdapter();
        ListView listView = (ListView) findViewById(R.id.ticketListView);
        listView.setAdapter(adapter);

        adapter.addItem(getResources().getDrawable(R.drawable.chickenfit), "치킨핏", 5, 15, 10);
        adapter.addItem(getResources().getDrawable(R.drawable.imsteak), "아임스테이크", 10, 16, 6);
        adapter.addItem(getResources().getDrawable(R.drawable.pandagrill), "팬더그릴", 20, 30, 10);
    }
}