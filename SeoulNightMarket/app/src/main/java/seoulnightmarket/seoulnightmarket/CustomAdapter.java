package seoulnightmarket.seoulnightmarket;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Yookmoonsu on 2017-09-12.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    int[] image;
    String[] text;
    LayoutInflater inflater = null;

    public CustomAdapter(Context context, int[] image, String[] text) {
        this.context = context;
        this.image = image;
        this.text = text;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int position) {
        return image[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.gridview_market, null);
        holder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
        holder.textView = (TextView) rowView.findViewById(R.id.textView);

        holder.imageView.setImageResource(image[position]);
        holder.textView.setText(text[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+ text[position], Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, FoodTruck.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        return rowView;
    }

    public class Holder {
        ImageView imageView;
        TextView textView;
    }
}