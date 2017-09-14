package seoulnightmarket.seoulnightmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void home(View v) {
        startActivity(new Intent(MainActivity.this, InitialScreen.class));
    }

    public void introduction(View v) {
        startActivity(new Intent(MainActivity.this, Introduction.class));
    }

    public void nightMarket(View v) {
        startActivity(new Intent(MainActivity.this, NightMarket.class));
    }

    public void concert(View v) {
        startActivity(new Intent(MainActivity.this, Concert.class));
    }

    public void fragment(View v) {
        startActivity(new Intent(MainActivity.this, NightMarketFragment.class));
    }
}