package seoulnightmarket.seoulnightmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(3000);
            setContentView(R.layout.activity_main);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void home(View v) {
        startActivity(new Intent(MainActivity.this, InitialScreen.class));
    }
}