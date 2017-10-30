package seoulnightmarket.seoulnightmarket.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;
import seoulnightmarket.seoulnightmarket.etc.Singleton;

public class LoginActivity extends AppCompatActivity {
    private EditText idText;
    private EditText passwordText;
    private String phoneNumber;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idText = (EditText) findViewById(R.id.phoneNumberText);
        passwordText = (EditText) findViewById(R.id.passwordText);

        idText.getBackground().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.ADD.SRC_ATOP);
        passwordText.getBackground().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.ADD.SRC_ATOP);
    }

    public void btnLogin(View v) { // 로그인 버튼
        phoneNumber = idText.getText().toString();
        password = passwordText.getText().toString(); // 입력한 텍스트 가져옴

        if (phoneNumber.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(), "아이디와 패스워드를 다시 확인하세요", Toast.LENGTH_SHORT).show();
        } else {
            String uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/login")
                    .buildUpon()
                    .appendQueryParameter("id", phoneNumber)
                    .appendQueryParameter("password", password)
                    .build().toString();

            HttpAsyncTask httpAsyncTask = new HttpAsyncTask("로그인");
            httpAsyncTask.execute(uri);
        }
    }

    public void btnJoin(View v) { // 회원가입 버튼
        startActivity(new Intent(LoginActivity.this, MembershipJoinActivity.class));
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

            // 데이터베이스에 있는 회원 아이디와 비밀번호가 일치하면
            if (Singleton.getInstance().getNowLogin() == true) {
                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                if (phoneNumber.contains("Admin")) { // 판매자
                    Singleton.getInstance().setNowLoginID(phoneNumber);
                    startActivity(new Intent(LoginActivity.this, SellerActivity.class));
                    finish();
                } else { // 구매자
                    Singleton.getInstance().setNowLoginID(phoneNumber);

                    if (Singleton.getInstance().getBtnOrder()) {
                        startActivity(new Intent(LoginActivity.this, DetailActivity.class));
                    } else {
                        startActivity(new Intent(LoginActivity.this, NumberTicketActivity.class));
                    }
                    finish();
                }
            } else {
                Toast.makeText(getApplicationContext(), "아이디와 패스워드를 다시 확인하세요", Toast.LENGTH_SHORT).show();
            }
        }
    }
}