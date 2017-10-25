package seoulnightmarket.seoulnightmarket.Activity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.TicketAdapter;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class NumberTicketActivity extends AppCompatActivity {
    int count = 0;
    TicketAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_ticket);

        listView = (ListView) findViewById(R.id.ticketListView);

        String uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/ticket/reservation")
                .buildUpon()
                .appendQueryParameter("phone", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowLoginID()))
                .build().toString();

        TicketAsyncTask ticketAsyncTask = new TicketAsyncTask("예약현황");
        ticketAsyncTask.execute(uri);

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

            for (int i = 0; i < Singleton.getInstance().getWaitStoreList().size(); i++) {
                String uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/ticket")
                        .buildUpon()
                        .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getWaitStoreList().get(i)))
                        .build().toString();

                HttpAsyncTask httpAsyncTask = new HttpAsyncTask("현재 순서");
                httpAsyncTask.execute(uri);
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

                adapter = new TicketAdapter();
                for (int j = 0; j < Singleton.getInstance().getWaitStoreList().size(); j++) {
                    adapter.addItem(HttpTask.getInstance().translateBitmap(Singleton.getInstance().getStoreImageList().get(j)), Singleton.getInstance().getWaitStoreList().get(j), Singleton.getInstance().getNowWaitList().get(j),
                            Singleton.getInstance().getMyWaitList().get(j), Singleton.getInstance().getMyWaitList().get(j) - Singleton.getInstance().getNowWaitList().get(j));
                }

                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                listView.invalidateViews();
            }
        }
    }
}