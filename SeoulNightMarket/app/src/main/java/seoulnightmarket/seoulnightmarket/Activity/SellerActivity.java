package seoulnightmarket.seoulnightmarket.Activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import seoulnightmarket.seoulnightmarket.R;

public class SellerActivity extends AppCompatActivity {
    private String orderNumber; // 현재 주문번호
    private String waitingNumber; // 남은 대기자 수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        // 판매자가 로그인 했을 시 서버에서 푸드트럭의 이름과 대기 번호를 받아옴
        TextView foodTruckName = (TextView) findViewById(R.id.foodtruckname);
        final Button btnOrder = (Button) findViewById(R.id.ordernumber);
        final TextView waitNumber = (TextView) findViewById(R.id.waitnumber);

        // 서버에서 푸드트럭의 대기자 수를 받아와야함
        btnOrder.setOnClickListener(new View.OnClickListener() { // 현재 주문번호 버튼 누르면
            @Override
            public void onClick(View view) {
                orderNumber = btnOrder.getText().toString(); // 다음 번호로 넘어감
                int order = Integer.parseInt(orderNumber);
                waitingNumber = waitNumber.getText().toString(); // 대기자 수 1 감소
                int wait = Integer.parseInt(waitingNumber);

                // 문자메세지 보내기
                String message = "앞에 대기자수가 5명입니다" + "\n" + "해당 푸드트럭 앞으로 와주시길 바랍니다";
                sendSMS("01089336478", message);

                if (wait > 0) {
                    order++;
                } else {
                    Toast.makeText(getApplicationContext(), "현재 대기중인 손님이 없습니다", Toast.LENGTH_SHORT).show();
                }

                orderNumber = Integer.toString(order);
                btnOrder.setText(orderNumber);

                if (wait > 0) {
                    wait--;
                    if (wait == 0) {
                        Toast.makeText(getApplicationContext(), "마지막 손님입니다", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "현재 대기중인 손님이 없습니다", Toast.LENGTH_SHORT).show();
                }

                waitingNumber = Integer.toString(wait);
                waitNumber.setText(waitingNumber);
            }
        });
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
                        Toast.makeText(getBaseContext(), "SMS sent", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service", Toast.LENGTH_SHORT).show();
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