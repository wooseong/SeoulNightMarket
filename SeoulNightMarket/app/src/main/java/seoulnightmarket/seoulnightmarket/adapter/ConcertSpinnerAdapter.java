package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Yookmoonsu on 2017-10-19.
 */

public class ConcertSpinnerAdapter extends ArrayAdapter<String> {
    Context context;
    String[] items = new String[]{};

    public ConcertSpinnerAdapter(final Context context, final int textViewResourceId, final String[] objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) { // 스피너 클릭시 나타나는 뷰
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(items[position]);
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundColor(Color.parseColor("#FFA726"));
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv.setTextSize(20);

        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // 기본 스피너
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(items[position]);
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(15);

        return convertView;
    }
}