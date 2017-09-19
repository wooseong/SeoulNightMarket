package seoulnightmarket.seoulnightmarket.etc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import seoulnightmarket.seoulnightmarket.R;

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
    }

    public void btnConfirm(View v) {
        phonenumber = phonenumberText.getText().toString();
        password = passwordText.getText().toString();
        repassword = repasswordText.getText().toString();
        nickname = nicknameText.getText().toString();

        startActivity(new Intent(MembershipJoinActivity.this, LoginActivity.class));

        Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
    }
}