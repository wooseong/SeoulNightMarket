package seoulnightmarket.seoulnightmarket.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
}