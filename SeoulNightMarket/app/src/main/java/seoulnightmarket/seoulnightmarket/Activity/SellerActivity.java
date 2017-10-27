package seoulnightmarket.seoulnightmarket.Activity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.MenuAdapter;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;
import seoulnightmarket.seoulnightmarket.fragment.FragmentMenu;

public class SellerActivity extends AppCompatActivity
{
    String uri;
    TextView foodTruckName;
    Button btnOrder;
    TextView waitNumber;
    boolean created;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        // 판매자가 로그인 했을 시 서버에서 푸드트럭의 이름과 대기 번호를 받아옴
        foodTruckName = (TextView) findViewById(R.id.foodtruckname);
        btnOrder = (Button) findViewById(R.id.ordernumber);
        waitNumber = (TextView) findViewById(R.id.waitnumber);

        uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/ticket")
                .buildUpon()
                .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowSeller()))
                .build().toString();

        created = false;
        TicketAsyncTask ticketAsyncTask = new TicketAsyncTask("번호표 보기");
        ticketAsyncTask.execute(uri);

        // 서버에서 푸드트럭의 대기자 수를 받아와야함

        btnOrder.setOnClickListener(new View.OnClickListener()
        { // 현재 주문번호 버튼 누르면
            @Override
            public void onClick(View view)
            {
                if (Singleton.getInstance().getWaitCount() > 0)
                {
                    uri = Uri.parse("http://ec2-13-59-247-200.usd-east-2.compute.amazonaws.com:3000/ticket")
                            .buildUpon()
                            .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowSeller()))
                            .build().toString();

                    TicketAsyncTask ticketAsyncTask = new TicketAsyncTask("번호표 보기");
                    ticketAsyncTask.execute(uri);
                }
                else
                {
                     Toast.makeText(getApplicationContext(), "현재 대기중인 손님이 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class TicketAsyncTask extends AsyncTask<String, Void, String>
    {
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
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            Log.e("CREATED", created+"");

            if(created == true)
            {
                uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/ticket/call")
                        .buildUpon()
                        .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowSeller()))
                        .appendQueryParameter("number", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowClient()+""))
                        .build().toString();

                HttpAsyncTask httpAsyncTask = new HttpAsyncTask("번호표 호출");
                httpAsyncTask.execute(uri);
                created = false;
            }
            else
            {
                foodTruckName.setText(Singleton.getInstance().getNowSeller());
                btnOrder.setText(Singleton.getInstance().getNowClient()+"");
                waitNumber.setText(Singleton.getInstance().getWaitCount()+"");
                created = true;
            }
        }
    }

    public class HttpAsyncTask extends AsyncTask<String, Void, String>
    {
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
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            if(Singleton.getInstance().getWaitCount()>0)
            {
                Singleton.getInstance().setWaitCount(Singleton.getInstance().getWaitCount()-1);
                Singleton.getInstance().setNowClient(Singleton.getInstance().getNowClient()+1);
            }

            foodTruckName.setText(Singleton.getInstance().getNowSeller());
            btnOrder.setText(Singleton.getInstance().getNowClient()+"");
            waitNumber.setText(Singleton.getInstance().getWaitCount()+"");
        }
    }
}