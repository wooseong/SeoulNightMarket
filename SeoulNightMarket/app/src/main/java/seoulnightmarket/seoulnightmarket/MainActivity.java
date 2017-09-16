package seoulnightmarket.seoulnightmarket;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(MainActivity.this,FlexibleSpaceWithImageWithViewPagerTabActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}