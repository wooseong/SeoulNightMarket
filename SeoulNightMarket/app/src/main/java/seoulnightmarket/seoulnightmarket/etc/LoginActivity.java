package seoulnightmarket.seoulnightmarket.etc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import seoulnightmarket.seoulnightmarket.R;

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
    }

    public void btnLogin(View v) { // 로그인 버튼
        phoneNumber = idText.getText().toString();
        password = passwordText.getText().toString(); // 입력한 텍스트 가져옴

        if (true) { // 데이터베이스에 있는 회원 아이디와 비밀번호가 일치하면
            Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
        }

        else if (true) { // 일치하지 않으면
            Toast.makeText(getApplicationContext(), "아이디와 패스워드를 다시 확인하세요", Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(LoginActivity.this, AreaInformationWithTabBar.class));
    }

    public void btnJoin(View v) { // 회원가입 버튼
        startActivity(new Intent(LoginActivity.this, MembershipJoinActivity.class));
    }
}