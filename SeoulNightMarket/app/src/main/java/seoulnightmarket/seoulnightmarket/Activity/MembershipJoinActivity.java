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

public class MembershipJoinActivity extends AppCompatActivity {
    private EditText phonenumberText;
    private EditText passwordText;
    private EditText repasswordText;
    private EditText nicknameText;
    private String phonenumber;
    private String password;
    private String repassword;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_join);

        phonenumberText = (EditText) findViewById(R.id.phoneNumber);
        passwordText = (EditText) findViewById(R.id.password);
        repasswordText = (EditText) findViewById(R.id.repassword);
        nicknameText = (EditText) findViewById(R.id.nickname);

        phonenumberText.getBackground().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.ADD.SRC_ATOP);
        passwordText.getBackground().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.ADD.SRC_ATOP);
        repasswordText.getBackground().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.ADD.SRC_ATOP);
        nicknameText.getBackground().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.ADD.SRC_ATOP);
    }

    public void btnConfirm(View v) { // 회원 가입 버튼 서버로 회원 정보 전송
        phonenumber = phonenumberText.getText().toString();
        password = passwordText.getText().toString();
        repassword = repasswordText.getText().toString();
        nickname = nicknameText.getText().toString();

        if (phonenumber.length() == 0) {
            Toast.makeText(getApplicationContext(), "핸드폰 번호를 입력하세요", Toast.LENGTH_SHORT).show();
        } else if (password.length() == 0) {
            Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
        } else if (repassword.length() == 0) {
            Toast.makeText(getApplicationContext(), "비밀번호를 다시 확인하세요", Toast.LENGTH_SHORT).show();
        } else if (nickname.length() == 0) {
            Toast.makeText(getApplicationContext(), "닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
        } else {
            String uri = Uri.parse("http://ec2-13-59-247-200.us-east-2.compute.amazonaws.com:3000/signup")
                    .buildUpon()
                    .appendQueryParameter("phone", phonenumber)
                    .appendQueryParameter("password", password)
                    .appendQueryParameter("nickname", nickname)
                    .build().toString();

            HttpAsyncTask httpAsyncTask = new HttpAsyncTask("회원가입");
            httpAsyncTask.execute(uri);
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
            return HttpTask.getInstance().POST(urls[0], type);
        }
        // onPostExecute displays the results of the AsyncTask.

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MembershipJoinActivity.this, LoginActivity.class));
        }
    }
}