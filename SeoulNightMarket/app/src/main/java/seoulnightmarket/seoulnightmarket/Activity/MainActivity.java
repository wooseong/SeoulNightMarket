package seoulnightmarket.seoulnightmarket.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.MainAdapter;

public class MainActivity extends AppCompatActivity {
    public int[] nightMarket = {R.drawable.order, R.drawable.yyd, R.drawable.ddp, R.drawable.bp, R.drawable.cgc, R.drawable.cggj};
    public GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageView imageView = (ImageView) findViewById(R.id.imageViewInitial);
//        AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground(); // 이미지뷰의 배경으로 애니메이션 객체 얻음
//        drawable.start(); // 애니메이션 시작

        MainAdapter mainAdapter = new MainAdapter(getApplicationContext(), nightMarket); // 그리드뷰에 어댑터 연결
        gridView = (GridView) findViewById(R.id.gridViewInitial);
        gridView.setAdapter(mainAdapter);
    }
}