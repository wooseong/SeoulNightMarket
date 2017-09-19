package seoulnightmarket.seoulnightmarket.etc;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.MainAdapter;

public class MainActivity extends AppCompatActivity {
    public int[] nightMarket = {R.drawable.order, R.drawable.home_banpo, R.drawable.home_ddp, R.drawable.home_yyd, R.drawable.home_cgc, R.drawable.home_cggj};
    public GridView gridView;
    MainAdapter mainAdapter;

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

        ImageView imageView = (ImageView) findViewById(R.id.imageViewInitial);
        AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground(); // 이미지뷰의 배경으로 애니메이션 객체 얻음
        drawable.start(); // 애니메이션 시작

        mainAdapter = new MainAdapter(getApplicationContext(), nightMarket); // 그리드뷰에 어댑터 연결
        gridView = (GridView) findViewById(R.id.gridViewInitial);
        gridView.setAdapter(mainAdapter);
    }
}