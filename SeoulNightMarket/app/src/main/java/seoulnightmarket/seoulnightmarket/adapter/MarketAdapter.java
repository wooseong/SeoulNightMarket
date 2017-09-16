package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.fragment.FoodTruckFragment;

public class MarketAdapter extends BaseAdapter {

    private Context context;
    private int[] image;
    private String[] text;

    public MarketAdapter(Context context, int[] image, String[] text) {
        this.context = context;
        this.image = image;
        this.text = text;
    }

    @Override
    public int getCount() {
        return image.length;
    } // 그리드뷰에 출력할 목록 갯수

    @Override
    public Object getItem(int position) {
        return image[position];
    } // 아이템 호출

    @Override
    public long getItemId(int position) {
        return position;
    } // 아이템의 아이디

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.gridview_market, null);
            ImageView imageView = gridView.findViewById(R.id.imageViewMarket);
            TextView textView = gridView.findViewById(R.id.textViewMarket);

            imageView.setImageResource(image[position]);
            textView.setText(text[position]);
        } else {
            gridView = convertView;
        }

        gridView.setOnClickListener(new View.OnClickListener() { // 아이템 클릭 이벤트
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + text[position], Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, FoodTruckFragment.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); // 클릭한 메뉴 상세 화면으로 이동
            }
        });

        return gridView;
    }
}