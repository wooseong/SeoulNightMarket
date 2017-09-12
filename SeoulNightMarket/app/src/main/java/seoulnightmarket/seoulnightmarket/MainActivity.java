package seoulnightmarket.seoulnightmarket;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static RecyclerView recyclerView;
    private static Toolbar toolbar;
    private static CollapsingToolbarLayout collapsingToolbarLayout;
    int mutedColor = R.attr.colorPrimary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Toolbar Animation Demo");//Set Title over collapsing toolbar layout


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.background);

      // It will generate colors based on the image in an AsyncTask.
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                mutedColor = palette.getMutedColor(R.color.colorPrimary);
                collapsingToolbarLayout.setContentScrimColor(mutedColor);
                collapsingToolbarLayout.setStatusBarScrimColor(R.color.black_trans);
            }
        });
        setRecyclerView();
    }

    //Setting recycler view
    private void setRecyclerView() {

        recyclerView = (RecyclerView)
                findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(MainActivity.this));//Linear Items


        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add("Card Item " + i);//Adding items to recycler view
        }
        RecyclerView_Adapter adapter = new RecyclerView_Adapter(MainActivity.this, arrayList);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview

    }
}
