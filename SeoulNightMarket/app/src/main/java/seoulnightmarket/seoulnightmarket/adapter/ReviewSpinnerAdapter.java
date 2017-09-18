package seoulnightmarket.seoulnightmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import seoulnightmarket.seoulnightmarket.R;

/**
 * Created by Yookmoonsu on 2017-09-18.
 */

public class ReviewSpinnerAdapter extends BaseAdapter {
    Context context;
    int[] flags;
    LayoutInflater inflater;

    public ReviewSpinnerAdapter(Context context, int[] flags) {
        this.context = context;
        this.flags = flags;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.spinner_item, null);
        ImageView imageView = convertView.findViewById(R.id.imageViewSpinner);
        imageView.setImageResource(flags[position]);

        return convertView;
    }
}