package seoulnightmarket.seoulnightmarket.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class SellerActivity extends AppCompatActivity {
    String uri;
    String message;
    TextView foodTruckName;
    TextView waitNumber;
    TextView textViewNow;
    TextView nickNameText;
    boolean created;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        foodTruckName = (TextView) findViewById(R.id.foodtruckname);
        waitNumber = (TextView) findViewById(R.id.waitnumber);
        textViewNow = (TextView) findViewById(R.id.textViewNow);
        nickNameText = (TextView) findViewById(R.id.callNickName);

        uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/ticket")
                .buildUpon()
                .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowSeller()))
                .build().toString();

        created = false;
        TicketAsyncTask ticketAsyncTask = new TicketAsyncTask("번호표 보기");
        ticketAsyncTask.execute(uri);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, 1);
        }

        Button btnLogoutSeller = (Button) findViewById(R.id.btnLogoutSeller);
        btnLogoutSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance().setNowLogin(false);
                startActivity(new Intent(SellerActivity.this, MainActivity.class));
                finish();
            }
        });

        ImageButton btnBackSeller = (ImageButton) findViewById(R.id.btnBackSeller);
        btnBackSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    public void btnTicketCall(View v) {
        if (Singleton.getInstance().getWaitCount() > 0) {
            TicketAsyncTask ticketAsyncTask = new TicketAsyncTask("번호표 보기");
            ticketAsyncTask.execute(uri);
        } else {
            Toast.makeText(getApplicationContext(), "현재 대기중인 손님이 없습니다", Toast.LENGTH_SHORT).show();
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

            if (created == true) {
                if (Singleton.getInstance().getWaitCount() >= 5) {
                    message = "[ " + Singleton.getInstance().getNowSeller() + " 푸드트럭 ] \n손님 앞에 대기자가 5명 남았습니다. 푸드트럭 앞으로 와주세요 :)";
                    Log.e("RECEIVER", Singleton.getInstance().getSMSReceiver());
                    sendSMS(Singleton.getInstance().getSMSReceiver(), message);
                }

                String url = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/ticket/call")
                        .buildUpon()
                        .appendQueryParameter("store", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowSeller()))
                        .appendQueryParameter("number", HttpTask.getInstance().getURLEncode(Singleton.getInstance().getNowClient() + ""))
                        .build().toString();

                HttpAsyncTask httpAsyncTask = new HttpAsyncTask("번호표 호출");
                httpAsyncTask.execute(url);
            } else {
                foodTruckName.setText(Singleton.getInstance().getNowSeller());
                textViewNow.setText(Singleton.getInstance().getNowClient() + "");
                waitNumber.setText(Singleton.getInstance().getWaitCount() + "");
                nickNameText.setText(Singleton.getInstance().getNowCallNickName());
                created = true;
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

            if (Singleton.getInstance().getWaitCount() > 0) {
                Singleton.getInstance().setWaitCount(Singleton.getInstance().getWaitCount() - 1);
                if (Singleton.getInstance().getWaitCount() == 0) {
                    Singleton.getInstance().setNowClient(0);
                } else {
                    Singleton.getInstance().setNowClient(Singleton.getInstance().getNowClient() + 1);
                }
            }

            foodTruckName.setText(Singleton.getInstance().getNowSeller());
            textViewNow.setText(Singleton.getInstance().getNowClient() + "");
            waitNumber.setText(Singleton.getInstance().getWaitCount() + "");
            nickNameText.setText(Singleton.getInstance().getNowCallNickName());
        }
    }

    private void sendSMS(final String phoneNumber, String message) { // 문자 보내기 함수
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        final PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        final PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "대기고객님에게 호출문자를 전송하였습니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "문자 전송 서비스가 불가능합니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }
}