package seoulnightmarket.seoulnightmarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Introduction extends AppCompatActivity {
    public TextView textView0;
    public TextView textView1;
    public TextView textView2;
    public ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        textView0 = (TextView) findViewById(R.id.introductionText0);
        textView1 = (TextView) findViewById(R.id.introductionText1);
        textView2 = (TextView) findViewById(R.id.introductionText2);
        imageView = (ImageView) findViewById(R.id.introductionImage);

        imageView.setImageResource(R.drawable.banpo);
    }
}