package seoulnightmarket.seoulnightmarket.Activity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.TicketAdapter;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class NumberTicketActivity extends AppCompatActivity {
    int count = 0;
    private RecyclerView recyclerView;
    private TicketAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView alterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_ticket);

        recyclerView = (RecyclerView) findViewById(R.id.ticket_recycler_view);
        alterTextView = (TextView) findViewById(R.id.alterView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/ticket/reservation")
                .buildUpon()
                .appendQueryParameter("phone", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowLoginID()))
                .build().toString();

        TicketAsyncTask ticketAsyncTask = new TicketAsyncTask("예약현황");
        ticketAsyncTask.execute(uri);

        Button btnLogout = (Button) findViewById(R.id.btnLogoutNumberTicket);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance().setNowLogin(false);
                finish();
            }
        });
        ImageButton btnBackNumberTicket = (ImageButton) findViewById(R.id.btnBackNumberTicket);
        btnBackNumberTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance().setNowLogin(true);
                finish();
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.md_black_1000));
        }
    }

    public class TicketAsyncTask extends AsyncTask<String, Void, String> {
        String type;

        TicketAsyncTask(String type) {
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
            count = 0;

            if(Singleton.getInstance().getWaitStoreList().size()>0)
            {
                alterTextView.setVisibility(View.INVISIBLE);

                for (int i = 0; i < Singleton.getInstance().getWaitStoreList().size(); i++) {
                    String uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/ticket")
                            .buildUpon()
                            .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getWaitStoreList().get(i)))
                            .build().toString();

                    HttpAsyncTask httpAsyncTask = new HttpAsyncTask("현재 순서");
                    httpAsyncTask.execute(uri);
                }
            }
            else
            {
                alterTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public class HttpAsyncTask extends AsyncTask<String, Void, String> {
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
            count++;

            if (count == Singleton.getInstance().getWaitStoreList().size()) {

                adapter = new TicketAdapter(getApplicationContext());
                for (int j = 0; j < Singleton.getInstance().getWaitStoreList().size(); j++) {
                    adapter.addItem(Singleton.getInstance().getStoreImageList().get(j), Singleton.getInstance().getWaitStoreList().get(j), Singleton.getInstance().getNowWaitList().get(j),
                            Singleton.getInstance().getMyWaitList().get(j), Singleton.getInstance().getMyWaitList().get(j) - Singleton.getInstance().getNowWaitList().get(j));
                }

                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                recyclerView.invalidate();
            }
        }
    }
}