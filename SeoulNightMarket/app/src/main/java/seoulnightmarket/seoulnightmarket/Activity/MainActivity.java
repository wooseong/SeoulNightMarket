package seoulnightmarket.seoulnightmarket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.adapter.MainAdapter;
import seoulnightmarket.seoulnightmarket.etc.TaskService;

public class MainActivity extends AppCompatActivity {
    public int[] nightMarket = {R.drawable.login, R.drawable.yyd_white, R.drawable.ddp_white, R.drawable.bp_white, R.drawable.cgc_white, R.drawable.cggj_white};
    public GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, TaskService.class)); // 앱 실행되는중

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MainAdapter mainAdapter = new MainAdapter(getApplicationContext(), nightMarket); // 그리드뷰에 어댑터 연결
        gridView = (GridView) findViewById(R.id.gridViewInitial);
        gridView.setAdapter(mainAdapter);
    }
}