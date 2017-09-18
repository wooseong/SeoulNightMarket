package seoulnightmarket.seoulnightmarket.etc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import seoulnightmarket.seoulnightmarket.R;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext()); // SDK 초기화 setContentView 보다 먼저 실행되어야함
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create(); // 로그인 응답을 처리할 콜백관리자

        loginButton = (LoginButton) findViewById(R.id.btnFacebook);
        loginButton.setReadPermissions("public_profile", "user_friends", "email"); // 정보를 수집하기 위해 허가를 받음
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() { // 버튼에 바로 콜백 등록하면 LoginManager에 콜백 등록안해도 됨
            @Override
            public void onSuccess(LoginResult loginResult) { // 로그인 성공시 호출
                Log.e("토큰", loginResult.getAccessToken().getToken());
                Log.e("유저아이디", loginResult.getAccessToken().getUserId());
                Log.e("퍼미션 리스트", loginResult.getAccessToken().getPermissions() + "");

                //loginResult.getAccessToken() 정보를 가지고 유저 정보를 가져올수 있음
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            Log.e("user profile", object.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}