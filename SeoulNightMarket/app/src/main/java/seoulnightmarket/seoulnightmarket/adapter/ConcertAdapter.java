package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import seoulnightmarket.seoulnightmarket.R;
import seoulnightmarket.seoulnightmarket.etc.HttpTask;

/**
 * Created by Yookmoonsu on 2017-09-13.
 */

public class ConcertAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> image;
    private ArrayList<String> text;

    public ConcertAdapter(Context context, ArrayList<String> image, ArrayList<String> text) {
        this.context = context;
        this.image = image;
        this.text = text;
    }

    @Override
    public int getCount() {
        return image.size();
    } // 그리드뷰에 출력할 목록 갯수

    @Override
    public Object getItem(int position) {
        return HttpTask.getInstance().translateBitmap(image.get(position));
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

            imageView.setImageBitmap(HttpTask.getInstance().translateBitmap(image.get(position)));
            textView.setText(text.get(position));
        } else {
            gridView = convertView;
        }

        return gridView;
    }
}