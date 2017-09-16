package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import seoulnightmarket.seoulnightmarket.R;

/**
 * Created by Yookmoonsu on 2017-09-13.
 */

public class ConcertAdapter extends BaseAdapter {
    private Context context;
    private int[] image;
    private String[] text;

    public ConcertAdapter(Context context, int[] image, String[] text) {
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
            gridView = inflater.inflate(R.layout.gridview_concert, null);
            ImageView imageView = gridView.findViewById(R.id.musicianImage);
            TextView textView = gridView.findViewById(R.id.musicianName);

            imageView.setImageResource(image[position]);
            textView.setText(text[position]);
        } else {
            gridView = convertView;
        }

        return gridView;
    }
}